package cl.masvida.poc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;


public interface RcmDAOLocal {
	public RcmVO buscarRcm(BigDecimal i);
	public void guardarRcm(RcmVO rmcVO);
	public List<TipoPagoVO> getAllTipoPago();
	public TipoPagoVO buscarTipoPago(BigDecimal id);
}
