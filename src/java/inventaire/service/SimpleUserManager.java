package inventaire.service;

import inventaire.domain.User;
import inventaire.repository.UserDao;
import java.util.List;

public class SimpleUserManager implements UserManager {
    
    private UserDao userDao;
    
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User authenticate(String login,String pass) {
        return userDao.authenticate(login,pass) ;
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
}
