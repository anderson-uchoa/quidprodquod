package br.com.alexegidio.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.Criptography;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Usuario;

public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String idEntity;
	private String senhaConfirmacao;

	private GenericDaoHibernateImpl<Usuario> usuarioDAO;
	private List<Usuario> list;

	public UsuarioBean() {
		super();
		usuario = new Usuario();
		usuarioDAO = new GenericDaoHibernateImpl<Usuario>(Usuario.class);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Usuario load(Long id) {
		return usuarioDAO.load(id);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getList() {
		if (list == null) {
			list = new ArrayList<Usuario>();
		}
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public Date getDataPadrao() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return fmt.parse("01/01/1976");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save() {

		if (usuario.getSenha().equals(senhaConfirmacao)) {
			try {
				usuario
						.setSenha(Criptography
								.encryptString(usuario.getSenha()));
				usuarioDAO.save(usuario);
				listAll();
				FacesUtil.getInstance().sendMessageInfo(
						"Registro incluído com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				FacesUtil.getInstance().sendMessageError(
						"Erro ao incluir o registro." + e);
			}
			usuario = new Usuario();
		} else {
			FacesUtil.getInstance().sendMessageError(
					"As Senhas digitadas devem ser iguais.");
		}
	}

	public void delete() {
		usuarioDAO.delete(usuario);
	}

	public void listAll() {
		list = usuarioDAO.listAll(Usuario.class);
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * prepara o form para atualizacao
	 */
	public void prepareUpdate() {
		Long id = Long.parseLong(idEntity);
		setUsuario(load(id));
	}

	public void remove() {
		Long id = Long.parseLong(idEntity);
		usuarioDAO.delete(load(id));
		listAll();
	}

}
