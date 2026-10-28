package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> readAll();
    void add(User user);
    User read(long id);
    void update(long id, User user);
    void delete(User user);
}
