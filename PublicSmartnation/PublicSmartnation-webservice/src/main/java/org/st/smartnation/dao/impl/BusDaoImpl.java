package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.dao.BusDao;
import org.st.smartnation.model.BusInfo;
import org.st.smartnation.model.BusSchedule;
import org.st.smartnation.model.BusStation;

@Repository("busDao")
public class BusDaoImpl implements BusDao {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public List<BusInfo> getBusInfo() {
		String sql = " from BusInfo ";
		List<BusInfo> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public BusInfo getBusInfo(String code) {
		String sql = " from BusInfo where code =:code ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<BusInfo> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BusSchedule> getBusSchedule() {
		String sql = " from BusSchedule ";
		List<BusSchedule> list = baseDao.find(sql);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public BusSchedule getBusSchedule(int busId) {
		String sql = " from BusSchedule where busId =:busId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busId", busId);
		List<BusSchedule> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public BusStation getBusStation(int id) {
		String sql = " from BusStation where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<BusStation> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BusDetail> getBusDetail(String code) {
		String sql = " SELECT new org.st.smartnation.bean.BusDetail(b.code ,b.startTime ,b.endTime ,bs.sortNum ,s.stationName ,s.lon ,s.lat) "+
					 " FROM BusInfo b,BusSchedule bs, BusStation s "+
					 " WHERE  b.id = bs.busId AND bs.stationId = s.id  AND b.code = :code ORDER BY b.id ASC,bs.sortNum ASC ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<BusDetail> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean insertBusInfo(List<BusInfo> busInfos) {
		if(busInfos!= null &&!busInfos.isEmpty()){
			for (BusInfo busInfo : busInfos) {
				this.insertBusInfo(busInfo);	
			}
			return true;
		}
		logger.warn("参数 List<BusInfo> busInfos 为空");
		return false;
	}

	@Override
	public boolean insertBusSchedule(List<BusSchedule> busSchedules) {
		if(busSchedules != null && !busSchedules.isEmpty()){
			baseDao.save(busSchedules);
			return true;
		}else{
			logger.warn("参数List<BusSchedule> busSchedules 为空 ");
			return false;
		}
	}

	@Override
	public boolean insertBusStation(List<BusStation> busStations) {
		if(busStations != null && !busStations.isEmpty()){
			for (BusStation busStation : busStations) {
				this.insertBusStation(busStation);
			}
			return true;
		}else{
			logger.warn("参数List<BusStation> busStations 为空 ");
			return false;
		}
	}

	@Override
	public boolean deleteBusInfo(String code) {
		String sql = " from BusInfo where code =:code ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<BusInfo> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			if(this.deleteBusSchedule(list.get(0).getId())){
				baseDao.delete(list.get(0));
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	@Override
	public boolean deleteBusSchedule(int busId){
		String sql = " from BusSchedule where busId =:busId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busId", busId);
		List<BusSchedule> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list.get(0));
			return true;
		}else{
			logger.warn("no exist this BusSchedule busId:"+busId);
			return false;
		}
	}

	@Override
	public boolean deleteBusStation(int busStationId) {
		String sql = " from BusStation where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", busStationId);
		List<BusStation> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			baseDao.delete(list.get(0));
			return true;
		}else{
			logger.warn("no exist this BusStation id:"+busStationId);
			return false;
		}
	}

	@Override
	public boolean updateBusInfo(BusInfo busInfo) {
		
		String sql = " from BusInfo where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", busInfo.getId());
		List<BusInfo> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			BusInfo businfo = list.get(0);
			businfo.setCode(busInfo.getCode());
			businfo.setStartTime(busInfo.getStartTime());
			businfo.setEndTime(busInfo.getEndTime());
			baseDao.update(businfo);
			return true;
		}else{
			logger.warn(" no this BusInfo id : " + busInfo.getId());
			return false;
		}
	}

	@Override
	public boolean updateBusSchedule(BusSchedule busSchedule) {
		String sql = " from BusSchedule where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", busSchedule.getId());
		List<BusSchedule> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			BusSchedule busschedule = list.get(0);
			busschedule.setBusId(busSchedule.getBusId());
			busschedule.setSortNum(busSchedule.getSortNum());
			busschedule.setStationId(busSchedule.getStationId());
			baseDao.update(busschedule);
			return true;
		}else{
			logger.warn(" no this BusSchedule id : " + busSchedule.getId());
			return false;
		}
		
	}

	@Override
	public boolean updateBusStation(BusStation busStation) {
		String sql = " from BusStation where id =:id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", busStation.getId());
		List<BusStation> list = baseDao.find(sql, params);
		if(list != null && !list.isEmpty()){
			BusStation busstation = list.get(0);
			busstation.setStationName(busStation.getStationName());
			busstation.setLon(busStation.getLon());
			busstation.setLat(busStation.getLat());
			baseDao.update(busstation);
			return true;
		}else{
			logger.warn(" no this BusStation id : " + busStation.getId());
			return false;
		}
	}

	@Override
	public int insertBusInfo(BusInfo busInfo) {
		BusInfo busInfoFromTable = this.getBusInfo(busInfo.getCode());
		if(busInfoFromTable == null){
			return Integer.parseInt(baseDao.save(busInfo).toString());
		}else{
			busInfoFromTable.setStartTime(busInfo.getStartTime());
			busInfoFromTable.setEndTime(busInfo.getEndTime());
			baseDao.update(busInfoFromTable);
			logger.warn("相同 BusCode : "+ busInfo.getCode() +" 已经存在,已更新");
			return busInfoFromTable.getId();
		}
	}

	@Override
	public int insertBusSchedule(BusSchedule busSchedule) {
		return Integer.parseInt(baseDao.save(busSchedule).toString());
	}

	@Override
	public int insertBusStation(BusStation busStation) {
		BusStation bs = this.getBusStation(busStation.getStationName());
		if(bs == null){
			return Integer.parseInt(baseDao.save(busStation).toString());
		}else{
			bs.setLon(busStation.getLon());
			bs.setLat(busStation.getLat());
			baseDao.update(bs);
			logger.warn("相同 busStation : "+ busStation.getStationName() +" 已经存在,已更新");
			return bs.getId();
		}
	}

	@Override
	public BusStation getBusStation(String name) {
		String sql = " from BusStation where stationName =:stationName ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stationName", name);
		List<BusStation> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
