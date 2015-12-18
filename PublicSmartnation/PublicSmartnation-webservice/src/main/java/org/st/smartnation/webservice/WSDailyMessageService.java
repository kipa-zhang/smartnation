package org.st.smartnation.webservice;

import java.util.List;

import javax.jws.WebService;

import org.st.smartnation.model.DailyMessage;

@WebService
public interface WSDailyMessageService {

	public List<DailyMessage> getAllDailyMessageInfo();
}

