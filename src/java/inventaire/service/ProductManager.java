package inventaire.service;

import inventaire.domain.Product;
import java.io.Serializable;
import java.util.List;

public interface ProductManager extends Serializable {
    public List<Product> listProducts() throws Exception;
    public void addProduct(Product p);
    public void deleteProduct(int id);
    public void UpdateProduct(int id,String name,String description,int quantity,float price,String supplier);
    public Product findProduct(String critere);
}