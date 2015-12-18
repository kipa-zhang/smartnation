package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.dao.CorporateDao;
import org.st.smartnation.model.BusInfo;
import org.st.smartnation.model.BusStation;
import org.st.smartnation.model.CorporateDate;

@Repository("corporateDao")
public class CorporateDaoImpl implements CorporateDao {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<CorporateDate> getCorporateDates() {
		String sql = " from CorporateDate ";
		List<CorporateDate> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public int insertCorporateDate(CorporateDate corporateDate) {
		int id = Integer.parseInt(baseDao.save(corporateDate).toString());
		return id;
	}

	@Override
	public boolean deleteCorporateDate(int id) {
		String sql = " from CorporateDate where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<CorporateDate> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list.get(0));
			return true;
		}else{
			logger.warn("no exist this CorporateDate id:"+id);
			return false;
		}
	}

	@Override
	public boolean updateCorporateDate(CorporateDate corporateDate) {
		String sql = " from CorporateDate where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", corporateDate.getId());
		List<CorporateDate> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			CorporateDate corporatedate = list.get(0);
			corporatedate.setCorporateName(corporateDate.getCorporateName());
			corporatedate.setDescrib(corporateDate.getDescrib());
			corporatedate.setStartTime(corporateDate.getStartTime());
			corporatedate.setEndTime(corporateDate.getEndTime());
			baseDao.update(corporatedate);
			return true;
		}else{
			logger.warn("no exist this CorporateDate id:"+corporateDate.getId());
			return false;
		}
	}

	@Override
	public CorporateDate getCorporateDate(int id) {
		String sql = " from CorporateDate where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<CorporateDate> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
