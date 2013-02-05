package inventaire.web;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/authentication.htm")
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(ModelMap model) {
        model.put("authentication", new Authentication());
        return new ModelAndView("authentication");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(
            HttpSession session, @ModelAttribute("authentication") @Valid Authentication authentication,
            BindingResult result) {

        if (result.hasErrors()) {
            return showForm(new ModelMap());
        }
        
        User user = authentication.getUser();
        try {
            user = userManager.authenticate(user);
            session.setAttribute("loggedUser", user);
            logger.info("AuthenticationController: authentication succeeded. Heading to welcome view");
            return new ModelAndView(new RedirectView("/welcome.htm", true));

        } catch (Exception e) {
            result.rejectValue("error", "error.authentication.failed");
            logger.info("AuthenticationController: authentication failed.");
            return showForm(new ModelMap());
        }
    }
}
