/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.repository;

import inventaire.domain.Product;
import java.util.List;

/**
 *
 * @author Meriem
 */
public class JdbcProductDao implements ProductDao{

    @Override
    public List<Product> list() {
        return null;
//        throw new UnsupportedOperationException("Not supported yet.");
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
