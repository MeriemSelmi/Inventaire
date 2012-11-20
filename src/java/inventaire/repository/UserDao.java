/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.repository;

import inventaire.domain.User;
import java.util.List;

/**
 *
 * @author Meriem
 */
public interface UserDao {
    
    public User authenticate(String login,String pass);
    public List<User> getUsers();
    
}
