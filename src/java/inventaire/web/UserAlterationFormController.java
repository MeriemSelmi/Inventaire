/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.User;
import inventaire.service.Authentication;
import inventaire.service.UserAlteration;
import inventaire.service.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Meriem
 */
public class UserAlterationFormController extends SimpleFormController{
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;

    @Override
    public ModelAndView onSubmit(Object command) throws ServletException {
        String login = ((UserAlteration) command).getLogin();
        String pass = ((UserAlteration) command).getPass();
        
        logger.info("UserAlterationFormController: trying to alter with login="+login +" and password="+ pass);
        boolean alterSucceeded = userManager.alter("", "", "", "", "", login, pass, "");
        
        if(!alterSucceeded){
            logger.info("AuthenticationFormController: alteration failed. Returning from user management view to " + getFormView());   
        }
        else{
            logger.info("AuthenticationFormController: authentication succeeded. Returning from authentication form view to " + getSuccessView());
        }
        return new ModelAndView(new RedirectView(getFormView().concat(".htm")));
        
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        UserAlteration userAlteration = new UserAlteration();
        userAlteration.setLogin("${user.login}");
        userAlteration.setPass("${user.pass}");
        return userAlteration;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }    
}
