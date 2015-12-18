package org.st.smartnation.dao;

import java.util.List;

import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.model.BusInfo;
import org.st.smartnation.model.BusSchedule;
import org.st.smartnation.model.BusStation;

public interface BusDao {

	public List<BusInfo> getBusInfo();
	public BusInfo getBusInfo(String code);
	public List<BusSchedule> getBusSchedule();
	public BusSchedule getBusSchedule(int busId);
	public BusStation getBusStation(int id);
	public BusStation getBusStation(String name);
	public List<BusDetail> getBusDetail(String code);
	
	public boolean insertBusInfo(List<BusInfo> busInfos);
	public int insertBusInfo(BusInfo busInfo);
	public boolean insertBusSchedule(List<BusSchedule> busSchedules);
	public int insertBusSchedule(BusSchedule busSchedule);
	public boolean insertBusStation(List<BusStation> busStations);
	public int insertBusStation(BusStation busStation);
	
	public boolean deleteBusInfo(String code);
	public boolean deleteBusSchedule(int busScheduleId);
	public boolean deleteBusStation(int busStationId);
	
	public boolean updateBusInfo(BusInfo busInfo);
	public boolean updateBusSchedule(BusSchedule busSchedule);
	public boolean updateBusStation(BusStation busStation);
}
