package service;

import dao.CardDao;
import entity.Card;
import entity.GoalCard;
import entity.TaskCard;
import exception.CardServiceException;
import exception.InvalidEntityException;
import exception.NotFoundException;
import exception.UserServiceException;
import org.springframework.dao.DataAccessException;
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
        } catch (DataAccessException e) {
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
        } catch (DataAccessException | NotFoundException | IllegalArgumentException | UserServiceException e) {
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
        } catch (DataAccessException | InvalidEntityException e) {
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
        } catch (DataAccessException | NotFoundException | IllegalArgumentException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(Card card) throws CardServiceException {
        try {
            if (read(card.getId()) == null) {
                throw new NotFoundException();
            }
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            }
            Card updatedCard = setBasicProperty(card);
            if (updatedCard instanceof TaskCard updatedTaskCard && card instanceof TaskCard taskCard) {
                updatedTaskCard.setDeadline(taskCard.getDeadline());
            } else if (updatedCard instanceof GoalCard updatedGoalCard && card instanceof GoalCard goalCard) {
                updatedGoalCard.setCategory(goalCard.getCategory());
            }
            cardDao.update(updatedCard);
        } catch (DataAccessException | NotFoundException | InvalidEntityException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws CardServiceException {
        try {
            Card card = read(id);
            List<Card> cards = readAll();
            if (!cards.contains(card)) {
                throw new NotFoundException();
            }
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            }
            cardDao.delete(card);
        } catch (DataAccessException | NotFoundException | InvalidEntityException e) {
            throw new CardServiceException(e);
        }
    }

    public Card setBasicProperty(Card card) throws CardServiceException {
        Card updateCard = read(card.getId());
        updateCard.setName(card.getName());
        updateCard.setContent(card.getContent());
        return updateCard;
    }
}