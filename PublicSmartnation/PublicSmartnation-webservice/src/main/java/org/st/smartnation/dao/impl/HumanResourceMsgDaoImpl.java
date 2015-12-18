package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.dao.HumanResourceMsgDao;
import org.st.smartnation.model.HumanResourceMsg;

@Repository("humanResourceMsgDao")
public class HumanResourceMsgDaoImpl implements HumanResourceMsgDao {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<HumanResourceMsg> getHumanResourceMsg() {
		String sql = " from HumanResourceMsg ";
		List<HumanResourceMsg> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<HumanResourceMsg> getHumanResourceMsg(int userId) {
		String sql = " from HumanResourceMsg where userId=:userId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<HumanResourceMsg> list = baseDao.find(sql,params);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public int insertHRMsg(HumanResourceMsg humanResourceMsg) {
		int id = Integer.parseInt(baseDao.save(humanResourceMsg).toString());
		return id;
	}

	@Override
	public boolean deleteHRMsg(int id) {
		String sql = " from HumanResourceMsg where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<HumanResourceMsg> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list.get(0));
			return true;
		}else{
			logger.warn("no exist this HumanResourceMsg id:"+id);
			return false;
		}
	}

	@Override
	public boolean deletePersonalHRMsg(int userId) {
		String sql = " from HumanResourceMsg where userId =:userId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<HumanResourceMsg> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list);
			return true;
		}else{
			logger.warn("no exist this HumanResourceMsg userId:"+userId);
			return false;
		}
	}

	@Override
	public List<HumanResourceMsg> getHumanResourceMsg(int page, int size) {
		String hql = " from HumanResourceMsg ";
        DetachedCriteria criteria=DetachedCriteria.forClass(HumanResourceMsg.class);
		List<HumanResourceMsg> list= baseDao.findByPage(criteria, hql, page, size);
		return list;
	}
	
	@Override
	public List<HumanResourceMsg> getHumanResourceMsg(int userId, int page,
			int size) {
		String hql = " from HumanResourceMsg where userId=:userId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		DetachedCriteria criteria=DetachedCriteria.forClass(HumanResourceMsg.class);
		List<HumanResourceMsg> list= baseDao.findByPage(criteria,hql, params, page, size);
		return list;
	}

}
