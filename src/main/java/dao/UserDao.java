package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> readAll();

    void add(User user);

    User read(Long id);

    void update(User user);

    void delete(User user);
}
