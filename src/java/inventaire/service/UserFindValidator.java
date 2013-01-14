package inventaire.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserFindValidator implements Validator {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public boolean supports(Class clazz) {
        return UserFind.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        logger.info("UserFind Validator");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keyword", "error.empty", new Object[]{"Keyword"});
    }
}
