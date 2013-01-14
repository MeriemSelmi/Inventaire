package inventaire.web;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserManager;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/authentication.htm")
public class AuthenticationFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    private Map<String, Object> commands;
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(ModelMap model) {
        model.put("authentication", new Authentication());
        return new ModelAndView("authentication");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(
            @ModelAttribute("authentication") Authentication authentication,
            BindingResult result, SessionStatus status, HttpServletRequest request) {

        User user = authentication.getUser();
        try {
            user = userManager.authenticate(user);
            
            request.getSession(true).setAttribute("loggedUser", user);
            logger.info("AuthenticationFormController: authentication succeeded. Heading to welcome view");
            return new ModelAndView(new RedirectView("/welcome.htm", true));

        } catch (Exception e) {
            result.rejectValue("error", "error.authentication.failed");
            logger.info("AuthenticationFormController: authentication failed.");
            return new ModelAndView("authentication");
        }
    }
    /*
     public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
     Object command, BindException errors) throws Exception {
     User user = ((Authentication) command).getUser();
     logger.info("AuthenticationFormController: trying to authenticate with login=" + user.getLogin() + " and password=" + user.getPassword());

     try {
     user = userManager.authenticate(user);
     request.getSession(true).setAttribute("loggedUser", user);
     logger.info("AuthenticationFormController: authentication succeeded. Returning from authentication form view to ");
     return new ModelAndView(new RedirectView("/welcome.htm"));

     } catch (Exception e) {
     errors.rejectValue("error", "error.authentication.failed");
     logger.info("AuthenticationFormController: authentication failed. Returning from authentication form view to ");
     return new ModelAndView(new RedirectView("/authentication.htm"));
     }
     }
     */
    /*
     @Override
     protected Object formBackingObject(HttpServletRequest request) throws ServletException {
     Authentication authentication = new Authentication();
     authentication.setLogin("");
     authentication.setPassword("");
     return authentication;
     }

     public UserManager getUserManager() {
     return userManager;
     }
     */

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
