package validator;

import entity.Card;
import entity.GoalCard;
import entity.TaskCard;
import entity.TodoCard;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardValidator implements Validator<Card> {
    @Override
    public boolean registerValidate(Card card) {
        return registerValidateId(card.getId())
                && validateName(card.getName())
                && validateContent(card.getContent())
                && validateTypedProperties(card)
                && validateCreatedAtTime(card.getCreatedAt());
    }

    @Override
    public boolean validate(Card card) {
        return validateId(card.getId())
                && validateName(card.getName())
                && validateContent(card.getContent())
                && validateTypedProperties(card)
                && validateCreatedAtTime(card.getCreatedAt());
    }

    private boolean registerValidateId(Long id) {
        return id == null;
    }

    private boolean validateId(Long id) {
        return id != null && id > 0;
    }

    private boolean validateName(String name) {
        return name != null && name.length() < 15;
    }

    private boolean validateContent(String content) {
        return content == null || content.length() < 500;
    }

    private boolean validateTypedProperties(Card card) {
        if (card instanceof TodoCard todoCard) {
            return todoCard.getStatus() != null;
        }
        if (card instanceof TaskCard taskCard) {
            return taskCard.getDeadline() != null && taskCard.getStatus() != null;
        }
        if (card instanceof GoalCard goalCard) {
            return goalCard.getCategory() != null
                    && goalCard.getStatus() != null
                    && goalCard.getProgress() >= 0
                    && goalCard.getProgress() <= 100;
        }
        return false;
    }

    private boolean validateCreatedAtTime(LocalDateTime time) {
        return time != null && !time.isAfter(LocalDateTime.now());
    }
}
