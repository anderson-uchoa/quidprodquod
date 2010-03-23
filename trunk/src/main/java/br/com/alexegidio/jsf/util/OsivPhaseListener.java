package br.com.alexegidio.jsf.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.SessionFactory;

import br.com.alexegidio.persistence.HibernateUtil;

/**
 * 
 * @author alexegidio@yahoo.com.br
 * 
 */
public class OsivPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void beforePhase(PhaseEvent event) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	public void afterPhase(PhaseEvent event) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			sessionFactory.getCurrentSession().getTransaction().commit();

		} catch (Throwable ex) {

			if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}
			ex.printStackTrace();
		}

	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;

	}
}