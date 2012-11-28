package inventaire.service;

import inventaire.domain.User;
import java.io.Serializable;
import java.util.List;

public interface UserManager extends Serializable {


    public List<User> getUsers();
    public  User authenticate(String login,String pass);
    public void update(int id, String name, String firstName, String mail, String telephone, String address, String login, String pass, String type);
    public void add(String name, String firstName, String mail, String telephone, String address, String login, String pass, String type);
    public void delete(int id);
    public List<User> findUsers(String keyword);
}
