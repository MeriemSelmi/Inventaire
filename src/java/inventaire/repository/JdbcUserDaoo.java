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
public class JdbcUserDaoo implements UserDao{

    @Override
    public User authenticate(String login, String pass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(int id, String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(String name, String firstName, String mail, String telephone, String address, String login, String pass, String type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findUsers(String keyword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
