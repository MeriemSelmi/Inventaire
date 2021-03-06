package inventory.repository;

import inventory.domain.Product;
import java.util.List;

public interface ProductDao {

    public List<Product> list() throws Exception;
    public void add(Product p) throws Exception;
    public void update(Product product) throws Exception;
    public void delete(int id) throws Exception;
    public List<Product> find(String key) throws Exception;
}