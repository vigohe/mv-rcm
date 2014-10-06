package com.redhat.masvida.cobranzas.medicas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.redhat.masvida.cobranzas.medicas.web.converter.FechaConverter;

public class FechaTest {

	@Test
	public void FechaConverter_UnSoloNumero_NumeroMasFechaActual() {
		String fecha = "01";
		FechaConverter fechaConverter = new FechaConverter(fecha);
		
		assertEquals("01/10/2014",  fechaConverter.formatear()  );
		
		
	}

	
	@Test
	public void FechaConverter_UnSoloNumeroOtroValor_NumeroMasFechaActual() {
		String fecha = "02";
		FechaConverter fechaConverter = new FechaConverter(fecha);
		
		assertEquals("02/10/2014",  fechaConverter.formatear()  );
		
	}
	
	
}
