package org.example.service;

import java.util.List;
import java.util.Map;

import org.example.model.BusInfo;
import org.example.model.BusRoute;
import org.example.model.BusStop;

public interface BusService {

	public void updateBusStopInfo(List<BusStop> busStopList);
	public List<BusStop> getBusStopInfo();
	/**
	 * no ready
	 * @param params
	 * @return
	 */
	public List<BusStop> getBusStopInfo(Map<String,Object> params);
	
	public void updateBusRoute(List<BusRoute> busRouteList);
	public List<BusRoute> getBusRouteInfo();
	public BusRoute getBusRoute(String busNo);
	
	public void updateBusInfo(List<BusInfo> busInfoInfo);
	public List<BusInfo> getBusInfoInfo();
	public BusInfo getBusInfo(String busNo);
}
