package service;

import dao.UserDao;
import entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

     public UserServiceImpl(UserDao userDao) {
         this.userDao = userDao;
     }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User read(long id) {
        return userDao.read(id);
    }

    @Override
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
