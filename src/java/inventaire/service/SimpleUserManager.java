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

    @Override
    public void update(int id, String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        userDao.update( id, name, firstName, mail, telephone, address, login, pass, type);
    }

    @Override
    public void add(String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        userDao.add(name, firstName, mail, telephone, address, login, pass, type);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
    
    public List<User> findUsers(String keyword){
        return userDao.findUsers(keyword);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
