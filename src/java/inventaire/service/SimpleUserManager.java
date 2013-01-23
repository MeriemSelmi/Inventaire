package inventaire.service;

import inventaire.domain.User;
import inventaire.repository.UserDao;
import java.lang.reflect.Method;
import java.util.List;

public class SimpleUserManager implements UserManager {

    private UserDao userDao;

    @Override
    public List<User> getUsers() throws Exception {
        return userDao.getUsers();
    }

    @Override
    public User authenticate(User user) throws Exception {
        return userDao.authenticate(user);
    }

    @Override
    public void update(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public void add(User user) throws Exception {
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
    
    @Override
    public List<User> findUsers(String keyword) throws Exception {
        return userDao.findUsers(keyword);
    }

    public void setUserDao(UserDao userDao) throws Exception {
        this.userDao = userDao;
    }
}
