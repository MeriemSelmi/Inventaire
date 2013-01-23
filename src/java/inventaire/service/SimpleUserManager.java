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
        User oldUser = userDao.getUserById(user.getId());
        setUserEmptyFields(user, oldUser);
        userDao.update(user);
    }

    private void setUserEmptyFields(User user, User oldUser){
        String[] fields = {"LastName", "FirstName", "Address", "Email", "Telephone", "Login", "Password", "Role"};
        try {
        for (String field : fields) {
            Method getter = User.class.getMethod("get" + field);
            Method setter = User.class.getMethod("set" + field, String.class);
            String oldValue = (String) getter.invoke(oldUser);
            String newValue = (String) getter.invoke(user);
            if (newValue.trim().isEmpty()) {
                setter.invoke(user, oldValue);
            }
        }
        }catch(Exception e){}
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
