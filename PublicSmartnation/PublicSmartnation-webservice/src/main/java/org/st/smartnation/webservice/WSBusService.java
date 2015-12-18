package org.st.smartnation.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.model.BusInfo;

@WebService
public interface WSBusService {

	public List<BusInfo> getBusInfo();
	public List<BusDetail> getBusDetail(@WebParam(name="code")String code);
	public List<BusDetail> getBusDetails(@WebParam(name="codes")String codes);
}
