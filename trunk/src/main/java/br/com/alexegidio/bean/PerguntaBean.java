package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.model.Pergunta;

public class PerguntaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pergunta pergunta;
	private GenericDaoHibernateImpl<Pergunta> perguntaDAO;
	private List<Pergunta> list;

	public PerguntaBean() {
		super();
		pergunta = new Pergunta();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>();
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta Pergunta) {
		this.pergunta = Pergunta;
	}

	public List<Pergunta> getList() {
		return list;
	}

	public void setList(List<Pergunta> list) {
		this.list = list;
	}

	public void save() {
		perguntaDAO.save(pergunta);
	}

	public void delete() {
		perguntaDAO.delete(pergunta);
	}

	public void listAll() {
		list = perguntaDAO.listAll(Pergunta.class);

	}
}
