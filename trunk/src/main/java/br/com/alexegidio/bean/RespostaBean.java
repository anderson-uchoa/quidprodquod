package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Role;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * 
 */
public class RespostaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pergunta pergunta;
	private String idEntity;
	private final GenericDaoHibernateImpl<Pergunta> perguntaDAO;
	private List<Pergunta> list;

	public RespostaBean() {
		super();
		pergunta = new Pergunta();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setRole(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public List<Pergunta> getList() {
		if (list == null) {
			list = new ArrayList<Pergunta>();
		}
		return list;
	}

	public void setList(List<Pergunta> list) {
		this.list = list;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public void save() {
		try {
			perguntaDAO.save(pergunta);
			listAll();
			FacesUtil.getInstance().sendMessageInfo(
					"Registro incluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao incluir o registro." + e);
		}
		pergunta = new Pergunta();
	}

	public void delete() {
		perguntaDAO.delete(pergunta);
	}

	public void listAll() {
		list = perguntaDAO.listAll(Pergunta.class);

	}

	public Pergunta load(Long id) {
		return perguntaDAO.load(id);
	}

	/**
	 * prepara o form para atualizacao
	 */
	public void prepareUpdate() {
		Long id = Long.parseLong(idEntity);
		setRole(load(id));
	}

	public List<SelectItem> getRoleSelect() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		GenericDaoHibernateImpl<Role> roleDao = new GenericDaoHibernateImpl<Role>(
				Role.class);
		List<Role> roles = roleDao.listAll(Role.class);
		for (Role r : roles) {
			list.add(new SelectItem(r, r.toString()));
		}
		return list;
	}

	public String answer() {
		pergunta = perguntaDAO.load(new Long(getIdEntity()));
		return "answer";
	}
}
