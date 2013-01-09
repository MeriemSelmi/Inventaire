package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AuthenticationValidator implements Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean supports(Class clazz) {
        return Authentication.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("Authentication Validator");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.empty", new Object[]{"Login"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty", new Object[]{"Password"});
    }
}
