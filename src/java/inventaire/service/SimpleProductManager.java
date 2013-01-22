package inventaire.service;

import inventaire.domain.Product;
import inventaire.repository.ProductDao;
import java.util.List;

public class SimpleProductManager implements ProductManager {

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