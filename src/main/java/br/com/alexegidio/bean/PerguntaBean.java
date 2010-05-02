package br.com.alexegidio.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.FacesUtil;
import br.com.alexegidio.model.Pergunta;
import br.com.alexegidio.model.Tag;
import br.com.alexegidio.model.Usuario;

public class PerguntaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pergunta pergunta;
	private GenericDaoHibernateImpl<Pergunta> perguntaDAO;
	private Tag tag;
	private List<Pergunta> list;
	private String idPerguntaSelecionada;
	private String idEntity;

	public PerguntaBean() {
		super();
		pergunta = new Pergunta();
		perguntaDAO = new GenericDaoHibernateImpl<Pergunta>(Pergunta.class);
	}

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

	public String getIdPerguntaSelecionada() {
		return idPerguntaSelecionada;
	}

	public void setIdPerguntaSelecionada(String idPerguntaSelecionada) {
		this.idPerguntaSelecionada = idPerguntaSelecionada;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	// Fim getter and setters

	public Pergunta load(Long id) {
		return perguntaDAO.load(id);
	}

	public void save() {
		getPergunta().setDataEnvio(Calendar.getInstance().getTime());
		Usuario usu = (Usuario) FacesUtil.getInstance().getSessionObject(
				"usuario");
		getPergunta().setUsuario(usu);
		getPergunta().setBloqueada(false);
		try {
			perguntaDAO.save(getPergunta());
			listAll();
			FacesUtil.getInstance().sendMessageInfo(
					"Registro salvo com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao incluir o registro." + e);
		}
		setPergunta(new Pergunta());
	}

	public void delete() {
		perguntaDAO.delete(pergunta);
	}

	public void listAll() {
		list = perguntaDAO.listAll();
	}

	public void addTag() {
		if (tag == null) {
			FacesUtil.getInstance().sendMessageError("Informe uma Tag");

		} else {
			if (pergunta.getTags().size() >= 3) {
				FacesUtil.getInstance().sendMessageError(
						"Você pode adicionar somente 3 tags");
			} else {
				pergunta.getTags().add(tag);
			}
		}

	}

	public List<Pergunta> getLastQuestions() {
		String hql = "from Pergunta p where p.bloqueada = "
				+ "false order by p.dataEnvio desc";
		return perguntaDAO.findByHQL(hql);
	}

	public List<Pergunta> getAllQuestions() {
		return perguntaDAO.listAll();
	}

	/**
	 * prepara o form para atualizacao
	 */
	public void blockQuestion() {
		Long id = Long.parseLong(idEntity);
		setPergunta(load(id));
		if (getPergunta().getBloqueada()) {
			FacesUtil.getInstance().sendMessageError(
					"Esta pergunta já foi bloqueada");

		} else {
			getPergunta().setBloqueada(true);
		}
		try {
			perguntaDAO.save(getPergunta());
			FacesUtil.getInstance().sendMessageInfo("Pergunta bloqueada");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.getInstance().sendMessageError(
					"Erro ao executar a operação: " + e);
		}

	}

}
