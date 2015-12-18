package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.dao.DailyMsgDao;
import org.st.smartnation.model.DailyMessage;

@Repository("dailyMsgDao")
public class DailyMsgDaoImpl implements DailyMsgDao {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<DailyMessage> getDailyMsg() {
		String sql = " from DailyMessage ";
		List<DailyMessage> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public int insertDailyMsg(DailyMessage dailyMsg) {
		int id = Integer.parseInt(baseDao.save(dailyMsg).toString());
		return id;
	}

	@Override
	public boolean deleteDailyMsg(int id) {
		String sql = " from DailyMessage where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<DailyMessage> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list.get(0));
			return true;
		}else{
			logger.warn("no exist this DailyMessage id:"+id);
			return false;
		}
	}

	@Override
	public boolean updateDailyMsg(DailyMessage dailyMsg) {
		String sql = " from DailyMessage where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", dailyMsg.getId());
		List<DailyMessage> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			DailyMessage dailymsg = list.get(0);
			dailymsg.setContent(dailyMsg.getContent());
			dailymsg.setDescrib(dailyMsg.getDescrib());
			dailymsg.setImagepath(dailyMsg.getImagepath());
			dailymsg.setTitle(dailyMsg.getTitle());
			baseDao.update(dailymsg);
			return true;
		}else{
			logger.warn("no exist this DailyMessage id:"+dailyMsg.getId());
			return false;
		}
	}

}
