package com.redhat.masvida.cobranzas.medicas.web.rest.client;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.masvida.cobranzas.medicas.common.exception.AgenciaNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.exception.FolioOrdeAtencionNoEncontradoException;
import com.redhat.masvida.cobranzas.medicas.common.exception.TipoPagoNoEncontradaException;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.AgenciaVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.OrdenAtencionVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.PagoVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.RcmVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.cobranzas.medicas.common.vo.business.TipoPagoVO;

@ManagedBean
@RequestScoped
public class FuseRestClient {
	private static final Logger LOG = LoggerFactory
			.getLogger(FuseRestClient.class);

	@Inject
	private FuseRestDummyData db;

	public String getNombreAgencia(Integer id)
			throws AgenciaNoEncontradaException {
		try {
			LOG.debug("Consultando a los metodos REST por la id={} de Agencia",
					id);
			return db.getNombreAgencia(id);
		} catch (NullPointerException e) { // TODO: definir errores de negocio
			LOG.debug("Ocurrio un error al buscar una agencia con la ID {}", id);
			throw new AgenciaNoEncontradaException();
		}

	}

	public String getNombreRutCobrador(String rut) {
		return db.getNombreRutCobrador(rut);
	}

	// CDI
	public void setDb(FuseRestDummyData db) {
		this.db = db;
	}

	public List<AgenciaVO> findAllAgencias() {
		// TODO definir la paginacion (lazy load)
		return db.getAllAgencias();
	}

	public TipoPagoVO getTipoPagoDefecto() {
		return db.getTipoPagoDefecto();
	}

	public List<TipoPagoVO> findAllTipoPagoValido() {
		return db.getAllTipoPago();
	}

	public OrdenAtencionVO loadOrdenAtencion(Integer folioOA)
			throws FolioOrdeAtencionNoEncontradoException {
		return db.loadOrdenAtencion(folioOA);

	}

	public void guardarRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		db.guardarRcm(rcm, pago, ordenes);
	}

	public RcmVO findFolio(Integer folio) {
		return db.findFolio(folio);
	}

	public void deleteRcm(RecepcionCobranzaMedicaVO rcm, PagoVO pago,
			List<OrdenAtencionVO> ordenes) {
		db.deleteRcm(rcm);
	}

	public String getNombreTipoPago(Integer id)
			throws TipoPagoNoEncontradaException {
		try {
			return db.getNombreTipoPago(id);
		} catch (NullPointerException e) { // TODO: definir errores de negocio
			throw new TipoPagoNoEncontradaException();
		}

	}
}
