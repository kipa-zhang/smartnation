package org.st.smartnation.webserviceimpl;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.beans.factory.annotation.Autowired;
import org.st.smartnation.model.DailyMessage;
import org.st.smartnation.service.DailyMsgService;
import org.st.smartnation.webservice.WSDailyMessageService;

@WebService
@SOAPBinding(style=Style.RPC)
public class WSDailyMessageServiceImpl implements WSDailyMessageService{

	@Autowired
	DailyMsgService dailyMsgService;
	
	@Override
	public List<DailyMessage> getAllDailyMessageInfo() {
		return dailyMsgService.getDailyMsg();
	}

}
