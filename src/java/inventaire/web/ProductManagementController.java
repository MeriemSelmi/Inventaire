/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.Product;
import inventaire.service.ProductAdd;
import inventaire.service.ProductAddValidator;
import inventaire.service.ProductFind;
import inventaire.service.ProductFindValidator;
import inventaire.service.ProductManager;
import inventaire.service.ProductUpdate;
import inventaire.service.ProductUpdateValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
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
    BindingResult errors;

    


    public BindingResult getErrors() {
        return errors;
    }

    public void setErrors(BindingResult errors) {
        this.errors = errors;
    }

    @Override
    protected void bind(HttpServletRequest request, Object command) throws Exception {
        ServletRequestDataBinder binder = createBinder(request, command);
        binder.bind(request);
        errors = binder.getBindingResult();
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    private List<Product> listProducts() throws Exception {
        List<Product> products = productManager.listProducts();
        return products;
    }

    public ModelAndView manageProducts(HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("ProductManagementController: returning list products view");
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            List<Product> products = this.listProducts();
            model.put("products", products);
            model.put("productupdate", new ProductUpdate());
            model.put("productadd", new ProductAdd());
            model.put("productfind", new ProductFind());
           // model.put("command",new ProductAdd());
            
            

            
            logger.info("ProductManagementController: returning the product management view");
            return new ModelAndView("productmanagement", "model", model).addAllObjects(model);
        } catch (Exception e) {
            logger.info("ProductManagementController: returning the exception content");
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }

    }

    public ModelAndView addProduct(HttpServletRequest req, HttpServletResponse res, ProductAdd productAdd) throws Exception {
        
        validateAdd(productAdd,req);
        Product product = productAdd.getProduct();
        
        if (errors.hasErrors()) {
            System.out.println("Error Handling : ");            
            Map<String,String> listErrors = new HashMap();                        
            List<FieldError> list = errors.getFieldErrors();
            
           
            for(int i=0;i<list.size();i++){          
            listErrors.put(list.get(i).getField(),list.get(i).getDefaultMessage());
            }
            
            return new ModelAndView("productmanagement", "errors", listErrors);
        }

        try {
            productManager.addProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));

        }
    }

    public void validateAdd(Object command,HttpServletRequest req) throws Exception {
        Validator[] validators = getValidators();
            req.setAttribute("productupdate", new ProductUpdate());
            req.setAttribute("productadd", new ProductAdd());
            req.setAttribute("productfind", new ProductFind());
            
            List<Product> products = this.listProducts();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("products", products);
            req.setAttribute("model",model);
        if (validators != null) {
            
            for (int index = 0; index < validators.length; index++) {
                Validator validator = validators[index];
                if (validator instanceof ProductAddValidator) {
                    
                    if (((ProductAddValidator) validator).supports(command.getClass())) {
                        
                        ValidationUtils.invokeValidator(validators[index], command, errors);
                    }
                } else if (validator.supports(command.getClass())) {
                    
                    ValidationUtils.invokeValidator(validators[index], command, errors);
                }
            }
        }
    }

    public void saveError(HttpServletRequest request, String msg) {
        Map<String, String> errors = (HashMap) request.getAttribute("errors");
        if (errors== null) {
            errors =new HashMap();
        }
        errors.put("erreur",msg);
        request.setAttribute("errors", errors);
    }

    

    public ModelAndView updateProduct(HttpServletRequest req, HttpServletResponse res, ProductUpdate productUpdate) throws Exception{
        
        
        validateUpdate(productUpdate,req);
        Product product = productUpdate.getProduct();
        
        if (errors.hasErrors()) {
            System.out.println("Error Handling : ");            
            Map<String,String> listErrors = new HashMap();                        
            List<FieldError> list = errors.getFieldErrors();
            
           
            for(int i=0;i<list.size();i++){          
            listErrors.put(list.get(i).getField()+"update",list.get(i).getDefaultMessage());
            }
            
            return new ModelAndView("productmanagement", "errors", listErrors);
        }

        
        product.setId(req.getParameter("id"));
        logger.info("ProductManagementController: trying to update product");
        try {
            productManager.UpdateProduct(product);
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }

        return new ModelAndView(new RedirectView("productmanagement.htm"));
    }
    
    
        public void validateUpdate(Object command,HttpServletRequest req) throws Exception {
        Validator[] validators = getValidators();
            req.setAttribute("productupdate", new ProductUpdate());
            req.setAttribute("productadd", new ProductAdd());
            req.setAttribute("productfind", new ProductFind());
            
            List<Product> products = this.listProducts();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("products", products);
            req.setAttribute("model",model);
        if (validators != null) {
            
            for (int index = 0; index < validators.length; index++) {
                Validator validator = validators[index];
                if (validator instanceof ProductUpdateValidator) {
                    
                    if (((ProductUpdateValidator) validator).supports(command.getClass())) {
                        
                        ValidationUtils.invokeValidator(validators[index], command, errors);
                    }
                } else if (validator.supports(command.getClass())) {
                    
                    ValidationUtils.invokeValidator(validators[index], command, errors);
                }
            }
        }
    }

    public ModelAndView deleteProduct(HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("UserManagementController: trying to delete product");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productManager.deleteProduct(id);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));

        }


    }

    public ModelAndView findProducts(HttpServletRequest req, HttpServletResponse res, ProductFind productFind) throws Exception {
        
        validateFind(productFind,req);        
        if (errors.hasErrors()) {                   
            return new ModelAndView("productmanagement", "errorFind", errors.getFieldError().getDefaultMessage());
        }

        
        
        String key = productFind.getKey();
        List<Product> products = (List<Product>) productManager.findProduct(key);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("products", products);
        model.put("productupdate", new ProductUpdate());
        model.put("productadd", new ProductAdd());
        model.put("productfind", new ProductFind());
        try {
            logger.info("ProductManagementController: returning the product management view");
            return new ModelAndView("productmanagement", "model", model).addAllObjects(model);
        } catch (Exception e) {
            model.put("exception", e);
            return new ModelAndView("productmanagement", "model", model).addAllObjects(model);
        }

    }
    
      public void validateFind(Object command,HttpServletRequest req) throws Exception {
        Validator[] validators = getValidators();
            req.setAttribute("productupdate", new ProductUpdate());
            req.setAttribute("productadd", new ProductAdd());
            req.setAttribute("productfind", new ProductFind());
            
            List<Product> products = this.listProducts();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("products", products);
            req.setAttribute("model",model);
            
        if (validators != null) {           
            for (int index = 0; index < validators.length; index++) {
                Validator validator = validators[index];
                if (validator instanceof ProductFindValidator) {
                    
                    if (((ProductFindValidator) validator).supports(command.getClass())) {
                        
                        ValidationUtils.invokeValidator(validators[index], command, errors);
                    }
                } else if (validator.supports(command.getClass())) {
                    
                    ValidationUtils.invokeValidator(validators[index], command, errors);
                }
            }
        }
    }
    
    
    
    
}
