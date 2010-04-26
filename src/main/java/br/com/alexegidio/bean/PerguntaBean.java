package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Tag;
import br.com.alexegidio.model.Usuario;

public class PerguntaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pergunta pergunta;
	private GenericDaoHibernateImpl<Pergunta> perguntaDAO;
	private Tag tag;
	private List<Pergunta> list;
	private String idPerguntaSelecionada;

	public PerguntaBean() {
		super();
		pergunta = new Pergunta();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta Pergunta) {
		this.pergunta = Pergunta;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Pergunta> getList() {
		return list;
	}

	public void setList(List<Pergunta> list) {
		this.list = list;
	}

	public String getIdPerguntaSelecionada() {
		return idPerguntaSelecionada;
	}

	public void setIdPerguntaSelecionada(String idPerguntaSelecionada) {
		this.idPerguntaSelecionada = idPerguntaSelecionada;
	}

	// Fim getter and setters

	public void save() {
		getPergunta().setDataEnvio(Calendar.getInstance().getTime());
		Usuario usu = (Usuario) FacesUtil.getInstance().getSessionObject(
				"usuario");
		getPergunta().setUsuario(usu);
		try {
			perguntaDAO.save(getPergunta());
			listAll();
			FacesUtil.getInstance().sendMessageInfo(
					"Registro incluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao incluir o registro." + e);
		}
		setPergunta(new Pergunta());
	}

	public void delete() {
		perguntaDAO.delete(pergunta);
	}

	public void listAll() {
		list = perguntaDAO.listAll(Pergunta.class);
	}

	public void addTag() {
		if (tag == null) {
			FacesUtil.getInstance().sendMessageError("Informe uma Tag");

		} else {
			if (pergunta.getTags().size() >= 3) {
				FacesUtil.getInstance().sendMessageError(
						"Você pode adicionar somente 3 tags");
			} else {
				pergunta.getTags().add(tag);
			}
		}

	}

	public List<Pergunta> getLastQuestions() {
		return perguntaDAO.listAll(false, "dataEnvio", 10);
	}

}
