package inventaire.repository;

import inventaire.domain.Product;
import java.util.List;

public interface ProductDao {

    public List<Product> list() throws Exception;
    public void add(Product p);
    public void delete(int id);
    public void update(int id,String name,String description,int quantity,float price,String supplier);
    public Product find(String critere);
}