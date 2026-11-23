package service;

import dao.UserDao;
import entity.User;
import exception.InvalidEntityException;
import exception.NotFoundException;
import exception.UserServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator validator;

    public UserServiceImpl(UserDao userDao, UserValidator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> readAll() throws UserServiceException {
        try {
            List<User> result = new ArrayList<>();
            List<User> users = userDao.readAll();
            for (User user : users) {
                if (user != null) {
                    result.add(user);
                }
            }
            return result;
        } catch (DataAccessException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional
    @Override
    public void add(User user) throws UserServiceException {
        try {
            if (!validator.registerValidate(user)) {
                throw new InvalidEntityException();
            }
            userDao.add(user);
        } catch (DataAccessException | InvalidEntityException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User read(Long id) throws UserServiceException {
        try {
            if (userDao.read(id) == null) {
                throw new NotFoundException();
            }
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            }
            return userDao.read(id);
        } catch (NotFoundException | IllegalArgumentException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(User user) throws UserServiceException {
        try {
            List<User> users = readAll();
            if (!users.contains(user)) {
                throw new NotFoundException();
            }
            if (!validator.validate(user)) {
                throw new InvalidEntityException();
            }
            userDao.update(user);
        } catch (DataAccessException | NotFoundException | InvalidEntityException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws UserServiceException {
        try {
            User user = read(id);
            List<User> users = readAll();
            if (!users.contains(user)) {
                throw new NotFoundException();
            }
            if (!validator.validate(user)) {
                throw new InvalidEntityException();
            }
            userDao.delete(user);
        } catch (DataAccessException | NotFoundException | InvalidEntityException e) {
            throw new UserServiceException(e);
        }
    }
}