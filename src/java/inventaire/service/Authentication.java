package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Authentication {
    protected final Log logger = LogFactory.getLog(getClass());
    private String login;
    private String pass;
    
    public void setLogin(String login){ 
        this.login = login; 
        logger.info("Authentication: login set to "+ login);
    }
    
    public void setPass(String pass){ 
        this.pass = pass;
        logger.info("Authentication: password set to "+ pass);
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
    
           
    
}
