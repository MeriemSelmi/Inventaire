/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.service;

import inventaire.domain.Product;

/**
 *
 * @author Balkis
 */
public class ProductAdd {
    private String name;
    private String description;
    private String quantity;
    private String price;
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
