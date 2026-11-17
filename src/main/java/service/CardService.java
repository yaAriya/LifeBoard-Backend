package service;

import entity.Card;

import java.util.List;

public interface CardService {
    List<Card> readAll();

    List<Card> readAllUserCards(Long id);

    void add(Card card);

    Card read(Long id);

    void update(Long id, Card card);

    void delete(Card card);
}
