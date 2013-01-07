package inventaire.repository;

import inventaire.domain.User;
import java.util.List;

/**
 *
 * @author Meriem
 */
public interface UserDao {
    
    public User authenticate(User user) throws Exception;
    public List<User> getUsers() throws Exception;
    public User getUserById(int id);
    public void update(User user) throws Exception;
    public void add(User user) throws Exception;
    public void delete(User user) throws Exception;
    public List<User> findUsers(String keyword) throws Exception;
}
