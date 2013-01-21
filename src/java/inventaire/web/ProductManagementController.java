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
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Balkis
 */
@Controller
@RequestMapping("/product")
public class ProductManagementController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ProductManager productManager;

    @RequestMapping(value = "/productmanagement.htm", method = RequestMethod.GET)
    public ModelAndView manageProducts(ModelMap model) throws Exception {
        logger.info("ProductManagementController: returning list products view");
        model.put("products", this.listProducts());
        model.put("productupdate", new ProductUpdate());
        model.put("productadd", new ProductAdd());
        model.put("productfind", new ProductFind());

        logger.info("ProductManagementController: returning the product management view");
        return new ModelAndView("productmanagement");
    }

    private List<Product> listProducts() throws Exception {
        List<Product> products = productManager.listProducts();
        return products;
    }
    
    @RequestMapping(value = "/productadd.htm", method = RequestMethod.POST)
    public ModelAndView addProduct(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("productadd") @Valid ProductAdd productAdd, BindingResult result) throws Exception {

        Product product = productAdd.getProduct();

        if (result.hasErrors()) {
            System.out.println("Error Handling : There's an error!!! ");
            /*Map<String,String> listErrors = new HashMap();                        
             List<FieldError> list = result.getFieldErrors();
            
           
             for(int i=0;i<list.size();i++){          
             listErrors.put(list.get(i).getField(),list.get(i).getDefaultMessage());
             }
             */
            return new ModelAndView("productmanagement");
        }

        try {
            productManager.addProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm"));

        }
    }

    /* public void validateAdd(Object command,HttpServletRequest req) throws Exception {
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
     }*/
    @RequestMapping(value = "/productupdate", method = RequestMethod.POST)
    public ModelAndView updateProduct(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("productupdate") @Valid ProductUpdate productUpdate, BindingResult result) throws Exception {

        Product product = productUpdate.getProduct();

        if (result.hasErrors()) {
            System.out.println("Error Handling : ");
            Map<String, String> listErrors = new HashMap();
            List<FieldError> list = result.getFieldErrors();


            for (int i = 0; i < list.size(); i++) {
                listErrors.put(list.get(i).getField() + "update", list.get(i).getDefaultMessage());
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

    /*
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
     }*/
    @RequestMapping(value = "/productdelete", method = RequestMethod.POST)
    public ModelAndView deleteProduct(HttpServletRequest req, HttpServletResponse res, BindingResult result) throws Exception {
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

    @RequestMapping(value = "/productfind", method = RequestMethod.POST)
    public ModelAndView findProducts(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("productfind") @Valid ProductFind productFind, BindingResult result) throws Exception {


        if (result.hasErrors()) {
            return new ModelAndView("productmanagement", "errorFind", result.getFieldError().getDefaultMessage());
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
    /*  
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
     */
}
