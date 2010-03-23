/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexegidio.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * Project bm-jsf-util Version 1.0 Date: 09/10/2009
 * 
 * @author alexegidio email: alexegidio@yahoo.com.br Esta classe efetua a
 *         validacao de um campo confirmacao de senha
 */
public class PasswordValidator implements Validator {

	/**
	 * Dispara uma excecao na view se a senha e a senhas de confirmacao nao forem iguais
	 * o inputSecret da senha de confirmacao deve possuir um '<f:atribute>' embutido
	 * com o id = "senhaId" 
	 * Ex.: <h:inputSecret id="<id_input>" 
            <f:validator validatorId="<nome_validador>" />
            <f:attribute name="senhaId" value=<id_form>:<campo_senha>" />
          </h:inputSecret>
	 */
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		// Obtem o ID do primeito campo senha atraves do f:atribute.
		String senhaId = (String) component.getAttributes().get("senhaId");
		UIInput passwordInput = (UIInput) context.getViewRoot().findComponent(
				senhaId);
		String password = (String) passwordInput.getValue();
		String confirm = (String) value;
		if (!password.equals(confirm)) {
			throw new ValidatorException(new FacesMessage(
					"As senhas digitadas devem ser iguais."));
		}
	}
}
