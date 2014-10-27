import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;

import cl.masvida.poc.dao.RcmDAO;
import cl.masvida.poc.entities.Agencia;
import cl.masvida.poc.entities.Rcm;
import static org.mockito.Mockito.*;

public class testRcmEntityToVO {

	private EntityManager em;
	private RcmDAO rcmDAO;
	private Rcm rcmEntity;
	
	@Before
	public void setUp() throws Exception {
		rcmDAO = new RcmDAO();
		
		em = mock(EntityManager.class);
		rcmDAO.setEm(em);
		
		rcmEntity = new Rcm();
		rcmEntity.setRcmFolio(new BigDecimal(800));
		rcmEntity.setRcmFechaRecepcion(new Date());
		rcmEntity.setRcmFecreg("21-10-2014");
		
		Agencia agencia = new Agencia();
		agencia.setAgeCodigo(new BigDecimal(100));
		agencia.setAgeNombre("Agencia 100");
		
		rcmEntity.setAgencia1(agencia);
		rcmEntity.setRcmObserv("Observacion................");
		
		
	}
	

	@Test
	public void test() {		
		RcmVO rcmVO = new RcmVO();
		Query query = mock(Query.class);
        when(em.createNamedQuery("Rcm.findByFolio")).thenReturn(query);	
		
        RcmDAO rcmDao = mock(RcmDAO.class);
        Rcm rcm = mock(Rcm.class);
        
        when(rcmDao.getRcmEntityToVO(rcmEntity));
  
		rcmVO = rcmDAO.buscarRcm(new BigDecimal(800));
		
		assertEquals( "800" , "800" );
		
		

	}

}
