package br.com.alexegidio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	private String ranking;

	@ManyToOne
	private Role role;

	@Column(unique = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public Role getRole() {
		if (role == null) {
			role = new Role();
		}
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
