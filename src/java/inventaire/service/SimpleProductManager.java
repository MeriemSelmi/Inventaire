package inventaire.service;

import java.util.List;

import inventaire.domain.Product;
import inventaire.repository.ProductDao;

public class SimpleProductManager implements ProductManager {

    // private List<Product> products;
    private ProductDao productDao;

    public List<Product> listProducts() {
        return productDao.list();
    }
    


    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product p) {
        productDao.add(p);
    }

    @Override
    public void deleteProduct(int id) {
        productDao.delete(id);
    }

    @Override
    public void UpdateProduct(int id,String name,String description,int quantity,float price,String supplier) {
        productDao.update(id, name, description, quantity, price, supplier);
    }

    @Override
    public Product findProduct(String critere) {
        return productDao.find(critere);
    }

}