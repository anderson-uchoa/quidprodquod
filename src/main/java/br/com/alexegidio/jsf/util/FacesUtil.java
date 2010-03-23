package br.com.alexegidio.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	private static FacesUtil instance = new FacesUtil();

	private FacesUtil() {
	}

	public static FacesUtil getInstance() {
		return instance;
	}

	public void sendMessageInfo(String msg) {
		sendMessageView(msg, 1);
	}

	public void sendMessageError(String msg) {
		sendMessageView(msg, 0);
	}

	private void sendMessageView(String msg, int tipo) {

		FacesMessage fm = new FacesMessage();
		if (tipo == 0) {
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		} else if (tipo == 1) {
			fm.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		fm.setSummary(msg);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
}
