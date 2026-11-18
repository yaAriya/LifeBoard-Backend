package dao;

import entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User read(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User user) {
        User updateUser = read(user.getId());
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
