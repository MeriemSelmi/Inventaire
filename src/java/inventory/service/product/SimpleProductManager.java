package inventory.service.product;

import inventory.domain.Product;
import inventory.repository.ProductDao;
import java.util.List;

public class SimpleProductManager implements ProductManager {

    private ProductDao productDao;
  
    @Override
    public List<Product> listProducts() throws Exception{
        return productDao.list() ;
    }

    @Override
    public void addProduct(Product p) throws Exception{
        productDao.add(p);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(int id) throws Exception{
        productDao.delete(id);
    }
    
    @Override
    public List<Product> findProducts(String key) throws Exception{
        return productDao.find(key);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}