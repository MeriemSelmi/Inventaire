package inventory.repository;

import inventory.domain.User;
import java.util.List;

public interface UserDao {
    
    public List<User> list() throws Exception;
    public User authenticate(User user) throws Exception;
    public void add(User user) throws Exception;
    public void update(User user) throws Exception;
    public void delete(User user);
    public List<User> find(String keyword) throws Exception;
}
