package dao;


import entity.Card;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardDaoImpl implements CardDao {
    private final SessionFactory sessionFactory;

    public CardDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Card> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Card", Card.class)
                .getResultList();
    }

    @Override
    public List<Card> readAllUserCards(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Card с WHERE с.user.id = :id", Card.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void add(Card card) {
        sessionFactory.getCurrentSession().persist(card);
    }

    @Override
    public Card read(Long id) {
        return sessionFactory.getCurrentSession().get(Card.class, id);
    }

    @Override
    public void update(Card card) {
        Card updateCard = read(card.getId());
        updateCard.setName(card.getName());
        updateCard.setContent(card.getContent());
    }

    @Override
    public void delete(Card card) {
        sessionFactory.getCurrentSession().remove(card);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
