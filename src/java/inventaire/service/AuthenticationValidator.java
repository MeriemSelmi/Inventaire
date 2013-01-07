package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthenticationValidator implements Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean supports(Class clazz) {
        return Authentication.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Authentication auth = (Authentication) o;
        if (auth == null) {
            errors.rejectValue("login", "error.not-specified", new Object[]{"Login"}, "Value required.");
            errors.rejectValue("password", "error.not-specified",new Object[]{"Password"}, "Value required.");
        } 
        else {
            logger.info("Authenticating with " + auth + ": " + auth.getLogin() + " " + auth.getPassword());
            
            if (auth.getLogin().trim().length() == 0) {
                errors.rejectValue("login", "error.empty", new Object[]{"Login"}, "Login must be entered.");
            }
            if (auth.getPassword().trim().length() == 0) {
                errors.rejectValue("password", "error.empty",new Object[]{"Password"}, "Password must be entered.");
            }

        }
    }
}
