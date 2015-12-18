package org.st.smartnation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.dao.DailyMsgDao;
import org.st.smartnation.model.DailyMessage;
import org.st.smartnation.service.DailyMsgService;

@Service
public class DailyMsgServiceImpl implements DailyMsgService {

	@Autowired
	private DailyMsgDao dailyMsgDao;
	
	@Override
	@Transactional
	public List<DailyMessage> getDailyMsg() {
		return dailyMsgDao.getDailyMsg();
	}

}
