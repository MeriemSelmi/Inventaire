package inventaire.service;

import java.util.List;

import inventaire.domain.Product;
import inventaire.repository.ProductDao;

public class SimpleProductManager implements ProductManager {

    // private List<Product> products;
    private ProductDao productDao;

  
    @Override
    public List<Product> listProducts() throws Exception{
        return productDao.list() ;
    }
    


    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product p) throws Exception{
        productDao.add(p);
    }

    @Override
    public void deleteProduct(int id) throws Exception{
        productDao.delete(id);
        
    }

    @Override
    public void UpdateProduct(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public List<Product> findProduct(String key) throws Exception{
        return productDao.find(key);
    }

}