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
import org.example.beans.HRMessage;
import org.example.utils.FunctionUtil;
import org.example.utils.GsonUtil;
import org.example.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/hrmessage")
public class HRMessageController {

	private static String token = FunctionUtil.loadProperties("token");
	private static String hrurl = FunctionUtil.loadProperties("hrurl");
	
	@RequestMapping("/getHRMessageData")
	@ResponseBody
	public String getHRMessageData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
//		List<HRMessage> hrMessages = new ArrayList<HRMessage>();
//		HRMessage hrMessage = null;
//		String[][] datas = new String[][]{
//				{"1","name1","1438905000000","1.2904640","103.86040","this is content1","title1"},
//				{"2","name2","1438908000000","1.2915641","103.87042","this is content2","title2"},
//				{"3","name3","1438821600000","1.2916651","103.88043","this is content3","title3"},
//				{"4","name4","1438827600000","1.2916544","103.84533","this is content4","title4"},
//				{"5","name5","1439004600000","1.2914522","103.85642","this is content5","title5"}
//		};
//		for(int i = 0;i < datas.length;i++)
//		{
//			hrMessage = new HRMessage();
//			hrMessage.setId(Integer.parseInt(datas[i][0]));
//			hrMessage.setName(datas[i][1]);
//			hrMessage.setTime(Long.parseLong(datas[i][2]));
//			hrMessage.setLon(datas[i][3]);
//			hrMessage.setLat(datas[i][4]);
//			hrMessage.setContent(datas[i][5]);
//			hrMessage.setTitle(datas[i][6]);
//			hrMessages.add(hrMessage);
//		}
//		String str = GsonUtil.objectToJson(hrMessages);
//		return str;
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(hrurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getAllHRMessageInfo");
		//Method method=objs[0].getClass().getMethod("getItem",null);
//		List invokeList = (List) method.invoke(objs[0], null);
		List invokeList = (ArrayList)objs[0];
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
		}
		return result;
	}
	
	@RequestMapping("/getHRMessageDataByPage")
	@ResponseBody
	public String getHRMessageDataByPage(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		int index = Integer.parseInt(request.getParameter("index"));
		int size = Integer.parseInt(request.getParameter("size"));
		List<HRMessage> hrMessages = new ArrayList<HRMessage>();
		HRMessage hrMessage = null;
		String[][] datas = new String[][]{
				{"1","name1","1438905000000","1.2904640","103.86040","this is content1","title1","Meetting","Bef Jln Chempaka Kuning","mwsf-2;mwsf-4;mwsf-5;mwsf-6","1.jpg"},
				{"2","name2","1438908000000","1.2915641","103.87042","this is content2","title2","Activity","Bef Jln Chempaka Kuning","mwsf-2;mwsf-5","2.jpg"},
				{"3","name3","1438821600000","1.2916651","103.88043","this is content3","title3","Inform","Bef Jln Chempaka Kuning","mwsf-9;mwsf-14;mwsf-16","3.jpg"},
				{"4","name4","1438827600000","1.2916544","103.84533","this is content4","title4","News","Bef Jln Chempaka Kuning","mwsf-20;mwsf-21;mwsf-24;mwsf-25","4.jpg"},
				{"5","name5","1439004600000","1.2914522","103.85642","this is content5","title5","Motto","Bef Jln Chempaka Kuning","mwsf-27;mwsf-28;mwsf-29;mwsf-30;mwsf-31","5.jpg"},
				{"6","Joseph Addison","1438905000000","1.2904640","103.86040","A contented mind is the greatest blessing a man can enjoy in this world.","Meetting","Bef Jln Chempaka Kuning","mwsf-2;mwsf-4;mwsf-5;mwsf-6","1.jpg"},
				{"7","Benjamin Franklin","1438908000000","1.2915641","103.87042","If you would know the value of money, go and try to borrow some","Activity","Bef Jln Chempaka Kuning","mwsf-2;mwsf-5","2.jpg"},
				{"8","Thomas Edison","1438821600000","1.2916651","103.88043","If you wish to succeed, you should use persistence as your good friend,","Inform","Bef Jln Chempaka Kuning","mwsf-9;mwsf-14;mwsf-16","3.jpg"},
				{"9","Samuel Johnson","1438827600000","1.2916544","103.84533","Health is certainly more valuable than money,","News","Bef Jln Chempaka Kuning","mwsf-20;mwsf-21;mwsf-24;mwsf-25","4.jpg"},
				{"10","Henry David Thoreau","1439004600000","1.2914522","103.85642","That man is the richest whose pleasure are the cheapest.","Motto","Bef Jln Chempaka Kuning","mwsf-27;mwsf-28;mwsf-29;mwsf-30;mwsf-31","5.jpg"}
		};
		for(int i = index;i < (size + index - 1);i++)
		{
			hrMessage = new HRMessage();
			hrMessage.setId(Integer.parseInt(datas[i][0]));
			hrMessage.setName(datas[i][1]);
			hrMessage.setTime(Long.parseLong(datas[i][2]));
			hrMessage.setLon(datas[i][3]);
			hrMessage.setLat(datas[i][4]);
			hrMessage.setContent(datas[i][5]);
			hrMessage.setTitle(datas[i][6]);
			hrMessage.setImgs(datas[i][7]);
			hrMessage.setIcon(datas[i][8]);
			hrMessages.add(hrMessage);
		}
		String str = GsonUtil.objectToJson(hrMessages);
		return str;
	}
	@RequestMapping("/getHRMessageDataByLength")
	@ResponseBody
	public List getHRMessageDataByLength(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, String> params = HttpUtil.getParameters(request, "utf-8");
		String _len = params.get("length");
		int length=0;
		if(_len!=null&&_len.trim().length()>0)
		{
			try{
				length=Integer.parseInt(_len.trim());
			}catch(Exception ex)
			{
				return null;
			}
		}
		String start;//start from 0
//		List<HRMessage> hrMessages = new ArrayList<HRMessage>();
//		HRMessage hrMessage = null;
//		String[][] datas = new String[][]{
//				{"1","name1","1438905000000","1.2904640","103.86040","this is content1","title1"},
//				{"2","name2","1438908000000","1.2915641","103.87042","this is content2","title2"},
//				{"3","name3","1438821600000","1.2916651","103.88043","this is content3","title3"},
//				{"4","name4","1438827600000","1.2916544","103.84533","this is content4","title4"},
//				{"5","name5","1439004600000","1.2914522","103.85642","this is content5","title5"}
//		};
//		for(int i = 0;i < datas.length;i++)
//		{
//			hrMessage = new HRMessage();
//			hrMessage.setId(Integer.parseInt(datas[i][0]));
//			hrMessage.setName(datas[i][1]);
//			hrMessage.setTime(Long.parseLong(datas[i][2]));
//			hrMessage.setLon(datas[i][3]);
//			hrMessage.setLat(datas[i][4]);
//			hrMessage.setContent(datas[i][5]);
//			hrMessage.setTitle(datas[i][6]);
//			hrMessages.add(hrMessage);
//		}
//		String str = GsonUtil.objectToJson(hrMessages);
//		return str;
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(hrurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getAllHRMessageInfo");
		//Method method=objs[0].getClass().getMethod("getItem",null);
//		List invokeList = (List) method.invoke(objs[0], null);
		List invokeList = (ArrayList)objs[0];
		List<Map<String, Object>> datas = new ArrayList<Map<String,Object>>();
		if(invokeList.size()>0)
		{
			for(int i = 0;i < invokeList.size()&&i<length;i++)
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
			//result = GsonUtil.objectToJson(datas);
		}
		return datas;
	}
	
	@RequestMapping("/getNextHRMessageInfo")
	@ResponseBody
	public String getNextHRMessageInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println(token + "   " + hrurl);
//		int index = Integer.parseInt(request.getParameter("index"));
//		int size = Integer.parseInt(request.getParameter("size"));
		String index = request.getParameter("index");
		String size = request.getParameter("size");
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(hrurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getNextHRMessageInfo",index,size);
		String str = "";
		List list= (ArrayList)objs[0];
		System.out.println("getHRMessageInfo: "+list);
		return GsonUtil.objectToJson(list);
	}
}
