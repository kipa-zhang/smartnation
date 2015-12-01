package org.example.spring.controller;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.example.utils.FunctionUtil;
import org.example.utils.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/busschedule")
public class BusScheduleController {
	
	private static String token = FunctionUtil.loadProperties("token");
	private static String busurl = FunctionUtil.loadProperties("busurl");
	private Client client;
	
	@RequestMapping("/dao")
	@ResponseBody
	public String getInfo(){
		return "KIPA";
	}
	
	@RequestMapping("/getBusStation")
	@ResponseBody
	public String getBusStation(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String busId = request.getParameter("id");
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(busurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getBusStation", busId);
		Method method = objs[0].getClass().getMethod("getItem",null);
		List invokeList = (List) method.invoke(objs[0], null);
		if(invokeList.size()>0)
		{
			List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
			for(int i = 0;i < invokeList.size();i++)
			{
				Map<String, Object> data = new HashMap<String, Object>();
				Class<?> clazz = invokeList.get(i).getClass();
				Field[] fields = clazz.getDeclaredFields();
				
				for(Field field : fields)
				{
					Method m = (Method) invokeList.get(i).getClass().getMethod(  
							"get" + FunctionUtil.getMethodName(field.getName()));
					if(m == null)
						continue;
					Object val = m.invoke(invokeList.get(i));
					data.put(field.getName(), val);
				}
				datas.add(data);
			}
			result = GsonUtil.objectToJson(datas);
		}
		return result;
	}
	
	@RequestMapping("/getBusSchedules")
	@ResponseBody
	public String getBusSchedules(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(busurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getBusSchedules");
		Method method = objs[0].getClass().getMethod("getItem",null);
		List invokeList = (List)method.invoke(objs[0], null);
		if(invokeList.size()>0)
		{
			List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
			for(int i = 0;i < invokeList.size();i++)
			{
				Map<String, Object> data = new HashMap<String, Object>();
				Class<?> clazz = invokeList.get(i).getClass();
				Field[] fields = clazz.getDeclaredFields();
				
				for(Field field : fields)
				{
					Method m = (Method) invokeList.get(i).getClass().getMethod(  
							"get" + FunctionUtil.getMethodName(field.getName()));
					if(m == null)
						continue;
					Object val = m.invoke(invokeList.get(i));
					data.put(field.getName(), val);
				}
				datas.add(data);
			}
			result = GsonUtil.objectToJson(datas);
		}
		return result;
	}
	
	@RequestMapping("/getStations")
	@ResponseBody
	public String getStations(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(busurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getStations");
		Method method=objs[0].getClass().getMethod("getItem",null);
		List invokeList = (List) method.invoke(objs[0], null);
		if(invokeList.size()>0)
		{
			List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
			for(int i = 0;i < invokeList.size();i++)
			{
				Map<String, Object> data = new HashMap<String, Object>();
				Class<?> clazz = invokeList.get(i).getClass();
				Field[] fields = clazz.getDeclaredFields();
				
				for(Field field : fields)
				{
					Method m = (Method) invokeList.get(i).getClass().getMethod(  
							"get" + FunctionUtil.getMethodName(field.getName()));
					if(m == null)
						continue;
					Object val = m.invoke(invokeList.get(i));
					data.put(field.getName(), val);
				}
				datas.add(data);
			}
			result = GsonUtil.objectToJson(datas);
		}
		return result;
	}
	@RequestMapping("/getBusSchedule")
	@ResponseBody
	public String getBusSchedule(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		System.out.println("aaa");
		Client client = dcf.createClient(busurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getBusSchedule","1");
//		Method method = objs[0].getClass().getMethod("getItem",null);
//		List invokeList = (List) method.invoke(objs[0], null);
		Object o = objs[0];
		if(o != null)
		{
			Map<String, Object> data = new HashMap<String, Object>();
			Class<?> clazz = o.getClass();
			Field[] fields = clazz.getDeclaredFields();
			
			for(Field field : fields)
			{
				Method m = (Method) o.getClass().getMethod(  
						"get" + FunctionUtil.getMethodName(field.getName()));
				if(m == null)
					continue;
				Object val = m.invoke(o);
				data.put(field.getName(), val);
			}
			System.out.println("----------------------");
			result = GsonUtil.objectToJson(data);
		}
		return result;
	}
	
	
}
