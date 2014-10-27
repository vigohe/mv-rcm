package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.masvida.poc.entities.Agencia;
import cl.masvida.poc.entities.Rcm;
import cl.masvida.poc.entities.TipoPagoDoc;

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
	
	
	public void guardarRcm(RcmVO rcmVO){
		Rcm rcm = new Rcm();
		
		try{
			
			SimpleDateFormat fechaRegistro = new SimpleDateFormat("dd-MM-yyyy");
			
			rcm.setRcmFolio( new BigDecimal( rcmVO.getRcm().getFolio() ) );
			rcm.setRcmFecreg( fechaRegistro.format(rcmVO.getRcm().getFechaRegistro()) );
			rcm.setRcmFechaRecepcion( rcmVO.getRcm().getFechaRecepcion() );
			rcm.setRcmObserv( rcmVO.getRcm().getObservacion()   );
			rcm.setAgeCodRecep( new BigDecimal( rcmVO.getRcm().getAgenciaRecepcion().getId() ) );
			
			em.persist(rcm);
			
		}catch(Exception e){
			System.out.println("RmcDAO: ERROR en guardarRcm");
			e.printStackTrace();
		}
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
		
		RcmVO rcm = getRcmEntityToVO(r);
		
		return rcm;

	}
	
	public RcmVO getRcmEntityToVO(Rcm rcmEntity){
		RcmVO rcmVO = new RcmVO();
		
		RecepcionCobranzaMedicaVO recepcionCobranzaMedicaVO = new RecepcionCobranzaMedicaVO();
		TipoPagoVO tipoPagoVO = new TipoPagoVO();
		List<OrdenAtencionVO> lsOrdenAtencion = new ArrayList<OrdenAtencionVO>();
		AgenciaVO agenciaVO = new AgenciaVO();
		
		
		
		// Veamos que tiene la Entity correspondiente
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Información");
		System.out.println("--------------------------------------------------");
		System.out.println("RCM Folio id:" + rcmEntity.getRcmFolio());
		System.out.println("Fecha Recepción:" + rcmEntity.getRcmFechaRecepcion());
		System.out.println("Fecha Registro: " + rcmEntity.getRcmFecreg() );
		System.out.println("Agencia:" + rcmEntity.getAgencia1().getAgeNombre());
		System.out.println("Observación:" + rcmEntity.getRcmObserv());
		
		
		// Seteamos algunos parámetros del VO
		try {
			
			SimpleDateFormat fechaRegistro = new SimpleDateFormat("dd-MM-yyyy");
			
			recepcionCobranzaMedicaVO.setFolio(rcmEntity.getRcmFolio().intValueExact());
			recepcionCobranzaMedicaVO.setObservacion(rcmEntity.getRcmObserv());
			recepcionCobranzaMedicaVO.setFechaRecepcion(rcmEntity.getRcmFechaRecepcion());
			recepcionCobranzaMedicaVO.setFechaRegistro( fechaRegistro.parse( rcmEntity.getRcmFecreg() ) );
			
			agenciaVO.setDescripcion( rcmEntity.getAgencia1().getAgeNombre() );
			agenciaVO.setId(  rcmEntity.getAgencia1().getAgeCodigo().intValue()  );
			
			
			recepcionCobranzaMedicaVO.setAgenciaRecepcion(agenciaVO);
			
			rcmVO.setRcm(recepcionCobranzaMedicaVO);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return rcmVO;
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}



	@Override
	public List<TipoPagoVO> getAllTipoPago() {
		// TODO Auto-generated method stub
		List<TipoPagoVO> listaTipoPagoVO = new ArrayList<TipoPagoVO>();
		
		List<TipoPagoDoc> listaTipoPagoDoc = em.createNamedQuery("TipoPagoDoc.findAll" , TipoPagoDoc.class ).getResultList();
		
		for (TipoPagoDoc tipoPagoDoc : listaTipoPagoDoc) {
			TipoPagoVO tipoPagoVO = new TipoPagoVO(tipoPagoDoc.getTpdCodigo().intValue(), tipoPagoDoc.getTpdDescripcion());
			
			System.out.println("RcmDAO tipoPago: "+tipoPagoDoc.getTpdCodigo().intValue()+ " " + tipoPagoDoc.getTpdDescripcion());
			
			listaTipoPagoVO.add(tipoPagoVO);
		}
		
		return listaTipoPagoVO;

	}


	@Override
	public TipoPagoVO buscarTipoPago(BigDecimal id) {
		TipoPagoDoc tipoPagoDoc = em.find( TipoPagoDoc.class, id);
		TipoPagoVO tipoPagoVO = new TipoPagoVO(tipoPagoDoc.getTpdCodigo().intValue(), tipoPagoDoc.getTpdDescripcion());
		return tipoPagoVO;
	}
	

}
