/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Balkis
 */
public class ProductFindValidator implements Validator{
    @Override
    public boolean supports(Class aClass) { //This method declares classes supported by the validator.
        return ProductFind.class.equals(aClass);
    }

    @Override
    public void validate(Object command, Errors errors) {
        ProductFind productFind = (ProductFind) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key", "field.required", "Required field");

        	
    }
    
    
}
