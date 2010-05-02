package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Role;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * 
 */
public class RoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Role role;
	private String idEntity;
	private final GenericDaoHibernateImpl<Role> roleDAO;
	private List<Role> list;

	public RoleBean() {
		super();
		role = new Role();
		roleDAO = new GenericDaoHibernateImpl<Role>(Role.class);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role Role) {
		this.role = Role;
	}

	public List<Role> getList() {
		if (list == null) {
			list = new ArrayList<Role>();
		}
		return list;
	}

	public void setList(List<Role> list) {
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
			roleDAO.save(role);
			listAll();
			FacesUtil.getInstance().sendMessageInfo(
					"Registro incluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao incluir o registro." + e);
		}
		role = new Role();
	}

	public void delete() {
		roleDAO.delete(role);
	}

	public void listAll() {
		list = roleDAO.listAll();

	}

	public Role load(Long id) {
		return roleDAO.load(id);
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
		GenericDaoHibernateImpl<Role> roleDao = new GenericDaoHibernateImpl<Role>(Role.class);
		List<Role> roles = roleDao.listAll();
		for (Role r : roles) {
			list.add(new SelectItem(r, r.toString()));
		}
		return list;
	}
}
