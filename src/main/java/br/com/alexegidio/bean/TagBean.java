package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Tag;

public class TagBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tag tag;
	private String idEntity;
	private GenericDaoHibernateImpl<Tag> tagDAO;
	private List<Tag> list;

	public TagBean() {
		super();
		tag = new Tag();
		tagDAO = new GenericDaoHibernateImpl<Tag>(Tag.class);
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag Tag) {
		this.tag = Tag;
	}

	public List<Tag> getList() {
		if (list == null) {
			list = new ArrayList<Tag>();
		}
		return list;
	}

	public void setList(List<Tag> list) {
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
			tagDAO.save(tag);
			listAll();
			FacesUtil.getInstance().sendMessageInfo(
					"Registro incluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao incluir o registro." + e);
		}
		tag = new Tag();
	}

	public void delete() {
		tagDAO.delete(tag);
	}

	public void listAll() {
		list = tagDAO.listAll();

	}

	public Tag load(Long id) {
		return tagDAO.load(id);
	}

	/**
	 * prepara o form para atualizacao
	 */
	public void prepareUpdate() {
		Long id = Long.parseLong(idEntity);
		setTag(load(id));
	}
}
