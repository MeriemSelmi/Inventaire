package inventory.repository;

import inventory.domain.User;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

public class JdbcUserDao implements UserDao{

    protected final Log logger = LogFactory.getLog(getClass());
    private Session session;
    private Query query;
    
    @Override
    public List<User> list() throws Exception{
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
    public User authenticate(User user) throws Exception{
        logger.info("JdbcUserDao: Authenticating...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM User WHERE login=:login AND password=:password")
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword());
        user=null;
        user = (User) query.uniqueResult();
        session.getTransaction().commit();
        
        if(user != null)
            return user;
        else
            throw new Exception("JdbcUserDao: Authenticating failed. No such user with login="+user.getLogin()+" and password="+user.getPassword());
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
    public void update(User user) throws Exception{
        logger.info("JdbcUserDao: Updating user...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        try{
            session.update(user);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            throw new Exception("JdbcUserDao: The login already exists in the database");
        }
    }
    
    @Override
    public void delete(User user){
        logger.info("JdbcUserDao: Deleting user...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("DELETE User WHERE id=:id")
                .setParameter("id", user.getId());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> find(String keyword) throws Exception{
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
}
