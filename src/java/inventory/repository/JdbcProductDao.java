package inventory.repository;

import inventory.domain.Product;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

public class JdbcProductDao implements ProductDao {

    protected final Log logger = LogFactory.getLog(getClass());
    private Session session;
    private Query query;

    @Override
    public List<Product> list() throws Exception {
        logger.info("JdbcProductDao: Getting list of all products");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM Product");
        List<Product> products = query.list();
        session.getTransaction().commit();
        
        if (products.size() > 0) {
            return products;
        } else {
            throw new Exception("JdbcUserDao: No products found in the database");
        }
    }

    @Override
    public void add(Product product) throws Exception {
        logger.info("JDBCProductDao: Adding a product ... ");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback(); //Annuler la transaction
            session.clear();
        }
    }

    @Override
    public void update(Product product) throws Exception {
        logger.info("JdbcProductDao: Updating a product");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        logger.info("JdbcUserDao: Deleting product...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            query = session.createQuery("DELETE Product WHERE id=:id")
                    .setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.clear();
        }
    }

    @Override
    public List<Product> find(String key) throws Exception {
        logger.info("JdbcUserDao: finding products...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            query = session.createQuery("FROM Product WHERE name LIKE :key OR description LIKE :key OR supplier LIKE :key")
                    .setParameter("key", "%" + key + "%");
            List<Product> products = query.list();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            throw e;
        }

    }
}
