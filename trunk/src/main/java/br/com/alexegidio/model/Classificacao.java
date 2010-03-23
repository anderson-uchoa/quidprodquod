package br.com.alexegidio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Classificacao implements BaseEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne
	private Usuario usario;

	@OneToOne
	private Resposta resposta;

	private Integer ponto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsario() {
		return usario;
	}

	public void setUsario(Usuario usario) {
		this.usario = usario;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Integer getPonto() {
		return ponto;
	}

	public void setPonto(Integer ponto) {
		this.ponto = ponto;
	}

}
