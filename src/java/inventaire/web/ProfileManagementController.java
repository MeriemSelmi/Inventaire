package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserManager;
import inventaire.service.UserUpdate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;


public class ProfileManagementController extends MultiActionController{
    
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    private Map<String, Object> services;
    
    public ModelAndView manageProfile(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userRoles", User.USER_ROLES);
        
        services = new HashMap<String, Object>();
        services.put("userupdate", new UserUpdate());
        
        logger.info("ProfileManagementController: returning the profile management view");
        return new ModelAndView("profilemanagement", model).addAllObjects(services);
    }    
    
    public ModelAndView updateProfile(HttpServletRequest request, HttpServletResponse response, UserUpdate userUpdate)
            throws Exception {
        
        User user = userUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));

        logger.info("ProfileManagementController: trying to update user");
        try {
            userManager.update(user);
            request.getSession().setAttribute("loggedUser", user);
        } catch (Exception e) {
            //login duplicated
            return new ModelAndView(new InternalResourceView("profilemanagement.htm"));
        }

        return new ModelAndView(new RedirectView("profilemanagement.htm"));
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().invalidate();
        return new ModelAndView(new RedirectView("/authentication.htm", true));
    }
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
