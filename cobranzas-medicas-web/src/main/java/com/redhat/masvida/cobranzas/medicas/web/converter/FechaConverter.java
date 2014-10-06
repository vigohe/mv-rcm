package com.redhat.masvida.cobranzas.medicas.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaConverter {
	private String fecha;
	
	public FechaConverter(String fecha){
		this.fecha = fecha;
	}

	public String formatear(){
		String fechaActual;
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		fechaActual = formatoFecha.format(new Date());
		
		
		if( fecha.length() == 1 ){
			fecha = "0" + fecha; 
		}
		
		
		return fecha + fechaActual.substring(2, fechaActual.length());
	}

}
