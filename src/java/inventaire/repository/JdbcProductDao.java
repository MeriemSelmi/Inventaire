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
    public void add(Product p) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(int id, String name, String description, int quantity, float price, String supplier) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product find(String critere) {
        return null;
//      throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
