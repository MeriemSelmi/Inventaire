package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserManager;
import inventaire.service.UserUpdate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/profile")
public class ProfileManagementController extends MultiActionController{
    
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private UserManager userManager;
    private Map<String, Object> services;
    
    @RequestMapping(value="/profilemanagement.htm", method = RequestMethod.GET)
    public ModelAndView manageProfile(ModelMap model){    
        model.put("userRoles", User.USER_ROLES);
        
        services= new HashMap<String, Object>();
        services.put("userupdate", new UserUpdate());
        model.addAllAttributes(services);
        
        logger.info("ProfileManagementController: returning the profile management view");
        return new ModelAndView("profilemanagement");
    }    
    
    @RequestMapping(value="/profileupdate.htm", method = RequestMethod.POST)
    public ModelAndView updateProfile( HttpServletRequest request, 
            @ModelAttribute("userupdate") @Valid UserUpdate userUpdate, BindingResult result){
        
        if (result.hasErrors()) {
            return manageProfile(new ModelMap());
        }
        User user = userUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));

        logger.info("ProfileManagementController: trying to update user");
        try {
            userManager.update(user);
            request.getSession().setAttribute("loggedUser", user);
        } catch (Exception e) {
            result.rejectValue("error", "error.update.failed");
        }
        return manageProfile(new ModelMap());
    }

    @RequestMapping(value="/logout.htm", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView(new RedirectView("/authentication.htm", true));
    }
}
