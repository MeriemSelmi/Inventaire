package inventaire.service;

import inventaire.domain.Product;
import inventaire.domain.User;
import java.io.Serializable;
import java.util.List;

public interface UserManager extends Serializable {


    public List<User> getUsers();
    public  User authenticate(String login,String pass);
    public boolean alter(String name, String firstName, String mail, String telephone, String address, String login, String pass, String type);
}
