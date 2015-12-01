package org.example.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.example.model.Camera;
import org.example.service.TrafficService;
import org.example.utils.GsonUtil;
import org.example.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/traffic")
public class TrafficController {

	@Autowired
	private TrafficService trafficService;
	
	@RequestMapping("/allcameras")
	@ResponseBody
	public String getCameraInfos()
	{
		List<Camera> list = trafficService.getTraficCameraInfo();
		return GsonUtil.objectToJson(list);
	}
	
	@RequestMapping("/cameras")
	@ResponseBody
	public String getCameraInfo(HttpServletResponse response,@RequestParam("roads")String roads)
	{
		List<Camera> list = trafficService.getTraficCameraInfo(roads);
		response.setHeader("Cache-Control", "max-age=60");
		return GsonUtil.objectToJson(list);
	}
	
	@RequestMapping("/incident")
	@ResponseBody
	public String getIncidentInfo()
	{
		String incidentInfo = HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/IncidentSet", null);
		JSONObject jsonObject = JSONObject.fromObject(incidentInfo);
	    JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
	    int size = jsonArray.size();
	    JSONArray newJsonArray = new JSONArray();
	    for(int i= 0 ;i<size;i++){
	    	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		    json.remove("__metadata");
		    newJsonArray.add(json);
	    }
		return newJsonArray.toString();
	}
}
