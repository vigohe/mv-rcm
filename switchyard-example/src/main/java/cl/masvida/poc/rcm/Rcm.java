package cl.masvida.poc.rcm;

import java.util.List;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;



public interface Rcm {

	RcmVO buscarPorFolio(RcmVO rcmVO);
	
	AgenciaVO[] obtenerAgencias();
	
	List<TipoPagoVO> obtenerTiposPago();
	
	void guardarRcm(RcmVO rcmVO) throws Exception;
	
	TipoPagoVO buscarTipoPago(TipoPagoVO tipoPagoVO);
	
	TipoPagoVO tipoPagoDefecto();
	
}
