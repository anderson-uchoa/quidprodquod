/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.alexegidio.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.hibernate.criterion.Example;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.Criptography;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Usuario;

/**
 * 
 * @author Alex Egidio
 * 
 */
public class AuthenticatorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private boolean logado;
	private boolean admin = false;

	private final GenericDaoHibernateImpl<Usuario> userDao;

	public AuthenticatorBean() {
		userDao = new GenericDaoHibernateImpl<Usuario>(Usuario.class);
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public boolean isAdmin() {
		if (usuario.getRole().getNome() != null
				&& usuario.getRole().getNome().equals("ADMIN")) {
			admin = true;
		}
		return admin;
	}

	public String autenthicate() {

		Example userExample = Example.create(usuario);
		userExample.excludeZeroes();
		usuario.setSenha(Criptography.encryptString(usuario.getSenha()));

		usuario = userDao.findByCriteria(Usuario.class, userExample);
		String retorno = "fail";
		if (usuario == null) {
			FacesUtil.getInstance().sendMessageError(
					"Erro no login. Verifique sua senha.");
			usuario = new Usuario();

		} else {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuario", usuario);
			logado = true;
			if (usuario.getRole().getNome().equals("ADMIN")) {
				retorno = "admin";
				admin = true;
			} else if (usuario.getRole().getNome().equals("USER")) {
				retorno = "user";
			}
		}
		usuario = new Usuario();
		return retorno;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("usuario");
		logado = false;
		return "logout";
	}

}
