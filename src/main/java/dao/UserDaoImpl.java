package dao;

import entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> readAll() {
        return List.of();
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public void update(long id, User user) {

    }

    @Override
    public void delete(User user) {

    }
}
