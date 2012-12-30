package inventaire.repository;

import inventaire.domain.User;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Meriem
 */
public class JdbcUserDaoo implements UserDao{

    protected final Log logger = LogFactory.getLog(getClass());
    private Session session;
    private Query query;
    
    @Override
    public User authenticate(String login, String password) throws Exception{
        logger.info("JdbcUserDao: Authenticating...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM User WHERE login=:login AND password=:password")
                .setParameter("login", login)
                .setParameter("password", password);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        
        if(user != null)
            return user;
        else
            throw new Exception("JdbcUserDao: Authenticating failed. No such user with login="+login+" and password="+password);
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
