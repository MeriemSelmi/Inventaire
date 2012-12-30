package inventaire.service;

import inventaire.domain.User;
import java.io.Serializable;
import java.util.List;

public interface UserManager extends Serializable {


    public List<User> getUsers() throws Exception;
    public  User authenticate(String login,String password) throws Exception;
    public void update(User user) throws Exception;
    public void add(User user) throws Exception;
    public void delete(User user) throws Exception;
    public List<User> findUsers(String keyword) throws Exception;
}
