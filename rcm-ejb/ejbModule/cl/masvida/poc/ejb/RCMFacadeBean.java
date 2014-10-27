package cl.masvida.poc.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cl.masvida.poc.dao.RcmDAOLocal;

import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

/**
 * Session Bean implementation class RCMFacadeBean
 */
@Stateless
public class RCMFacadeBean implements RCMFacade {

	@EJB
	RcmDAOLocal rcmDAOLocal;
	
    /**
     * Default constructor. 
     */
    public RCMFacadeBean() {
        // TODO Auto-generated constructor stub
    }
    
    public RcmVO buscarRcm(BigDecimal i){
    	return rcmDAOLocal.buscarRcm(i);
    }

	@Override
	public void guardarRcm(RcmVO rcmVO) {
		rcmDAOLocal.guardarRcm(rcmVO);
		
	}
	
	public List<TipoPagoVO> getAllTipoPago(){
		return rcmDAOLocal.getAllTipoPago();
	}

	@Override
	public TipoPagoVO buscarTipoPago(BigDecimal id) {
		return rcmDAOLocal.buscarTipoPago(id);
	}
	
	
	
}
