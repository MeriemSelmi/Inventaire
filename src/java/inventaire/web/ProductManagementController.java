/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.Product;
import inventaire.domain.User;
import inventaire.service.ProductAdd;
import inventaire.service.ProductFind;
import inventaire.service.ProductManager;
import inventaire.service.ProductUpdate;
import inventaire.service.UserAdd;
import inventaire.service.UserFind;
import inventaire.service.UserManager;
import inventaire.service.UserUpdate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Balkis
 */
public class ProductManagementController extends MultiActionController {
    
    
    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;
    private Map<String, Object> services;

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
    
        private List<Product> listProducts() throws Exception{        
        List<Product> products=productManager.listProducts();       
        return products;
}
    
    public ModelAndView manageProducts(HttpServletRequest request, HttpServletResponse response)throws Exception{
        logger.info("ProductManagementController: returning list products view");   
        Map<String, Object> model = new HashMap<String, Object>();
        List<Product> products =  this.listProducts();
        model.put("products",products);
        
        
        services = new HashMap<String, Object>();
        services.put("productupdate", new ProductUpdate());
        services.put("productadd", new ProductAdd());
        services.put("productfind", new ProductFind());
        

        logger.info("ProductManagementController: returning the product management view");
        return new ModelAndView("productmanagement","model", model).addAllObjects(services);
        
        
    }
    

    
    public ModelAndView addProduct(){return null;
}
    
    public ModelAndView UpdateProduct(HttpServletRequest req, HttpServletResponse res,ProductUpdate ProductUpdate){
        Product product = ProductUpdate.getProduct();
        product.setId(Integer.parseInt(req.getParameter("id")));

        logger.info("ProductManagementController: trying to update product");
        try {
            productManager.UpdateProduct(product);
        } catch (Exception e) {
            //login duplicated
            return new ModelAndView(new InternalResourceView("productmanagement.htm"));
        }

        return new ModelAndView(new RedirectView("productmanagement.htm"));
        
}
    
    public ModelAndView deleteProduct(){
        return null;
    }
    
    public ModelAndView findProduct(){return null;
    }
    
    
    
    
    
    
    
}
