/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 *
 * @author furkankykc
 */


@FacesValidator("saatValidator")
public class SaatValidator implements Validator{

	
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		String str = value.toString();
		if((str.length())>2){
			
			FacesMessage msg = 
				new FacesMessage("Saat validation failed.", 
						"Invalid Saat format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}


}
