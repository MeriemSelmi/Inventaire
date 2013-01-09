package inventaire.service;

import inventaire.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Authentication {
    protected final Log logger = LogFactory.getLog(getClass());
    private String login;
    private String password;
    private String error;
    
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
    public User getUser(){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

}
