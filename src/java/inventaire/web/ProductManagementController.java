/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.Product;
import inventaire.service.ProductAdd;
import inventaire.service.ProductFind;
import inventaire.service.ProductManager;
import inventaire.service.ProductUpdate;
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
    
    public ModelAndView manageProducts(HttpServletRequest req, HttpServletResponse res)throws Exception{
        logger.info("ProductManagementController: returning list products view");   
        Map<String, Object> model = new HashMap<String, Object>();
        try{
        List<Product> products =  this.listProducts();
        model.put("products",products);             
        model.put("productupdate", new ProductUpdate());
        model.put("productadd", new ProductAdd());
        model.put("productfind", new ProductFind());             
        logger.info("ProductManagementController: returning the product management view");
        return new ModelAndView("productmanagement","model", model).addAllObjects(model);
        }
        
        catch(Exception e){
            logger.info("ProductManagementController: returning the exception content");
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }   
        
    }
    

    
    public ModelAndView addProduct(HttpServletRequest req,HttpServletResponse res,ProductAdd productAdd) throws Exception{
        Product product = productAdd.getProduct(); 
        try{             
        productManager.addProduct(product);       
        return new ModelAndView(new RedirectView("productmanagement.htm"));
        }
        catch(Exception e){
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
            
        }
        }
    
    public ModelAndView updateProduct(HttpServletRequest req, HttpServletResponse res,ProductUpdate ProductUpdate){
        Product product = ProductUpdate.getProduct();
        product.setId(req.getParameter("id"));
        logger.info("ProductManagementController: trying to update product");
        try {
            productManager.UpdateProduct(product);
        } 
        catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }

        return new ModelAndView(new RedirectView("productmanagement.htm"));        
}
    
    public ModelAndView deleteProduct(HttpServletRequest req,HttpServletResponse res) throws Exception{      
        logger.info("UserManagementController: trying to delete product");       
        try{
        int id = Integer.parseInt(req.getParameter("id"));       
        productManager.deleteProduct(id);
        return new ModelAndView(new RedirectView("productmanagement.htm"));
        }
        catch(Exception e){
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
            
        }
        
        
    }
    
    public ModelAndView findProducts(HttpServletRequest req,HttpServletResponse res,ProductFind productFind) throws Exception{       
        String key = productFind.getKey();
        List<Product> products = (List<Product>) productManager.findProduct(key);
        Map<String, Object> model = new HashMap<String, Object>();       
        model.put("products",products);             
        model.put("productupdate", new ProductUpdate());
        model.put("productadd", new ProductAdd());
        model.put("productfind", new ProductFind());
        try{       
            logger.info("ProductManagementController: returning the product management view");
            return new ModelAndView("productmanagement","model", model).addAllObjects(model);
        }
        
        catch(Exception e){
            model.put("exception", e);
            return new ModelAndView("productmanagement","model", model).addAllObjects(model);
        }   

    }
}
    
    
    
    
    
    
    

