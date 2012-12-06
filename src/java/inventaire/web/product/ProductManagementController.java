/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web.product;

import inventaire.service.ProductManager;
import inventaire.service.UserManager;
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
    
    public ModelAndView manageProducts(){
        return null;
    }
    
    public ModelAndView listProducts(){return null;
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
