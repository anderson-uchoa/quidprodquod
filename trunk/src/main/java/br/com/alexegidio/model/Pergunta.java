package br.com.alexegidio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "seq_pergunta", sequenceName = "seq_pergunta", allocationSize = 1)
public class Pergunta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String descricao;
	private Date dataEnvio;
	private Boolean bloqueada;
	private Usuario usuario;
	private List<Resposta> respostas;
	private List<Tag> tags;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pergunta")
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

	@Lob
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "dt_envio")
	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(Boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	@Transient
	public Integer getQuantidadeRespostas() {
		return getRespostas().size();
	}

	public void setQuantidadeRespostas(Integer quantidade) {

	}

	@Transient
	public Integer getViews() {
		return null;
	}

	public void setViews(Integer views) {

	}

	@ManyToMany
	@JoinTable(name = "pergunta_tag", 
			joinColumns = { @JoinColumn(name = "id_pergunta") },
			inverseJoinColumns = { @JoinColumn(name = "id_tag") })
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta", fetch = FetchType.EAGER)
	@OrderBy("melhorResposta ASC")
	public List<Resposta> getRespostas() {
		if (respostas == null) {
			respostas = new ArrayList<Resposta>();
		}
		return respostas;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
