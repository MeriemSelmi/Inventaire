package inventaire.web;

import inventaire.domain.User;
import inventaire.service.ProfileUpdate;
import inventaire.service.UserManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/profile")
public class ProfileManagementController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private UserManager userManager;

    @RequestMapping(value="profilemanagement.htm",method = RequestMethod.GET)
    public ModelAndView manageProfile(ModelMap model) {
        model.put("profileupdate", new ProfileUpdate());
        return new ModelAndView("profilemanagement");
    }

    @RequestMapping(value="profileupdate.htm",method = RequestMethod.POST)
    public ModelAndView updateProfile(
            HttpServletRequest request, @ModelAttribute("profileupdate") @Valid ProfileUpdate profileUpdate,
            BindingResult result) {

        if (result.hasErrors()) {
            return manageProfile(new ModelMap());
        }
        
        User user = profileUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));
        try {
            userManager.update(user);
            request.getSession().setAttribute("loggedUser", user);
            return new ModelAndView(new RedirectView("/profile/profilemanagement.htm", true));
        } catch (Exception e) {
            result.rejectValue("error", "error.update.failed");
            return manageProfile(new ModelMap());
        }
    }

    @RequestMapping(value="logout.htm", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView(new RedirectView("/authentication.htm", true));
    }
    
}
