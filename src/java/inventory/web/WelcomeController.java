package inventory.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome.htm")
public class WelcomeController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView showWelcome(){
        logger.info("WelcomeController: returning welcome view");
        return new ModelAndView("welcome");
    }
}