package br.com.alexegidio.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.model.Role;
import br.com.alexegidio.model.Tag;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * 
 */
public class SuportBean {

	public SuportBean() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> getTagSelect() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Tag> tags = new GenericDaoHibernateImpl(Tag.class)
				.listAll();
		for (Tag t : tags) {
			list.add(new SelectItem(t, t.toString()));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> getRoleSelect() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Role> roles = new GenericDaoHibernateImpl(Role.class)
				.listAll();
		for (Role r : roles) {
			list.add(new SelectItem(r, r.toString()));
		}
		return list;
	}

}
