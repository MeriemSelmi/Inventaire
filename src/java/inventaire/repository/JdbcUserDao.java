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
public class JdbcUserDao implements UserDao{

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
    public List<User> getUsers() throws Exception{
        logger.info("JdbcUserDao: Getting list of all users");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.getTransaction().commit();
        
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database");
    }

    @Override
    public void update(User user) throws Exception{
        logger.info("JdbcUserDao: Updating user...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        try{
            session.update(user);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            session.clear();
            throw new Exception("JdbcUserDao: The login already exists in the database");
        }
    }

    @Override
    public void add(User user) throws Exception {
        logger.info("JdbcUserDao: Adding user...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        try{
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            session.clear();
            throw new Exception("JdbcUserDao: The login already exists in the database");
        }
    }

    @Override
    public void delete(User user) throws Exception{
        logger.info("JdbcUserDao: Deleting user...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("DELETE User WHERE id=:id")
                .setParameter("id", user.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> findUsers(String keyword) throws Exception{
        logger.info("JdbcUserDao: Getting list of all users corresponding to " + keyword);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM User WHERE lastname LIKE :keyword OR firstname LIKE :keyword OR email LIKE :keyword OR login LIKE :keyword OR  role LIKE :keyword")
                .setParameter("keyword", "%"+keyword+"%");
        List<User> users = query.list();
        session.getTransaction().commit();
        if(users.size() > 0)
            return users;
        else
            throw new Exception("JdbcUserDao: No users found in the database corresponding to " + keyword);
    }

    @Override
    public User getUserById(int id) {
        logger.info("JdbcUserDao: Getting user with id=" + id);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM User WHERE id=:id")
                .setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    
}
