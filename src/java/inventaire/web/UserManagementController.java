package inventaire.web;

import inventaire.service.UserManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Meriem
 */
public class UserManagementController implements Controller{

    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        logger.info("UserManagementController: returning usermanagement view");   
        
        return new ModelAndView("usermanagement"); 
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    
}
