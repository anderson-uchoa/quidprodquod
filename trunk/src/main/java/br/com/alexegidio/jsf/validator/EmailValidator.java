package br.com.alexegidio.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Esta classe efetua a validacao de um campo email
 * 
 * @author alexegidio@yahoo.com.br
 * @date 08/05/2010
 * @since version 1.0
 * @version 1.0
 * 
 */
public class EmailValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String enteredEmail = (String) value;
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(enteredEmail);

		// Check whether match is found
		boolean matchFound = m.matches();

		if (!matchFound) {
			throw new ValidatorException(new FacesMessage("Email inválido"));
		}

	}

}
