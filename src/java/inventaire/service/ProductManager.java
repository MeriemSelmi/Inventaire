package inventaire.service;

import inventaire.domain.Product;
import java.io.Serializable;
import java.util.List;

public interface ProductManager extends Serializable {
    public List<Product> listProducts() throws Exception;
    public void addProduct(Product p) throws Exception;
    public void deleteProduct(int id) throws Exception;
    public void UpdateProduct(Product product) throws Exception;
    public List<Product> findProduct(String critere) throws Exception;
}