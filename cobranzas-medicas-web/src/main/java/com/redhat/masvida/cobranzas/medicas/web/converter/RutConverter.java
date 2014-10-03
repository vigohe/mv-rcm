package com.redhat.masvida.cobranzas.medicas.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FacesConverter(value = "com.redhat.mas.vida.converter.RutConverter")
public class RutConverter implements Converter {
	private final static Logger LOG = LoggerFactory
			.getLogger(RutConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try {
			Integer s = Integer.parseInt(value.replace(".", "")
					.replace("-", ""));
			return s;
		} catch (NumberFormatException nfe) {
			LOG.debug("Error de conversion del rut={}", value);
		} catch (NullPointerException ex) {
			LOG.debug("NO envio valores para convertir valor = {}", value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Integer) value).intValue() + "";
	}

}
