/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.repository;

import inventaire.domain.Product;
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
public class JdbcProductDao implements ProductDao{
    protected final Log logger = LogFactory.getLog(getClass());
    private Session session;
    private Query query;

    @Override
    public List<Product> list() throws Exception{
        logger.info("JdbcProductDao: Getting list of all products");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM Product");
        List<Product> products = query.list();
        session.getTransaction().commit();
        
        if(products.size() > 0)
            return products;
        else
            throw new Exception("JdbcUserDao: No products found in the database");
    }

    @Override
    public void add(Product product) throws Exception{
        try{
        logger.info("JDBCProductDao: Adding a product ... ");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            session.clear();
        }
    }

    @Override
    public void delete(int id) throws Exception{
        try{
        logger.info("JdbcUserDao: Deleting product...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("DELETE Product WHERE id=:id")
                .setParameter("id", String.valueOf(id));
        query.executeUpdate();
        session.getTransaction().commit();
        }
        catch(Exception e){
            
        }
    }

    @Override
    public void update(Product product) throws Exception{
        logger.info("JdbcProductDao: Updating a product");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        session.update(product);

        session.getTransaction().commit();
        session.clear();

        throw new Exception("JdbcProductDao: Product already exists in database.");
    }

    @Override
    public List<Product> find(String key) throws Exception{
        System.out.print("I'm in JDBC!");
       try{ logger.info("JdbcUserDao: finding products...");
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        query = session.createQuery("FROM Product WHERE name LIKE :key OR description LIKE :key OR quantity LIKE :key OR price LIKE :key OR supplier LIKE :key")
                .setParameter("key", "%"+key+"%");
        List<Product> products = query.list();
        session.getTransaction().commit();
        return products;}
       catch(Exception e){
           return null;
       }
    
}
}
