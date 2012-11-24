/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Meriem
 */
public class UserAlterationValidator implements Validator{
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UserAlteration.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserAlteration userAlteration = (UserAlteration) o;
        if (userAlteration == null) {
            errors.rejectValue("login", "error.not-specified", new Object[]{"Login"}, "Value required.");
            errors.rejectValue("pass", "error.not-specified",new Object[]{"Password"}, "Value required.");
        } 
        else {
            logger.info("UserAlteration with " + userAlteration + ": " + userAlteration.getLogin() + " " + userAlteration.getPass());
            
            if (userAlteration.getLogin().trim().length() == 0) {
                errors.rejectValue("login", "error.empty", new Object[]{"Login"}, "Login must be entered.");
            }
            if (userAlteration.getPass().trim().length() == 0) {
                errors.rejectValue("pass", "error.empty",new Object[]{"Password"}, "Password must be entered.");
            }

        }
    }
    
}
