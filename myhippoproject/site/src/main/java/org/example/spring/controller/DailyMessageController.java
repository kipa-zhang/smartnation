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
import org.example.beans.DailyMessage;
import org.example.utils.FunctionUtil;
import org.example.utils.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dailymessage")
public class DailyMessageController {

	private static String token = FunctionUtil.loadProperties("token");
	private static String dailyurl = FunctionUtil.loadProperties("dailyurl");
	
	@RequestMapping("/getDailyMessageData")
	@ResponseBody
	public String getDailyMessageData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
//		List<DailyMessage> dailyMessages = new ArrayList<DailyMessage>();
//		DailyMessage dailyMessage = null;
//		String[][] datas = new String[][]{
//				{"1","name1","1438905000000","1.2904640","103.86040","this is content1","title1","this is for test,desc1"},
//				{"2","name2","1438908000000","1.2915641","103.87042","this is content2","title2","this is for test,desc2"},
//				{"3","name3","1438821600000","1.2916651","103.88043","this is content3","title3","this is for test,desc3"},
//				{"4","name4","1438827600000","1.2916544","103.84533","this is content4","title4","this is for test,desc4"},
//				{"5","name5","1439004600000","1.2914522","103.85642","this is content5","title5","this is for test,desc5"}
//		};
//		for(int i = 0;i < datas.length;i++)
//		{
//			dailyMessage = new DailyMessage();
//			dailyMessage.setId(Integer.parseInt(datas[i][0]));
//			dailyMessage.setTime(Long.parseLong(datas[i][2]));
//			dailyMessage.setTitle(datas[i][6]);
//			dailyMessage.setContent(datas[i][5]);
//			dailyMessage.setDesc(datas[i][7]);
//			dailyMessages.add(dailyMessage);
//		}
//		String str = GsonUtil.objectToJson(dailyMessages);
//		return str;
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(dailyurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getAllDailyMessageInfo");
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
				System.out.println("----------------------");
			}
			result = GsonUtil.objectToJson(datas);
			System.out.println(result);
		}
		return result;
	}
	@RequestMapping("/getDailyMessageInfo")
	@ResponseBody
	public String getDailyMessageInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String title = request.getParameter("title");
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		System.out.println("aaa");
		Client client = dcf.createClient(dailyurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getDailyMessageInfo",title);
		if(objs == null)
			return "";
		Object obj = objs[0];
		if(obj != null)
		{
			Map<String, Object> data = new HashMap<String, Object>();
			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			
			for(Field field : fields)
			{
				Method m = (Method) obj.getClass().getMethod(  
						"get" + FunctionUtil.getMethodName(field.getName()));
				if(m == null)
					continue;
				Object val = m.invoke(obj);
				data.put(field.getName(), val);
			}
			System.out.println("----------------------");
			result = GsonUtil.objectToJson(data);
		}
		return result;
	}
}
