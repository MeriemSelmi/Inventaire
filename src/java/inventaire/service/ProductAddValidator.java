/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.service;

import inventaire.domain.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Balkis
 */
public class ProductAddValidator implements Validator {

    @Override
    public boolean supports(Class aClass) { //This method declares classes supported by the validator.
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object command, Errors errors) {
        ProductAdd productAdd = (ProductAdd) command;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "field.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supplier", "field.required", "Required field");
        
        if ( ! errors.hasFieldErrors("price")) {
			if (Integer.parseInt(productAdd.getPrice()) == 0)
				errors.rejectValue("price", "not_zero", "Can't be free!");
		}	
    }
    
    
    
}
