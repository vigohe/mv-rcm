package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.masvida.poc.entities.Rcm;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.OrdenAtencionVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.RecepcionCobranzaMedicaVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Stateless
public class RcmDAO implements RcmDAOLocal {

	@PersistenceContext(unitName = "masvida-ejb-jpa")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public RcmDAO() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Método para buscar un RCM vía ID.
	 */
	public RcmVO buscarRcm(BigDecimal i) {
		
		Rcm r = null;
		
		Query query = em.createNamedQuery("Rcm.findByFolio");
		query.setParameter("rcmFolio", i);

		List<Rcm> rcms = query.getResultList();
		if (rcms != null && rcms.size() == 1) {
			r = rcms.get(0);
		}

//		// Veamos que tiene la Entity correspondiente
//		System.out
//				.println("--------------------------------------------------");
//		System.out.println("RCM Información");
//		System.out
//				.println("--------------------------------------------------");
//		System.out.println("RCM Folio id:" + r.getRcmFolio());
//		System.out.println("Fecha Recepción:" + r.getRcmFechaRecepcion());
//		System.out.println("Agencia:" + r.getAgencia1().getAgeNombre());
//		System.out.println("Observación:" + r.getRcmObserv());
		RcmVO rcm = getRcmEntityToVO(r);
		
		return rcm;

	}
	
	private RcmVO getRcmEntityToVO(Rcm rcmEntity){
		RcmVO rcmVO = new RcmVO();
		
		RecepcionCobranzaMedicaVO recepcionCobranzaMedicaVO = new RecepcionCobranzaMedicaVO();
		TipoPagoVO tipoPagoVO = new TipoPagoVO();
		List<OrdenAtencionVO> lsOrdenAtencion = new ArrayList<OrdenAtencionVO>();
		AgenciaVO agenciaVO = new AgenciaVO();
		
		
		
		// Veamos que tiene la Entity correspondiente
		System.out
				.println("--------------------------------------------------");
		System.out.println("RCM Información");
		System.out
				.println("--------------------------------------------------");
		System.out.println("RCM Folio id:" + rcmEntity.getRcmFolio());
		System.out.println("Fecha Recepción:" + rcmEntity.getRcmFechaRecepcion());
		System.out.println("Agencia:" + rcmEntity.getAgencia1().getAgeNombre());
		System.out.println("Observación:" + rcmEntity.getRcmObserv());
		
		
		// Seteamos algunos parámetros del VO
		try {
			
			recepcionCobranzaMedicaVO.setFolio(rcmEntity.getRcmFolio().intValueExact());
			recepcionCobranzaMedicaVO.setObservacion(rcmEntity.getRcmObserv());
			recepcionCobranzaMedicaVO.setFechaRecepcion(rcmEntity.getRcmFechaRecepcion());
			recepcionCobranzaMedicaVO.setFechaRegistro(rcmEntity.getRcmFechaPago());
			rcmVO.setRcm(recepcionCobranzaMedicaVO);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rcmVO;
	}
	

}
