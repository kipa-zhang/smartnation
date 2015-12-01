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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.st.ws.service.CorporateDate;




@Controller
@RequestMapping("/corporateDate")
public class CorporateDateController {
	public static final Logger log = LoggerFactory.getLogger(CorporateDateController.class);
	
	private static String token = FunctionUtil.loadProperties("token");
	private static String corporateurl = FunctionUtil.loadProperties("corporateurl");
	
	@RequestMapping("/fortest")
	@ResponseBody
    public String sayHi(HttpServletRequest request,HttpServletResponse response)
    {
    	return "测试";
    }
	
	@RequestMapping("/transferDatas")
	@ResponseBody
	public String transferDatas(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String result = "";
		// 生成客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		Client client =dcf.createClient("http://10.41.87.107:8280/eventdata/1.0.0?wsdl", new QName("tns", "CameraServer"));
		Client client = dcf.createClient(corporateurl);
		// 添加 Header 头信息
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		// 调用  Webservice 方法
		Object[] objs = client.invoke("getAllCorporatedateInfo");
		
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
		}
		return result;
	}

	@RequestMapping("/saveCorporate")
	@ResponseBody
	public int saveCorporate(@ModelAttribute("corporateDate") CorporateDate corporateDate) throws Exception{
		
		// 创建客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(corporateurl);
		// Header头信息
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		// 调用 webservice 的 saveCorporatedateInfo 方法，并传递对象
		// objs 为调用返回的结果
		Object[] objs = client.invoke("saveCorporatedateInfo", corporateDate);
		
		//这里只返回一个  int 类型数据，故直接(Integer)objs[0]接受数据
		if(objs != null && objs.length > 0){
			System.out.println("!!!!!!!!" + (Integer)objs[0]);
			return (Integer)objs[0];
		}
		return 0;
	}
	@RequestMapping("/anosaveCorporate")
	@ResponseBody
	public int anosaveCorporate(@ModelAttribute("corporateDate")CorporateDate corporateDate) throws Exception{
		
		// 创建客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(corporateurl);
		// Header头信息
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		// 调用 webservice 的 saveCorporatedateInfo 方法，并传递对象
		// objs 为调用返回的结果
		Object anocorporateDate = Thread.currentThread().getContextClassLoader().loadClass("com.st.ws.service.CorporateDate").newInstance();
		Field[] fields = anocorporateDate.getClass().getDeclaredFields();
		System.out.println("111-1-1-1-1-1-1-" + fields.length);
		for(Field field : fields)
		{
			Method m = null;
			System.out.println("-------------" + field.getName());
			
			switch(field.getName())
			{
				case "id":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),int.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,3);
					break;
				case "name":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),String.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,"a");
					break;
				case "start":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),long.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,1);
					break;
				case "end":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),long.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,1);
					break;
				case "date":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),long.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,1);
					break;
				case "describ":
					m = (Method) anocorporateDate.getClass().getMethod(  
							"set" + FunctionUtil.getMethodName(field.getName()),String.class);
					if(m == null)
						continue;
					m.invoke(anocorporateDate,"aaa");
					break;
			}
			
		}
		for(Field field : fields)
		{
			Method m = (Method) anocorporateDate.getClass().getMethod(  
					"get" + FunctionUtil.getMethodName(field.getName()));
			if(m == null)
				continue;
			Object val = m.invoke(anocorporateDate);
			System.out.println("1-1-1-1-11-1-1-11-1-1-" + val.toString());
		}
//		Object[] objs = client.invoke("saveCorporatedateInfo", corporateDate);
//		
//		//这里只返回一个  int 类型数据，故直接(Integer)objs[0]接受数据
//		if(objs != null && objs.length > 0){
//			System.out.println("!!!!!!!!" + (Integer)objs[0]);
//			return (Integer)objs[0];
//		}
		return 0;
	}
	
}
