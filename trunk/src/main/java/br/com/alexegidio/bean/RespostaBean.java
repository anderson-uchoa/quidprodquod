package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Resposta;
import br.com.alexegidio.model.Role;
import br.com.alexegidio.model.Usuario;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * @date 21/04/2010
 * @since version 1.0
 * @version 1.0
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
	private Resposta resposta;

	public RespostaBean() {
		super();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
	}

	public Pergunta getPergunta() {
		if (pergunta == null) {
			pergunta = new Pergunta();
		}
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

	public Resposta getResposta() {
		if (resposta == null) {
			resposta = new Resposta();
		}
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	// *************Fim Getters and Setters**************\\

	public void save() {
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
		resposta = new Resposta();
	}

	public void delete() {
		perguntaDAO.delete(pergunta);
	}

	public void listAll() {
		list = perguntaDAO.listAll();

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
		List<Role> roles = roleDao.listAll();
		for (Role r : roles) {
			list.add(new SelectItem(r, r.toString()));
		}
		return list;
	}

	public String answer() {
		pergunta = perguntaDAO.load(new Long(getIdEntity()));
		return "answer";
	}

	public void submitAnswer() {
		resposta.setPergunta(getPergunta());
		Usuario usuario = (Usuario) FacesUtil.getInstance().getSessionObject(
				"usuario");
		Integer rank = usuario.getRanking() + 5;
		saveRanking(rank);

		resposta.setDataResposta(Calendar.getInstance().getTime());
		resposta.setUsuario(usuario);
		pergunta.getRespostas().add(getResposta());
		save();
	}

	private void saveRanking(Integer ranking) {
		Usuario usuario = (Usuario) FacesUtil.getInstance().getSessionObject(
				"usuario");
		usuario.setRanking(ranking);
		new GenericDaoHibernateImpl<Usuario>(Usuario.class).save(usuario);
	}
}
