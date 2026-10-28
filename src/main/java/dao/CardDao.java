package dao;

import entity.Card;

import java.util.List;

public interface CardDao {
    List<Card> readAll();
    List<Card> readAllUserCards(long id);
    void add(Card card);
    Card read(long id);
    void update(long id, Card card);
    void delete(Card card);
}
