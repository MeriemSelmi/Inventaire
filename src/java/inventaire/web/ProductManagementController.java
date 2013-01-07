/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.Product;
import inventaire.domain.User;
import inventaire.service.ProductManager;
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

/**
 *
 * @author Balkis
 */
public class ProductManagementController extends MultiActionController {
    
    
    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
    
        private List<Product> listProducts(){        
        List<Product> products=productManager.listProducts();       
        return products;
}
    
    public ModelAndView manageProducts(HttpServletRequest request, HttpServletResponse response)throws Exception{
        logger.info("ProductManagementController: returning list products view");   
        Map<String, Object> model = new HashMap<String, Object>();
        List<Product> products =  this.listProducts();
        model.put("products",products);
        
        return new ModelAndView("productmanagement", "model", model);       
    }
    

    
    public ModelAndView addProduct(){return null;
}
    
    public ModelAndView UpdateProduct(){return null;
}
    
    public ModelAndView deleteProduct(){
        return null;
    }
    
    public ModelAndView findProduct(){return null;
    }
    
    
    
    
    
    
    
}
