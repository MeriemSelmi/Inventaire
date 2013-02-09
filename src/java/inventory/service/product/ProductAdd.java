package inventory.service.product;

import inventory.domain.Product;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Balkis
 */
public class ProductAdd {
    @NotEmpty
    private String name;
    @NotEmpty(message="{product.label.name.error}")
    private String description;
    @NotEmpty(message="{product.label.name.error}")
    private String quantity;
    @NotEmpty(message="{product.label.name.error}")
    @Min(1)
    private String price;
    @NotEmpty(message="{product.label.name.error}")
    private String supplier;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    public Product getProduct(){
        return new Product(name,description,quantity,price,supplier);
    }
    
    
}
