package com.redhat.masvida.cobranzas.medicas.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class SimpleDateFormatTest {

	@Test
	public void SimpleDateFormat_TransformarFechaActualAstring_fechaEnString() {
		Date fechaActual = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		
		assertEquals("06/10/2014", formateador.format(fechaActual));
	}

	
//	@Test
//	public void SimpleDateFormat_TransformarStringAstring_fechaEnString() {
//		String fecha = "01";
//		Date fechaActual = new Date();
//		
//		
//		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//		formateador.format(fecha);
//		
//		assertEquals("06/10/2014", formateador.format(fecha));
//	}
//	
//	
	
}
