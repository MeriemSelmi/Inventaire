package inventaire.web;

import inventaire.service.UserManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AuthenticationController implements Controller{

    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        logger.info("AuthenticationController: returning authentication view");        
 //       Map<String, Object> model = new HashMap<String, Object>();
 //       model.put("user", this.userManager.authentifier(req.getParameter("login"),req.getParameter("password")));
 //       return new ModelAndView("welcome", "model", model);
        return new ModelAndView("welcome");
    }
    
    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }
    
    
    
}
