package inventory.web;

import inventory.domain.Product;
import inventory.service.product.ProductAdd;
import inventory.service.product.ProductFind;
import inventory.service.product.ProductManager;
import inventory.service.product.ProductUpdate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "productmanagement.htm", method = RequestMethod.GET)
    public ModelAndView manageProducts() throws Exception {
        logger.info("ProductManagementController: returning list products view");
        return new ModelAndView("productmanagement");
    }

    @RequestMapping(value = "productadd.htm")
    public ModelAndView addProduct(HttpServletRequest req, @ModelAttribute("productadd") @Valid ProductAdd productAdd, BindingResult result) throws Exception {
        Product product = productAdd.getProduct();
        if (result.hasErrors()) {
            System.out.println("Error Handling : There's an error!!! ");
            req.setAttribute("openAddForm", "open");
            req.setAttribute("errorProduct", product.getId());
            return manageProducts();
        }
        try {
            productManager.addProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm", true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return manageProducts();
        }
    }

    @RequestMapping(value = "productupdate")
    public ModelAndView updateProduct(HttpServletRequest req, @ModelAttribute("productupdate") @Valid ProductUpdate productUpdate, BindingResult result) throws Exception {
        Product product = productUpdate.getProduct();
        String OriginalNameProduct = req.getParameter("updateName");
        product.setId(Integer.parseInt(req.getParameter("id")));

        if (result.hasErrors()) {
            System.out.println("Error Handling : ");
            req.setAttribute("nameErrorProduct", OriginalNameProduct);
            req.setAttribute("errorProduct", product.getId());
            return manageProducts();
        }

        logger.info("ProductManagementController: trying to update product");
        try {
            productManager.updateProduct(product);
            return new ModelAndView(new RedirectView("productmanagement.htm", true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return manageProducts();
        }
    }

    @RequestMapping(value = "productdelete", method = RequestMethod.GET)
    public ModelAndView deleteProduct(HttpServletRequest req) throws Exception {
        logger.info("ProductManagementController: trying to delete product");
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productManager.deleteProduct(id);
            return new ModelAndView(new RedirectView("productmanagement.htm", true));
        } catch (Exception e) {
            req.setAttribute("exception", e);
            return manageProducts();
        }
    }

    @RequestMapping(value = "productfind", method = RequestMethod.POST)
    public ModelAndView findProducts(HttpServletRequest req, @ModelAttribute("productfind") @Valid ProductFind productFind, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return new ModelAndView(new RedirectView("productmanagement.htm"));
        }

        String key = productFind.getKey();
        List<Product> products = (List<Product>) productManager.findProducts(key);
        logger.info("ProductManagementController: returning the product management view");
        return manageProducts().addObject("products", products);
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

    @ModelAttribute("products")
    private List<Product> listProducts() throws Exception {
        List<Product> products = productManager.listProducts();
        return products;
    }
}