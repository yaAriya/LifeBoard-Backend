package service;

import dao.UserDao;
import entity.User;
import exception.InvalidEntityException;
import exception.UserServiceException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
            List<User> users = readAll();
            if (!validator.registerValidate(user)) {
                throw new InvalidEntityException();
            } else if (users.contains(user)) {
                throw new EntityExistsException();
            }
            userDao.add(user);
        } catch (DataAccessException | InvalidEntityException | EntityExistsException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User read(Long id) throws UserServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException();
            }
            if (userDao.read(id) == null) {
                throw new EntityNotFoundException();
            }
            return userDao.read(id);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(User user) throws UserServiceException {
        try {
            List<User> users = readAll();
            if (!validator.validate(user)) {
                throw new InvalidEntityException();
            } else if (!users.contains(user)) {
                throw new EntityNotFoundException();
            }
            userDao.update(user);
        } catch (DataAccessException | InvalidEntityException | EntityNotFoundException e) {
            throw new UserServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(User user) throws UserServiceException {
        try {
            List<User> users = readAll();
            if (!validator.validate(user)) {
                throw new InvalidEntityException();
            } else if (!users.contains(user)) {
                throw new EntityNotFoundException();
            }
            userDao.delete(user);
        } catch (DataAccessException | InvalidEntityException | EntityNotFoundException e) {
            throw new UserServiceException(e);
        }
    }
}