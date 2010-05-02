package br.com.alexegidio.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Example;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.Criptography;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Role;
import br.com.alexegidio.model.Usuario;

public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String idEntity;
	private String senhaConfirmacao;
	private Role role;
	private String senhaAtual;

	private GenericDaoHibernateImpl<Usuario> usuarioDAO;
	private List<Usuario> list;

	public UsuarioBean() {
		super();
		usuarioDAO = new GenericDaoHibernateImpl<Usuario>(Usuario.class);
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	// ***********fim getters and setters*************\\
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

		usuario.setRole(getRole());
		Usuario usuarioExample = new Usuario();
		usuarioExample.setLogin(getUsuario().getLogin());

		if (usuario.getSenha().equals(senhaConfirmacao)) {

			Example userExample = Example.create(usuarioExample);
			userExample.excludeZeroes();

			if (usuarioDAO.findByCriteria(Usuario.class, userExample) == null) {
				try {
					usuario.setSenha(Criptography.encryptString(usuario
							.getSenha()));
					usuario.setRanking(new Integer(0));// todos começam como
					// rankinng 0
					usuario.setBloqueado(false);
					if (usuario.getRole().getNome() == null) {
						usuario.setRole(new GenericDaoHibernateImpl<Role>(
								Role.class).load(new Long(2)));
					}
					usuarioDAO.save(usuario);
					listAll();
					FacesUtil.getInstance().sendMessageInfo(
							"Registro incluído com sucesso.");
					usuario = new Usuario();
				} catch (Exception e) {
					e.printStackTrace();
					FacesUtil.getInstance().sendMessageError(
							"Erro ao incluir o registro." + e);
				}

			} else {
				FacesUtil.getInstance().sendMessageError(
						"Já existe um usuário com este login."
								+ " Por favor tente outro");
			}

		} else {
			FacesUtil.getInstance().sendMessageError(
					"As Senhas digitadas devem ser iguais.");
		}
	}

	public void delete() {
		usuarioDAO.delete(usuario);
	}

	public void listAll() {
		list = usuarioDAO.listAll();
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

	public void alterarSenha() {
		if (Criptography.encryptString(senhaAtual).equals(usuario.getSenha())) {
			save();
		} else {
			FacesUtil.getInstance().sendMessageError(
					"A senha atual não confere.");
		}
	}

	public void bloquearUsuario() {
		Long id = Long.parseLong(idEntity);
		setUsuario(load(id));
		getUsuario().setBloqueado(true);
		listAll();
	}

	public void desbloquearUsuario() {
		Long id = Long.parseLong(idEntity);
		setUsuario(load(id));
		getUsuario().setBloqueado(false);
		listAll();
	}
}
