package inventaire.web.user;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class AuthenticationFormController extends SimpleFormController{
    
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;

    public ModelAndView onSubmit(Object command) throws ServletException {
        String login = ((Authentication) command).getLogin();
        String pass = ((Authentication) command).getPass();
        
        logger.info("AuthenticationFormController: trying to authenticate with login="+login +" and password="+ pass);
        User user = userManager.authenticate(login,pass);
        
        if(user==null){
            logger.info("AuthenticationFormController: authentication failed. Returning from authentication form view to " + getFormView());
            return new ModelAndView(new RedirectView(getFormView().concat(".htm")));
        }
        else{
            logger.info("AuthenticationFormController: authentication succeeded. Returning from authentication form view to " + getSuccessView());
            return new ModelAndView(new RedirectView(getSuccessView()));
        }
        
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Authentication auth = new Authentication();
        auth.setLogin("");
        auth.setPass("");
        return auth;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    
}
