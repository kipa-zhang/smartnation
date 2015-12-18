package org.st.smartnation.webserviceimpl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.st.smartnation.model.HumanResourceMsg;
import org.st.smartnation.service.HumanResourceMsgService;
import org.st.smartnation.webservice.WSHRMessageService;

@WebService  
public class WSHRMessageServiceImpl implements WSHRMessageService {

	@Autowired
	HumanResourceMsgService humanResourceMsgService;

	@Override
	public List<HumanResourceMsg> getAllHRMessageInfo() {
		return humanResourceMsgService.getHumanResourceMsg();
	}

	@Override
	public List<HumanResourceMsg> getHRMessageInfo(int userId) {
		return humanResourceMsgService.getHumanResourceMsg(userId);
	}

	@Override
	public List<HumanResourceMsg> getNextHRMessageInfo(int page, int size) {
		return humanResourceMsgService.getHumanResourceMsg(page, size);
	}

	@Override
	public List<HumanResourceMsg> getPersonalNextHRMessageInfo(int page,
			int size, int userId) {
		return humanResourceMsgService.getHumanResourceMsg(userId, page, size);
	}

	@Override
	public boolean deleteHRMessage(int id) {
		return humanResourceMsgService.deleteHRMsg(id);
	}

	@Override
	public int insertHRMessage(HumanResourceMsg humanResourceMsg) {
		return humanResourceMsgService.insertHRMsg(humanResourceMsg);
	}
	
	
}
