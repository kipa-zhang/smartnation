package org.example.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.model.Camera;
import org.example.utils.GsonUtil;
import org.example.utils.http.HttpRequester;
import org.example.utils.http.HttpRespons;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/weather")
public class WeatherController {

	@RequestMapping("/test")
	@ResponseBody
	public String getCameraInfo()
	{
		
		return "test";
	}
	@RequestMapping(value="/forecast/{woeid}", method={RequestMethod.GET})
	public ResponseEntity<String> getForecastByWoeid(HttpServletRequest request,HttpServletResponse response,@PathVariable("woeid") String woeid)
	{
		System.out.println("XX:weather");
		HttpRequester req = new HttpRequester();  
		req.setDefaultContentEncoding("utf-8");  
		Map<String,String> params=new HashMap<String,String>();
		
		HttpRespons hr;
		String content ;
		try {
			params.put("q", java.net.URLEncoder.encode("select * from weather.forecast where woeid="+woeid,"utf-8"));
			params.put("format", "json");
			params.put("env", java.net.URLEncoder.encode("store://datatables.org/alltableswithkeys","utf-8"));
			hr = req.sendGet("https://query.yahooapis.com/v1/public/yql", params);
			content= hr.getContent();
		} catch (Exception e) {
			content=e.getMessage();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(content,headers, HttpStatus.OK);
	}
	@RequestMapping(value="/woeid/{name}", method={RequestMethod.GET})
	public ResponseEntity<String> getWoeidByName(HttpServletRequest request,HttpServletResponse response,@PathVariable("name") String name)
	{
		HttpRequester req = new HttpRequester();  
		req.setDefaultContentEncoding("utf-8");  
		Map<String,String> params=new HashMap<String,String>();
		HttpRespons hr;
		String content ;
		try {
			params.put("q", java.net.URLEncoder.encode("select woeid from geo.places(1) where text="+name,"utf-8"));
			params.put("format", "json");
			params.put("env", java.net.URLEncoder.encode("store://datatables.org/alltableswithkeys","utf-8"));
			hr = req.sendGet("https://query.yahooapis.com/v1/public/yql", params);
			content= hr.getContent();
		} catch (Exception e) {
			content=e.getMessage();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(content,headers, HttpStatus.OK);
	}
	
	public static void main(String[] args) {
		System.out.println(java.net.URLEncoder.encode("select * from weather.forecast where woeid=utf-8"));
	}
}
