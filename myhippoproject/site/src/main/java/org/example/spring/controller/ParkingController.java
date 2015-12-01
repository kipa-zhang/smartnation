package org.example.spring.controller;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
@RequestMapping("/parking")
public class ParkingController {
	private static String token = FunctionUtil.loadProperties("token");
	private static String parkurl = FunctionUtil.loadProperties("parkurl");
	@RequestMapping("/getParkingData")
	@ResponseBody
	public String getParkingData(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println(token + parkurl);
		String result = "";
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		System.out.println("aaa");
		Client client = dcf.createClient(parkurl);
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		Object[] objs = client.invoke("getAllParkingInfo");
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
		System.out.println(result);
		return result;
	}
}
