package inventaire.service;

import inventaire.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserUpdateValidator implements Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean supports(Class clazz) {
        return UserUpdate.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("UserUpdate Validator");
        
        UserUpdate userUpdate = (UserUpdate) o;
        if(userUpdate.getEmail().trim().length()!=0 && !userUpdate.getEmail().matches("/^([a-z0-9_.-]+)@([da-z.-]+).([a-z.]{2,6})$/"))
            errors.rejectValue("email", "error.invalid", new Object[]{"Email"}, "Email is invalid");
        if(userUpdate.getTelephone().trim().length()!=0 && !userUpdate.getTelephone().matches("^[+]?[0-9]{8,}$"))
            errors.rejectValue("telephone", "error.invalid", new Object[]{"Telephone"}, "Telephone is invalid");
        if(userUpdate.getTelephone().trim().length()!=0 && !User.USER_ROLES.containsValue(userUpdate.getRole()))
            errors.rejectValue("role", "error.invalid", new Object[]{"Role"}, "Role is invalid");
    }
}
