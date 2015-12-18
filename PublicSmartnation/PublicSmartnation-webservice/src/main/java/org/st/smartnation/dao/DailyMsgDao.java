package org.st.smartnation.dao;

import java.util.List;

import org.st.smartnation.model.DailyMessage;

public interface DailyMsgDao {
	
	public List<DailyMessage> getDailyMsg();
	public int insertDailyMsg(DailyMessage dailyMsg);
	public boolean deleteDailyMsg(int id);
	public boolean updateDailyMsg(DailyMessage dailyMsg);
	
}
