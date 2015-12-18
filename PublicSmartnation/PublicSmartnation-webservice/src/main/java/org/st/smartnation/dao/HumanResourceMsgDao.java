package org.st.smartnation.dao;

import java.util.List;

import org.st.smartnation.model.HumanResourceMsg;

public interface HumanResourceMsgDao {

	public List<HumanResourceMsg> getHumanResourceMsg();
	public List<HumanResourceMsg> getHumanResourceMsg(int index,int size); 
	public List<HumanResourceMsg> getHumanResourceMsg(int userId);
	public List<HumanResourceMsg> getHumanResourceMsg(int userId,int index,int size);
	public int insertHRMsg(HumanResourceMsg humanResourceMsg);
	public boolean deleteHRMsg(int id);
	public boolean deletePersonalHRMsg(int userId);
	
}
