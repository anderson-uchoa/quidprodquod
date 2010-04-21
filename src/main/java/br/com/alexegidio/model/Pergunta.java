package br.com.alexegidio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pergunta implements BaseEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String descricao;
	private Date dataEnvio;
	private Boolean bloqueado;
	private List<Resposta> respostas;
	private List<Tag> tags;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Integer getQuantidadeRespostas() {
		return getRespostas().size();
	}

	public void setQuantidadeRespostas(Integer quantidade) {

	}

	public Integer getViews() {
		return null;
	}

	public void setViews(Integer views) {
		
	}

	@OneToMany
	public List<Tag> getTags() {
		if (tags == null) {
			tags = new ArrayList<Tag>();
		}
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@OneToMany(cascade= CascadeType.ALL)
	public List<Resposta> getRespostas() {
		if (respostas == null) {
			respostas = new ArrayList<Resposta>();
		}
		return respostas;
	}

}
