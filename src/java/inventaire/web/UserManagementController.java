package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserManager;
import java.util.ArrayList;
import java.util.List;
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
    private List<User> users;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        logger.info("UserManagementController: getting all users in the database");   
        users= userManager.getUsers();
        
        logger.info("UserManagementController: returning the user management view");
        request.setAttribute("users", users);
        return new ModelAndView("usermanagement"); 
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    
}
