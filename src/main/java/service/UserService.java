package service;

import entity.User;
import exception.UserServiceException;

import java.util.List;

public interface UserService {
    List<User> readAll() throws UserServiceException;

    void add(User user) throws UserServiceException;

    User read(Long id) throws UserServiceException;

    void update(User user) throws UserServiceException;

    void delete(Long id) throws UserServiceException;
}
