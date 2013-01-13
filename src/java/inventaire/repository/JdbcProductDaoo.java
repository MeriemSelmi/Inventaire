package inventaire.repository;

import inventaire.domain.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcProductDaoo extends SimpleJdbcDaoSupport implements ProductDao {

    protected final Log logger = LogFactory.getLog(getClass());
    private static final String ADD_REQUEST = "INSERT INTO ROOT.PRODUCTS (\"name\", \"description\", \"quantity\", \"price\", \"supplier\") VALUES (?,?,?,?,?)";
    private static final String UPDATE_REQUEST = "UPDATE ROOT.PRODUCTS SET \"name\"=? , \"description\"=? , \"quantity\"=? , \"price\"=? , \"supplier\"=? WHERE \"id\"=?";
    private static final String DELETE_REQUEST = "DELETE FROM ROOT.PRODUCTS WHERE \"id\"=?;";
    private static final String FIND_REQUEST = "SELECT * FROM ROOT.PRODUCTS WHERE \"name\" LIKE ? OR \"description\" LIKE ? OR \"quantity\" LIKE ? OR \"price\" LIKE ? OR \"supplier\" LIKE ? ";
    private static final String LIST_REQUEST = "SELECT * FROM ROOT.PRODUCTS";


    @Override
    public List<Product> list() {
        logger.info("Getting products!");
        List<Product> products = getSimpleJdbcTemplate().query(LIST_REQUEST,new JdbcProductDaoo.ProductMapper());
      
        return products;
        
    }
    @Override
    public void add(Product p) {
        logger.info("Adding a product!");
        getSimpleJdbcTemplate().update(ADD_REQUEST,p.getName(),p.getDescription(),p.getQuantity(),p.getPrice(),p.getSupplier());
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting a product!");
        getSimpleJdbcTemplate().update(DELETE_REQUEST, id);
    }

    @Override
    public void update(Product product) {
        logger.info("Updating a product!");
        getSimpleJdbcTemplate().update(UPDATE_REQUEST, product.getName(),product.getDescription(),product.getQuantity(),product.getPrice(),product.getSupplier(),product.getId());
    }

    @Override
    public Product find(String critere) {
        logger.info("Finding a product!");
        List<Product> products = getSimpleJdbcTemplate().query(FIND_REQUEST, new ProductMapper());
        for (Product product : products) {
            return product;
        }
        return null;
    }

    private static class ProductMapper implements ParameterizedRowMapper<Product> {

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product prod = new Product();
            prod.setId(rs.getInt("id"));
            prod.setName(rs.getString("name"));
            prod.setDescription(rs.getString("description"));
   //         prod.setQuantity(rs.getInt("quantity"));
   //         prod.setPrice(rs.getFloat("price"));
            prod.setSupplier(rs.getString("supplier"));
            return prod;
        }
    }
}