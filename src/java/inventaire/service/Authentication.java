package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Authentication {
    protected final Log logger = LogFactory.getLog(getClass());
    private String login;
    private String password;
    
    public void setLogin(String login){ 
        this.login = login; 
        logger.info("Authentication: login set to "+ login);
    }
    
    public void setPassword(String password){ 
        this.password = password;
        logger.info("Authentication: password set to "+ password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
