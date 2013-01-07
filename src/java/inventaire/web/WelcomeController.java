package inventaire.web;

import inventaire.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class WelcomeController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = (User)request.getSession().getAttribute("loggedUser");
        if (user == null) {
            return new ModelAndView(new RedirectView("authentication.htm"));
        } else {
            if(user.getRole().equals(User.USER_ROLE_ADMINISTRATOR)){
               logger.info("WelcomeController: returning welcome view");
               return new ModelAndView("welcome"); 
            }else{
               logger.info("WelcomeController: returning welcome view");
               return new ModelAndView("welcome");
            }
            
        }
    }
}
