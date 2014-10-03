package com.redhat.masvida.cobranzas.medicas.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.richfaces.application.ServiceTracker;
import org.richfaces.focus.FocusManager;

public class RichFacesUtil {

	public static void changeFocusTo(String simpleComponentId){
		FocusManager focusManager = ServiceTracker.getService(FocusManager.class);
        focusManager.focus(simpleComponentId);
	}
}
