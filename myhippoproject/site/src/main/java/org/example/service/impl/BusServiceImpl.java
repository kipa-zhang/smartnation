package org.example.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.model.BusInfo;
import org.example.model.BusRoute;
import org.example.dao.BaseDao;
import org.example.model.BusStop;
import org.example.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BusServiceImpl implements BusService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	@Transactional
	public void updateBusStopInfo(List<BusStop> busStopList) {
		if(busStopList!= null){
			//查询已有信息
			List<BusStop> existBusStops = baseDao.find("from BusStop");
			
			//比较，如果数据已近存在，则 update ，否则插入新增的 camera
			for(BusStop busStop:busStopList){
				boolean ifExist = false;
				for(BusStop existBusStop:existBusStops){
					if(busStop.getBusStopCode() == existBusStop.getBusStopCode()){
						existBusStop.setDescription(busStop.getDescription());
						existBusStop.setRoad(busStop.getRoad());
						existBusStop.setLon(busStop.getLon());
						existBusStop.setLat(busStop.getLat());
						baseDao.update(existBusStop);
						ifExist = true;
					}
				}
				if(!ifExist){
					baseDao.save(busStop);
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(Calendar.getInstance().getTime());
			System.out.println(date+" : End update BusStop Info !");
		}
		else
			System.out.println("update BusStop Info data failed!");
	}

	@Override
	@Transactional
	public List<BusStop> getBusStopInfo() {
		List<BusStop> busStops = baseDao.find("from BusStop");
		return busStops;
	}

	@Override
	@Transactional
	public List<BusStop> getBusStopInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void updateBusRoute(List<BusRoute> busRouteList) {
		if(busRouteList!= null){
			//查询已有信息
			List<BusRoute> existBusRoutes = baseDao.find("from BusRoute");
			
			//比较，如果数据已近存在，则 update ，否则插入新增的 camera
			for(BusRoute busRoute:busRouteList){
				boolean ifExist = false;
				for(BusRoute existBusRoute:existBusRoutes){
					if(busRoute.getSR_SVC_NUM() == existBusRoute.getSR_SVC_NUM()){
						
						existBusRoute.setTransportOperatorCode(busRoute.getTransportOperatorCode());
						existBusRoute.setSR_DISTANCE(busRoute.getSR_DISTANCE());
						existBusRoute.setSR_FST_SAT(busRoute.getSR_FST_SAT());
						existBusRoute.setSR_FST_SUN(busRoute.getSR_FST_SUN());
						existBusRoute.setSR_FST_WD(busRoute.getSR_FST_WD());
						existBusRoute.setSR_LST_SAT(busRoute.getSR_LST_SAT());
						existBusRoute.setSR_LST_SUN(busRoute.getSR_LST_SUN());
						existBusRoute.setSR_LST_WD(busRoute.getSR_LST_WD());
						existBusRoute.setSR_ROUT_SEQ(busRoute.getSR_ROUT_SEQ());
						existBusRoute.setSR_SVC_DIR(busRoute.getSR_SVC_DIR());
						existBusRoute.setSR_BS_CODE(busRoute.getSR_BS_CODE());
						baseDao.update(existBusRoute);
						ifExist = true;
					}
				}
				if(!ifExist){
					baseDao.save(busRoute);
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(Calendar.getInstance().getTime());
			System.out.println(date+" : End update BusRoute Info !");
		}
		else
			System.out.println("update BusRoute Info data failed!");
	}

	@Override
	@Transactional
	public List<BusRoute> getBusRouteInfo() {
		List<BusRoute> busRoute = baseDao.find("from BusRoute");
		return busRoute;
	}

	@Override
	@Transactional
	public void updateBusInfo(List<BusInfo> busInfoInfo) {
		if(busInfoInfo!= null){
			//查询已有信息
			List<BusInfo> existBusInfos = baseDao.find("from BusInfo");
			
			//比较，如果数据已近存在，则 update ，否则插入新增的 camera
			for(BusInfo busInfo:busInfoInfo){
				boolean ifExist = false;
				for(BusInfo existBusInfo:existBusInfos){
					if(busInfo.getSI_SVC_NUM() == existBusInfo.getSI_SVC_NUM()){
						existBusInfo.setTransportOperatorCode(busInfo.getTransportOperatorCode());
						existBusInfo.setSI_SVC_DIR(busInfo.getSI_SVC_DIR());
						existBusInfo.setSI_SVC_CAT(busInfo.getSI_SVC_CAT());
						existBusInfo.setSI_BS_CODE_ST(busInfo.getSI_BS_CODE_ST());
						existBusInfo.setSI_BS_CODE_END(busInfo.getSI_BS_CODE_END());
						existBusInfo.setSI_FREQ_AM_PK(busInfo.getSI_FREQ_AM_PK());
						existBusInfo.setSI_FREQ_AM_OF(busInfo.getSI_FREQ_AM_OF());
						existBusInfo.setSI_FREQ_PM_PK(busInfo.getSI_FREQ_PM_PK());
						existBusInfo.setSI_FREQ_PM_OF(busInfo.getSI_FREQ_PM_OF());
						existBusInfo.setSI_LOOP(busInfo.getSI_LOOP());
						baseDao.update(existBusInfo);
						ifExist = true;
					}
				}
				if(!ifExist){
					baseDao.save(busInfo);
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(Calendar.getInstance().getTime());
			System.out.println(date+" : End update BusRoute Info !");
		}
		else
			System.out.println("update BusRoute Info data failed!");
		System.out.println(" 更新数据 大小 ：  " + busInfoInfo.size());
		
	}

	@Override
	@Transactional
	public List<BusInfo> getBusInfoInfo() {
		List<BusInfo> busInfo = baseDao.find("from BusInfo");
		return busInfo;
	}

	@Override
	@Transactional
	public BusRoute getBusRoute(String busNo) {
		String sql = " from BusRoute where SR_SVC_NUM=:busNo ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busNo", busNo);
		List<BusRoute> list = baseDao.find(sql, params);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	@Transactional
	public BusInfo getBusInfo(String busNo) {
		String sql = " from BusInfo where SI_SVC_NUM=:busNo ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busNo", busNo);
		List<BusInfo> list = baseDao.find(sql, params);
		if(list != null && list.size()>0)
			return list.get(0);
		return null;
	}

}
