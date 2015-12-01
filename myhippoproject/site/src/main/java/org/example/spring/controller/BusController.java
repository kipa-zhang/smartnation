package org.example.spring.controller;

import java.util.List;

import org.example.model.BusInfo;
import org.example.model.BusRoute;
import org.example.model.BusStop;
import org.example.service.BusService;
import org.example.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@RequestMapping("/busstops")
	@ResponseBody
	public String getBusStops()
	{
		List<BusStop> list = busService.getBusStopInfo();
		return GsonUtil.objectToJson(list);
	}
	
	@RequestMapping("/busroutes")
	@ResponseBody
	public String getBusRoutes()
	{
		List<BusRoute> list = busService.getBusRouteInfo();
		return GsonUtil.objectToJson(list);
	}
	
	@RequestMapping("/busroute")
	@ResponseBody
	public String getBusRoute(@RequestParam("busNo")String busNo)
	{
		BusRoute busRoute = busService.getBusRoute(busNo);
		return GsonUtil.objectToJson(busRoute);
	}
	
	@RequestMapping("/businfos")
	@ResponseBody
	public String getBusInfos()
	{
		List<BusInfo> busInfos = busService.getBusInfoInfo();
		return GsonUtil.objectToJson(busInfos);
	}
	
	@RequestMapping("/businfo")
	@ResponseBody
	public String getBusInfo(@RequestParam("busNo")String busNo)
	{
		BusInfo busInfo = busService.getBusInfo(busNo);
		return GsonUtil.objectToJson(busInfo);
	}
}
