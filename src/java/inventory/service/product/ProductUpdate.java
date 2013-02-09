package inventory.service.product;

import inventory.domain.Product;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Balkis
 */
public class ProductUpdate {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String quantity;
    @NotEmpty
    @Min(1)
    private String price;
    @NotEmpty
    private String supplier;

    public Product getProduct() {
        return new Product(name, description, quantity, price, supplier);
    }

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
}
