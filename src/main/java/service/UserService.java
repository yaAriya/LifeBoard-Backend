package service;

import entity.User;

import java.util.List;

public interface UserService {
    List<User> readAll();
    void add(User user);
    User read(long id);
    void update(long id, User user);
    void delete(User user);
}
