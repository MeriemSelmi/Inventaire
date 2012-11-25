package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserAlteration;
import inventaire.service.UserManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Meriem
 */
public class UserManagementController extends MultiActionController {
    
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    private List<User> users;
    
    public ModelAndView listUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("UserManagementController: getting all users in the database");
        users = userManager.getUsers();
        
        logger.info("UserManagementController: returning the user management view");
        request.setAttribute("users", users);
        
        return new ModelAndView("usermanagement", "userTypes", User.getUserTypes()).addObject("userupdate", new UserAlteration());
    }
    
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, UserAlteration userAlteration)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name= userAlteration.getName();
        String firstName= userAlteration.getFirstName();
        String mail= userAlteration.getMail();
        String telephone= userAlteration.getTelephone();
        String address= userAlteration.getAddress();
        String login= userAlteration.getLogin();
        String pass= userAlteration.getPass();
        String type= userAlteration.getType();
        
        logger.info("UserManagementController: trying to alterate user");
        userManager.update(id, name, firstName, mail, telephone, address, login, pass, type);
       
        return new ModelAndView(new RedirectView("usermanagement.htm")).addObject("useralteration", userAlteration);
    }
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
