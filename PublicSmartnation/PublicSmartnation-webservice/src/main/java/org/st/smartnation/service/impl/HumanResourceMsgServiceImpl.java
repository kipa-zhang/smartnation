package org.st.smartnation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.dao.HumanResourceMsgDao;
import org.st.smartnation.model.HumanResourceMsg;
import org.st.smartnation.service.HumanResourceMsgService;

@Service
public class HumanResourceMsgServiceImpl implements	HumanResourceMsgService {

	@Autowired
	private HumanResourceMsgDao humanResourceMsgDao;
	
	@Override
	@Transactional
	public List<HumanResourceMsg> getHumanResourceMsg() {
		return humanResourceMsgDao.getHumanResourceMsg();
	}

	@Override
	@Transactional
	public List<HumanResourceMsg> getHumanResourceMsg(int userId) {
		if(userId != 0){
			return humanResourceMsgDao.getHumanResourceMsg(userId);
		}
		return null;
	}

	@Override
	@Transactional
	public int insertHRMsg(HumanResourceMsg humanResourceMsg) {
		if(humanResourceMsg != null){
			return humanResourceMsgDao.insertHRMsg(humanResourceMsg);
		}
		return 0;
	}

	@Override
	@Transactional
	public boolean deleteHRMsg(int id) {
		if(id != 0){
			return humanResourceMsgDao.deleteHRMsg(id);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deletePersonalHRMsg(int userId) {
		if(userId != 0){
			return humanResourceMsgDao.deletePersonalHRMsg(userId);
		}
		return false;
	}

	@Override
	public List<HumanResourceMsg> getHumanResourceMsg(int index, int size) {
		if(index != 0 && size != 0){
			return humanResourceMsgDao.getHumanResourceMsg(index, size);
		}
		return null;
	}
	
	public List<HumanResourceMsg> getHumanResourceMsg2(int index, int size) {
		if(index != 0 && size != 0){
			return humanResourceMsgDao.getHumanResourceMsg(index, size);
		}
		return null;
	}

	@Override
	public List<HumanResourceMsg> getHumanResourceMsg(int userId, int index,
			int size) {
		if(userId != 0 && index != 0 && size != 0){
			return humanResourceMsgDao.getHumanResourceMsg(userId, index, size);
		}
		return null;
	}

}
