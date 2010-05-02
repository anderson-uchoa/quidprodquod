package br.com.alexegidio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
public class Usuario extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	private Integer ranking;
	private Boolean bloqueado;

	@ManyToOne
	@JoinColumn(name = "ID_ROLE")
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

	@Transient
	public Integer getRanking() {
		if (ranking == null) {
			return 0;
		}
		return ranking;
	}

	public void setRanking(Integer ranking) {
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

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

}
