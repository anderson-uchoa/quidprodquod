package br.com.alexegidio.jsf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *  
 * @author alex Data: 04/11/2009
 */
public class EmailValidator implements Validator {

	/**
	 * Este metodo efetua a validacao do email
	 */
	public void validate(FacesContext context, UIComponent uiComponent,
			Object object) throws ValidatorException {

		String email = (String) object;
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (!matchFound) {

			throw new ValidatorException(new FacesMessage("Email inválido."));

		}
	}
}
