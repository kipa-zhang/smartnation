package org.st.smartnation.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.st.smartnation.model.HumanResourceMsg;

@WebService
public interface WSHRMessageService {

	public List<HumanResourceMsg> getAllHRMessageInfo();
	public List<HumanResourceMsg> getHRMessageInfo(@WebParam(name="userId") int userId);
	public List<HumanResourceMsg> getNextHRMessageInfo(@WebParam(name="page") int page,@WebParam(name="size") int size);
	public List<HumanResourceMsg> getPersonalNextHRMessageInfo(@WebParam(name="page") int page,@WebParam(name="size") int size,@WebParam(name="userId") int userId);
	public int insertHRMessage(@WebParam(name="humanResourceMsg") HumanResourceMsg humanResourceMsg );
	public boolean deleteHRMessage(@WebParam(name="id")int id);
}
