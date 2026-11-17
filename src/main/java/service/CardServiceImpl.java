package service;

import dao.CardDao;
import entity.Card;
import exception.CardServiceException;
import exception.InvalidEntityException;
import exception.UserServiceException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            }
            if (userService.read(id) == null) {
                throw new EntityNotFoundException();
            }
            return cardDao.readAllUserCards(id);
        } catch (DataAccessException | IllegalArgumentException | EntityNotFoundException | UserServiceException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void add(Card card) throws CardServiceException {
        try {
            List<Card> cards = readAll();
            if (!validator.registerValidate(card)) {
                throw new InvalidEntityException();
            }
            if (cards.contains(card)) {
                throw new EntityExistsException();
            }
            cardDao.add(card);
        } catch (DataAccessException | InvalidEntityException | EntityExistsException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Card read(Long id) throws CardServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            } else if (cardDao.read(id) == null) {
                throw new EntityNotFoundException();
            }
            return cardDao.read(id);
        } catch (DataAccessException | IllegalArgumentException | EntityNotFoundException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(Card card) throws CardServiceException {
        try {
            List<Card> cards = readAll();
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            } else if (!cards.contains(card)) {
                throw new EntityNotFoundException();
            }
            cardDao.update(card);
        } catch (DataAccessException | InvalidEntityException | EntityNotFoundException e) {
            throw new CardServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Card card) throws CardServiceException {
        try {
            List<Card> cards = readAll();
            if (!validator.validate(card)) {
                throw new InvalidEntityException();
            } else if (!cards.contains(card)) {
                throw new EntityNotFoundException();
            }
            cardDao.delete(card);
        } catch (DataAccessException | InvalidEntityException | EntityNotFoundException e) {
            throw new CardServiceException(e);
        }
    }
}