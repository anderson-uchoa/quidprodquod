package br.com.alexegidio.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Tag;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * 
 */
public class SuportBean {

	private final GenericDaoHibernateImpl<Pergunta> perguntaDao;
	private List<Pergunta> lastQuestions;
	
	public SuportBean() {
		super();
		perguntaDao = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
		lastQuestions = perguntaDao.listAll(Pergunta.class);
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> getTagSelect() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Tag> tags = new GenericDaoHibernateImpl(Tag.class).listAll(Tag.class);
		for (Tag t : tags) {
			list.add(new SelectItem(t, t.toString()));
		}
		return list;
	}

	public List<Pergunta> getLastQuestions() {
		//lastQuestions  = perguntaDao.listAll(Pergunta.class);
		return lastQuestions;
	}

	public void setLastQuestions(List<Pergunta> lastQuestions) {
		this.lastQuestions = lastQuestions;
	}
	
	
}
