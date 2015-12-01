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
import javax.servlet.http.HttpSession;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.example.model.Hobby;
import org.example.model.Topic;
import org.example.model.bean.Result;
import org.example.service.HobbiesService;
import org.example.utils.FunctionUtil;
import org.example.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
@RequestMapping("/hobby")
public class HobbyController {

//	private static String token = FunctionUtil.loadProperties("token");
//	private static String hobbyurl = FunctionUtil.loadProperties("hobbyurl");
//	private Client client;
	
	@Autowired
	private HobbiesService hobbiesService;
	
	@RequestMapping("/hi")
	@ResponseBody
	public String hi()
	{
		return "hello";
	}
	
	@RequestMapping("/recommend_topic")
	@ResponseBody
	public Result recommendTopic(HttpServletRequest req, HttpServletResponse response) throws Exception{
		//int userId = Integer.parseInt(request.getParameter("userid"));
		//String result = "";
		HttpSession session = req.getSession();
		int _userId=0;
		Result<Object> res=new Result<Object>();
		if(session!=null&&session.getAttribute("UserId")!=null)
		{
			_userId=Integer.parseInt(session.getAttribute("UserId").toString());
			List<Map> hobbies = hobbiesService.recommendTopics(_userId);
			res.setStatus("success");
			res.setResult(hobbies);
			return res;
		}
		else
		{
			
			res.setMessage("请登录");
			res.setStatus("error");
			return res;
		}
//		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		client = dcf.createClient(hobbyurl);
//		Map<String, List<String>> headers = new HashMap<String, List<String>>();
//		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
//		headers.put("Authorization", Arrays.asList(token));
//		Object[] objs = client.invoke("recommendTopic", userId);
//		Method method = objs[0].getClass().getMethod("getItem", null);
//		List invokeList = (List) method.invoke(objs[0], null);
//		if(!invokeList.isEmpty()){
//			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
//			for(int i = 0; i < invokeList.size(); i++){
//				Map<String, Object> data = new HashMap<String, Object>();
//				Class<?> clazz = invokeList.get(i).getClass();
//				Field[] fields = clazz.getDeclaredFields();
//				
//				for(Field field : fields)
//				{
//					Method m = (Method) invokeList.get(i).getClass().getMethod(  
//							"get" + FunctionUtil.getMethodName(field.getName()));
//					if(m == null)
//						continue;
//					Object val = m.invoke(invokeList.get(i));
//					data.put(field.getName(), val);
//				}
//				datas.add(data);
//			}
//			result = GsonUtil.objectToJson(datas);
//		}
		
	}
}
