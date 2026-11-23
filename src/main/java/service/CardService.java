package service;

import entity.Card;
import exception.CardServiceException;

import java.util.List;

public interface CardService {
    List<Card> readAll() throws CardServiceException;

    List<Card> readAllUserCards(Long id) throws CardServiceException;

    void add(Card card) throws CardServiceException;

    Card read(Long id) throws CardServiceException;

    void update(Card card) throws CardServiceException;

    void delete(Long id) throws CardServiceException;
}
