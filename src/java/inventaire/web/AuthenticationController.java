package inventaire.web;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserManager;
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
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(ModelMap model) {
        model.put("authentication", new Authentication());
        return new ModelAndView("authentication");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(
            @ModelAttribute("authentication") @Valid Authentication authentication,
            BindingResult result, SessionStatus status, HttpServletRequest request) {

        if (result.hasErrors()) {
            return new ModelAndView("authentication");
        }
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

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
