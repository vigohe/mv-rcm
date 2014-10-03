package com.redhat.masvida.cobranzas.medicas.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class JsfUtil {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Flash getFlash() {
		return getFacesContext().getExternalContext().getFlash();
	}

	public static void addMessage(String clientId, String message) {
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO, message, message);
		getFacesContext().addMessage(clientId, facesMessage);
	}

	public static void addMessage(String clientId, String message,
			FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(severity, message, null);
		getFacesContext().addMessage(clientId, facesMessage);
	}

	public static void addMessage(String clientId, String title,
			String message, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(severity, title, message);
		getFacesContext().addMessage(clientId, facesMessage);
	}
}
