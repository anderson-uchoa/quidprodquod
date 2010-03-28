package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Tag;

public class PerguntaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pergunta pergunta;
	private GenericDaoHibernateImpl<Pergunta> perguntaDAO;
	private Tag tag;
	private List<Pergunta> list;

	public PerguntaBean() {
		super();
		pergunta = new Pergunta();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
	}

	/******* G&S *********************/
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

	/***** GS ***************/

	public void save() {
		perguntaDAO.save(pergunta);
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
				FacesUtil.getInstance().sendMessageError("Você pode adicionar somente 3 tags");
			} else {
				pergunta.getTags().add(tag);
				tag = new Tag();
			}
		}

	}
}
