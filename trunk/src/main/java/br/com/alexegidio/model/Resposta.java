package br.com.alexegidio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resposta implements BaseEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String resposta;
	private Boolean melhorResposta;
	private Date dataResposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Date getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}

	public Boolean getMelhorResposta() {
		return melhorResposta;
	}

	public void setMelhorResposta(Boolean melhorResposta) {
		this.melhorResposta = melhorResposta;
	}

}
