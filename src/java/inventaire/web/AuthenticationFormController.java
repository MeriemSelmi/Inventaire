package inventaire.web;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class AuthenticationFormController extends SimpleFormController {

    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;

    @Override
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        User user = ((Authentication) command).getUser();
        logger.info("AuthenticationFormController: trying to authenticate with login=" + user.getLogin() + " and password=" + user.getPassword());

        try {
            user = userManager.authenticate(user);
            request.getSession(true).setAttribute("loggedUser", user);
            logger.info("AuthenticationFormController: authentication succeeded. Returning from authentication form view to " + getSuccessView());
            return new ModelAndView(new RedirectView(getSuccessView()));

        } catch (Exception e) {
            errors.rejectValue("error", "error.authentication.failed");
            logger.info("AuthenticationFormController: authentication failed. Returning from authentication form view to " + getFormView());
            return showForm(request, response, errors);
        }
    }

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

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
