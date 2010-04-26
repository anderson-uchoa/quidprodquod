package br.com.alexegidio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_resposta", sequenceName = "seq_resposta", allocationSize = 1)
public class Resposta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;
	private Boolean melhorResposta;
	private Date dataResposta;
	private Usuario usuario;

	private Pergunta pergunta;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resposta")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Lob
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "DT_RESP")
	public Date getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}

	@Column(name = "MELHOR_RESP")
	public Boolean getMelhorResposta() {
		return melhorResposta;
	}

	public void setMelhorResposta(Boolean melhorResposta) {
		this.melhorResposta = melhorResposta;
	}

	@OneToOne
	@JoinColumn(name = "ID_PERGUNTA")
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
