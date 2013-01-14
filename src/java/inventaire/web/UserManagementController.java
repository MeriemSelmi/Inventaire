package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserAdd;
import inventaire.service.UserFind;
import inventaire.service.UserFindValidator;
import inventaire.service.UserManager;
import inventaire.service.UserUpdate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestBindingException;
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
            throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();

        List<User> users = listUsers();
        model.put("users", users);

        services = new HashMap<String, Object>();
        services.put("userupdate", new UserUpdate());
        services.put("useradd", new UserAdd());
        services.put("userfind", new UserFind());
        model.put("userRoles", User.USER_ROLES);

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

        User user = userUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));

        logger.info("UserManagementController: trying to update user");
        try {
            userManager.update(user);
        } catch (Exception e) {
            //login duplicated
            return new ModelAndView(new InternalResourceView("usermanagement.htm"));
        }

        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }

    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, UserAdd userAdd)
            throws Exception {

        User user = userAdd.getUser();

        logger.info("UserManagementController: trying to add user");
        try {
            userManager.add(user);
        } catch (Exception e) {
            return new ModelAndView(new InternalResourceView("usermanagement.htm"));
            //login duplicated
        }

        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }

    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));

        logger.info("UserManagementController: trying to delete user");
        userManager.delete(user);

        return new ModelAndView(new RedirectView("usermanagement.htm"));
    }

    public ModelAndView findUsers(HttpServletRequest request, HttpServletResponse response, UserFind userfind) {

        String keyword = userfind.getKeyword();
        List<User> usersFound;
        logger.info("UserManagementController: getting all users in the database corresponding to " + keyword);

        try {
            usersFound = userManager.findUsers(keyword);
            logger.info("UserManagementController: returning the user management view");
            request.setAttribute("usersFound", usersFound);
        } catch (Exception ex) {
            //no users found
            return new ModelAndView(new InternalResourceView("usermanagement.htm"));
        }

        return new ModelAndView(new InternalResourceView("usermanagement.htm"));
    }

    public ModelAndView hanldeBindException(HttpServletRequest request, HttpServletResponse response, ServletRequestBindingException bindingException) {
        BindException bindException = (BindException) bindingException.getRootCause();
/*
        for (Object object : bindException.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;

                System.out.println(fieldError.getField());
            }

            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;

                System.out.println(objectError.getCode());
            }
        }
    */
        return new ModelAndView(new RedirectView("/user/usermanagement.htm", true)).addAllObjects(bindException.getModel()); 
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
}
