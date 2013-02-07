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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Balkis
 */
@Controller
@RequestMapping("/product")
public class ProductManagementController extends MultiActionController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ProductManager productManager;

    private List<Product> listProducts() throws Exception {
        List<Product> products = productManager.listProducts();
        return products;
    }

    @RequestMapping(value = "productmanagement.htm", method = RequestMethod.GET)
    public ModelAndView manageProducts(HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("ProductManagementController: returning list products view");
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            List<Product> products = this.listProducts();
            model.put("products", products);
            logger.info("ProductManagementController: returning the product management view");
            return new ModelAndView("productmanagement", "model", model).addAllObjects(model);
        } catch (Exception e) {
            logger.info("ProductManagementController: returning the exception content");
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        }

    }

    @RequestMapping(value = "productadd.htm")
    public ModelAndView addProduct(HttpServletRequest req, @ModelAttribute("productadd") @Valid ProductAdd productAdd, BindingResult result) throws Exception {
        Product product = productAdd.getProduct();
        if (result.hasErrors()) {
            System.out.println("Error Handling : There's an error!!! ");
            Map<String, Object> model = new HashMap<String, Object>();
            List<Product> products = this.listProducts();
            model.put("products", products);
            req.setAttribute("errorProduct",product.getId());
            return new ModelAndView("productmanagement","model",model).addAllObjects(model);
        }
        try {
            productManager.addProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        }
    }

    @RequestMapping(value = "productupdate")
    public ModelAndView updateProduct(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("productupdate") @Valid ProductUpdate productUpdate, BindingResult result) throws Exception {
        Product product = productUpdate.getProduct();
        product.setId(Integer.parseInt(req.getParameter("id")));
        if (result.hasErrors()) {
            System.out.println("Error Handling : ");
             Map<String, Object> model = new HashMap<String, Object>();
            List<Product> products = this.listProducts();
            model.put("products", products);
            req.setAttribute("errorProduct",product.getId());
            return new ModelAndView("productmanagement","model",model).addAllObjects(model);
        }

        
        logger.info("ProductManagementController: trying to update product");
        try {
            productManager.UpdateProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        }
    }

    @RequestMapping(value = "productdelete", method = RequestMethod.GET)
    public ModelAndView deleteProduct(HttpServletRequest req, HttpServletResponse res) throws Exception {
        logger.info("UserManagementController: trying to delete product");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productManager.deleteProduct(id);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return new ModelAndView(new RedirectView("productmanagement.htm",true));
        }
    }

    @RequestMapping(value = "productfind", method = RequestMethod.POST)
    public ModelAndView findProducts(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("productfind") @Valid ProductFind productFind, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }

        String key = productFind.getKey();
        List<Product> products = (List<Product>) productManager.findProduct(key);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("products", products);
        try {
            logger.info("ProductManagementController: returning the product management view");
            return new ModelAndView("productmanagement", "model", model).addAllObjects(model);
        } catch (Exception e) {
            model.put("exception", e);
            return new ModelAndView("productmanagement");
        }
    }

    @ModelAttribute("productfind")
    public ProductFind getProductFind() {
        return new ProductFind();
    }

    @ModelAttribute("productupdate")
    public ProductUpdate getProductUpdate() {
        return new ProductUpdate();
    }

    @ModelAttribute("productadd")
    public ProductAdd getProductAdd() {
        return new ProductAdd();
    }
}