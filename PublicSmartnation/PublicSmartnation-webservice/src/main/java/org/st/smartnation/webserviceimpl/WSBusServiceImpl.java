package org.st.smartnation.webserviceimpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.model.BusInfo;
import org.st.smartnation.service.BusService;
import org.st.smartnation.webservice.WSBusService;

@WebService  
public class WSBusServiceImpl implements WSBusService {

	@Autowired
	BusService busService;

	@Override
	public List<BusInfo> getBusInfo() {
		return busService.getBusInfo();
	}

	@Override
	public List<BusDetail> getBusDetail(@WebParam(name="code")String code) {
		return busService.getBusDetail(code);
	}

	@Override
	public List<BusDetail> getBusDetails(@WebParam(name="codes")String codes) {
		return busService.getBusDetails(codes);
	}
	
	
}




