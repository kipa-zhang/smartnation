package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.dao.ParkingDao;
import org.st.smartnation.model.DailyMessage;
import org.st.smartnation.model.Parking;

@Repository("parkingDao")
public class ParkingDaoImpl implements ParkingDao {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<Parking> getParkingInfo() {
		String sql = " from Parking ";
		List<Parking> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Parking getParkingInfo(String name) {
		String sql = " from Parking where name=:name ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<Parking> list = baseDao.find(sql,params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean insertParkingInfo(List<Parking> parkingInfo) {
		if(parkingInfo!=null&&!parkingInfo.isEmpty()){
			List<Parking> parkingInfoFromTable = this.getParkingInfo();
	    	if(parkingInfoFromTable != null && !parkingInfoFromTable.isEmpty()){
	    		for (Parking parkingInternet : parkingInfo) {
	    			Parking parkingFromTable = this.getParkingInfo(parkingInternet.getName());
	    			if(parkingFromTable != null){
	    				parkingInternet.setId(parkingFromTable.getId());
	    				baseDao.update(parkingInternet);
	    			}else{
	    				baseDao.save(parkingInternet);
	    			}
	    		}
	    		return true;
	    	}else{
	    		//将数据插入数据库
	            baseDao.save(parkingInfo);
	            return true;
	    	}
		}else{
			logger.warn("参数 List<Parking> parkingInfo 为空");
			return false;
		}
	}

	@Override
	public boolean updateParkingInfo(List<Parking> parkingInfo) {
		baseDao.update(parkingInfo);
		return true;
	}

	@Override
	public boolean insertParkingInfo(Parking parkingInfo) {
		baseDao.save(parkingInfo);
		return true;
	}

	@Override
	public boolean updateParkingInfo(Parking parkingInfo) {
		baseDao.update(parkingInfo);
		return true;
	}

}
