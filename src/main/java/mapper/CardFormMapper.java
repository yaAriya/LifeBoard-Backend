package mapper;

import controller.CardForm;
import entity.Card;
import entity.GoalCard;
import entity.TaskCard;
import entity.TodoCard;
import entity.User;
import exception.CardServiceException;
import exception.InvalidEntityException;
import exception.NotFoundException;
import exception.UserServiceException;
import org.springframework.stereotype.Component;
import service.UserService;

import java.time.LocalDateTime;

@Component
public class CardFormMapper {
    private final UserService userService;

    public CardFormMapper(UserService userService) {
        this.userService = userService;
    }

    public Card toNewCard(CardForm form) throws CardServiceException {
        if (form == null) {
            throw invalid("Card form is required");
        }
        if (form.getCardType() == null) {
            throw invalid("CardType is required");
        }

        Card card = createTypedCard(form);
        fillCommonProperties(card, form);
        card.setCreatedAt(LocalDateTime.now());
        fillUser(card, form.getUserId());
        return card;
    }

    public Card toUpdateCard(Long id, CardForm form) throws CardServiceException {
        if (id == null || id <= 0) {
            throw invalid("Valid card id is required");
        }
        if (form == null) {
            throw invalid("Card form is required");
        }
        if (form.getCardType() == null) {
            throw invalid("cardType is required");
        }

        Card card = createTypedCard(form);
        card.setId(id);
        fillCommonProperties(card, form);
        card.setCreatedAt(LocalDateTime.now());
        fillUser(card, form.getUserId());
        return card;
    }

    private Card createTypedCard(CardForm form) throws CardServiceException {
        return switch (form.getCardType()) {
            case TODO -> {
                TodoCard card = new TodoCard();
                card.setStatus(form.getTodoStatus());
                yield card;
            }
            case TASK -> {
                TaskCard card = new TaskCard();
                card.setDeadline(form.getDeadline());
                card.setStatus(form.getTaskStatus());
                yield card;
            }
            case GOAL -> {
                GoalCard card = new GoalCard();
                card.setCategory(form.getCategory());
                card.setStatus(form.getGoalStatus());
                card.setProgress(form.getGoalProgress());
                yield card;
            }
        };
    }

    private void fillCommonProperties(Card card, CardForm form) {
        card.setName(form.getName());
        card.setContent(form.getContent());
    }

    private void fillUser(Card card, Long userId) throws CardServiceException {
        if (userId == null) {
            return;
        }
        if (userId <= 0) {
            throw invalid("Valid userId is required");
        }
        try {
            User user = userService.read(userId);
            card.setUser(user);
        } catch (UserServiceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof NotFoundException) {
                throw new CardServiceException(new NotFoundException("User not found", cause));
            }
            throw new CardServiceException(cause == null ? e : cause);
        }
    }

    private static CardServiceException invalid(String message) {
        return new CardServiceException(new InvalidEntityException(message));
    }
}
