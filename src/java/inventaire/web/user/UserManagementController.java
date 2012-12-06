package inventaire.web.user;

import inventaire.domain.User;
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
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Meriem
 */
public class UserManagementController extends MultiActionController {
    
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    private Map<String, Object> services;
    
    public ModelAndView manageUsers(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        Map<String, Object> model = new HashMap<String, Object>();
        
        List<User> users = listUsers();
        model.put("users", users);
        
        services = new HashMap<String, Object>();
        services.put("userupdate", new UserUpdate());
        services.put("useradd", new UserAdd());
        services.put("userfind", new UserFind());
        model.put("userTypes", User.getUserTypes());
        
        logger.info("UserManagementController: returning the user management view");
        return new ModelAndView("usermanagement", model).addAllObjects(services);
    }
    
    private List<User> listUsers()
            throws Exception {
        logger.info("UserManagementController: getting all users in the database");
        List<User> users = userManager.getUsers();
        return users;
    }
    
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, UserUpdate userUpdate)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name= userUpdate.getName();
        String firstName= userUpdate.getFirstName();
        String mail= userUpdate.getMail();
        String telephone= userUpdate.getTelephone();
        String address= userUpdate.getAddress();
        String login= userUpdate.getLogin();
        String pass= userUpdate.getPass();
        String type= userUpdate.getType();
        
        logger.info("UserManagementController: trying to update user");
        userManager.update(id, name, firstName, mail, telephone, address, login, pass, type);
       
        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }
    
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, UserAdd userAdd)
            throws Exception {
        String name= userAdd.getName();
        String firstName= userAdd.getFirstName();
        String mail= userAdd.getMail();
        String telephone= userAdd.getTelephone();
        String address= userAdd.getAddress();
        String login= userAdd.getLogin();
        String pass= userAdd.getPass();
        String type= userAdd.getType();
        
        logger.info("UserManagementController: trying to add user");
        userManager.add(name, firstName, mail, telephone, address, login, pass, type);
       
        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }
    
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        logger.info("UserManagementController: trying to delete user");
        userManager.delete(id);
        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }
    
    public ModelAndView findUsers(HttpServletRequest request, HttpServletResponse response, UserFind userfind){
        String keyword = userfind.getKeyword();
        
        logger.info("UserManagementController: getting all users in the database corresponding to "+ keyword);
        List<User> usersFound = userManager.findUsers(keyword);
        
        logger.info("UserManagementController: returning the user management view");
        request.setAttribute("usersFound", usersFound);
        return new ModelAndView(new InternalResourceView("usermanagement.htm"));
    }
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
