package inventory.service.user;

import inventory.domain.User;
import inventory.repository.UserDao;
import java.util.LinkedList;
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
    public List<User> findUsers(String keyword) {
        List<User> users = new LinkedList<User>();
        try {
            users= userDao.findUsers(keyword);
        } catch (Exception e) {
        }
        return users;
    }

    public void setUserDao(UserDao userDao) throws Exception {
        this.userDao = userDao;
    }
}