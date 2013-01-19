package inventaire.service;

import inventaire.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserAddValidator implements Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean supports(Class clazz) {
        return UserAdd.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("UserAdd Validator");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.empty", new Object[]{"First Name"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.empty", new Object[]{"Last Name"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.empty", new Object[]{"Login"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty", new Object[]{"Password"});
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "error.empty", new Object[]{"Role"});
        
        UserAdd userAdd = (UserAdd) o;
        if(userAdd.getEmail().trim().length()!=0 && !userAdd.getEmail().matches("/^([a-z0-9_.-]+)@([da-z.-]+).([a-z.]{2,6})$/"))
            errors.rejectValue("email", "error.invalid", new Object[]{"Email"}, "Email is invalid");
        if(userAdd.getTelephone().trim().length()!=0 && !userAdd.getTelephone().matches("^[+]?[0-9]{8,}$"))
            errors.rejectValue("telephone", "error.invalid", new Object[]{"Telephone"}, "Telephone is invalid");
        if(!User.USER_ROLES.containsValue(userAdd.getRole()))
            errors.rejectValue("role", "error.invalid", new Object[]{"Role"}, "Role is invalid");
    }
}
