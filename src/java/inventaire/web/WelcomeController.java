package inventaire.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WelcomeController implements Controller{

    protected final Log logger = LogFactory.getLog(getClass());
//    private UserManager userManager;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        logger.info("WelcomeController: returning welcome view");        
 //       Map<String, Object> model = new HashMap<String, Object>();
 //       model.put("user", this.userManager.authentifier(req.getParameter("login"),req.getParameter("password")));
 //       return new ModelAndView("welcome", "model", model);
        return new ModelAndView("welcome");
    }
    
//    public void setUserManager(UserManager userManager){
//        this.userManager = userManager;
//    }
    
    
    
}
