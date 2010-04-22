package br.com.alexegidio.jsf.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.alexegidio.model.Usuario;

public class AuthenticatorListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String currentPage = context.getViewRoot().getViewId();

		Usuario currentUser = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");

		boolean isLoginPage = (currentPage.lastIndexOf("login.jsf") > -1);
		/*
		 * boolean securePage = (currentPage.startsWith("/admin") || currentPage
		 * .startsWith("/user"));
		 * 
		 * if (securePage) {
		 * 
		 * if (isLoginPage && currentUser == null) { NavigationHandler nh =
		 * context.getApplication() .getNavigationHandler();
		 * nh.handleNavigation(context, null, "loginPage");
		 * 
		 * } else if ((!currentUser.getRole().equals("ADMIN") && currentPage
		 * .startsWith("/admin"))) {
		 * 
		 * NavigationHandler nh = context.getApplication()
		 * .getNavigationHandler(); nh.handleNavigation(context, null,
		 * "loginPage");
		 * 
		 * } else if ((!currentUser.getRole().equals("USER") && currentPage
		 * .startsWith("/user"))) {
		 * 
		 * NavigationHandler nh = context.getApplication()
		 * .getNavigationHandler(); nh.handleNavigation(context, null,
		 * "loginPage");
		 * 
		 * } }
		 */
		if (!isLoginPage && currentUser == null) {
			NavigationHandler nh = context.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(context, null, "loginPage");
		}

	}

	public void beforePhase(PhaseEvent arg0) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
