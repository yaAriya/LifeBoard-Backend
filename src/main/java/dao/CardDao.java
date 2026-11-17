package dao;

import entity.Card;

import java.util.List;

public interface CardDao {
    List<Card> readAll();

    List<Card> readAllUserCards(Long id);

    void add(Card card);

    Card read(Long id);

    void update(Card card);

    void delete(Card card);
}
