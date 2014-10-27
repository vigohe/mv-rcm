package cl.masvida.poc.ejb;

import java.util.List;
import java.math.BigDecimal;

import javax.ejb.Remote;

import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Remote
public interface RCMFacade {

	public RcmVO buscarRcm(BigDecimal i);
	public void guardarRcm(RcmVO rcmVO);
	public List<TipoPagoVO> getAllTipoPago();
	public TipoPagoVO buscarTipoPago(BigDecimal id);
	
}
