package service;

import dao.CardDao;
import entity.Card;
import entity.GoalCard;
import entity.TaskCard;
import entity.TodoCard;
import exception.CardServiceException;
import exception.InvalidEntityException;
import exception.NotFoundException;
import exception.UserServiceException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import validator.CardValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardDao cardDao;
    private final CardValidator validator;
    private final UserService userService;

    public CardServiceImpl(CardDao cardDao, CardValidator cardValidator, UserService userService) {
        this.cardDao = cardDao;
        this.validator = cardValidator;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> readAll() throws CardServiceException {
        try {
            List<Card> result = new ArrayList<>();
            List<Card> cards = cardDao.readAll();
            for (Card card : cards) {
                if (!result.contains(card)) {
                    result.add(card);
                }
            }
            return result;
        } catch (HibernateException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> readAllUserCards(Long id) throws CardServiceException {
        try {
            if (userService.read(id) == null) {
                throw new NotFoundException();
            }
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            }
            return cardDao.readAllUserCards(id);
        } catch (HibernateException | NotFoundException | IllegalArgumentException | UserServiceException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void add(Card card) throws CardServiceException {
        try {
            if (!validator.registerValidate(card)) {
                throw new InvalidEntityException();
            }
            cardDao.add(card);
        } catch (HibernateException | InvalidEntityException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Card read(Long id) throws CardServiceException {
        try {
            if (cardDao.read(id) == null) {
                throw new NotFoundException();
            }
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            }
            return cardDao.read(id);
        } catch (HibernateException | NotFoundException | IllegalArgumentException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(Card card) throws CardServiceException {
        try {
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            }
            Card storedCard = cardDao.read(card.getId());
            if (storedCard == null) {
                throw new NotFoundException();
            }
            if (!sameType(storedCard, card)) {
                throw new InvalidEntityException();
            }
            copyUpdatableProperties(storedCard, card);
            cardDao.update(storedCard);
        } catch (HibernateException | NotFoundException | InvalidEntityException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws CardServiceException {
        try {
            Card card = read(id);
            if (card == null) {
                throw new NotFoundException();
            }
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            }
            cardDao.delete(card);
        } catch (HibernateException | NotFoundException | InvalidEntityException e) {
            throw new CardServiceException(e);
        }
    }

    private static boolean sameType(Card storedCard, Card incomingCard) {
        if (storedCard instanceof TodoCard) {
            return incomingCard instanceof TodoCard;
        }
        if (storedCard instanceof TaskCard) {
            return incomingCard instanceof TaskCard;
        }
        if (storedCard instanceof GoalCard) {
            return incomingCard instanceof GoalCard;
        }
        return false;
    }

    private static void copyUpdatableProperties(Card storedCard, Card incomingCard) {
        storedCard.setName(incomingCard.getName());
        storedCard.setContent(incomingCard.getContent());
        if (incomingCard.getUser() != null) {
            storedCard.setUser(incomingCard.getUser());
        }

        if (storedCard instanceof TodoCard storedTodo && incomingCard instanceof TodoCard incomingTodo) {
            storedTodo.setStatus(incomingTodo.getStatus());
        } else if (storedCard instanceof TaskCard storedTask && incomingCard instanceof TaskCard incomingTask) {
            storedTask.setDeadline(incomingTask.getDeadline());
            storedTask.setStatus(incomingTask.getStatus());
        } else if (storedCard instanceof GoalCard storedGoal && incomingCard instanceof GoalCard incomingGoal) {
            storedGoal.setCategory(incomingGoal.getCategory());
            storedGoal.setStatus(incomingGoal.getStatus());
            storedGoal.setProgress(incomingGoal.getProgress());
        }
    }
}