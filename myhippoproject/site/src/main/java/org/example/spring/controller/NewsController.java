package org.example.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.message.Message;
import org.example.beans.Event;
import org.example.beans.Traffic;
import org.example.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tns.StringArray;

@Controller
@RequestMapping("/news")
public class NewsController {
	private Client client;
	public static final Logger log = LoggerFactory.getLogger(NewsController.class);
	/*it is the am token used for access the api you subscription in the am store,when you want to access the api,
	 you should generate a useful token,then edit the param below*/
	private static final String token = "Bearer 56fc1f3594b35c38ef1f4d7b57c6fb96";
	
	@RequestMapping(value={"", "/"})
    @ResponseBody
    public String listHandler(HttpServletRequest request) {

    	String returnInfo = packingInfo(getInfo());
    	System.out.println("returnInfo : "+returnInfo);
    	return returnInfo;
    }
    
    private String getInfo(){
    	JaxWsDynamicClientFactory factory =JaxWsDynamicClientFactory.newInstance();
        //from wso2
//    	Client client =factory.createClient("http://10.41.87.107:9764/services/PMWebService?wsdl");
    	Client client =factory.createClient("http://10.41.87.107:8280/pmservice/1.0.0?wsdl");
    	Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
		//from gis
    	//Wrong Info: tns.StringArray cannot be cast to java.lang.String
    	//Client client =factory.createClient("http://10.41.87.37:7789/snt?wsdl");
        
        Object[] obj = null;
		try {
			//from wso2
			obj = client.invoke("getPMInfo");
			
			//from gis
			//obj = client.invoke("getAccidentDataforCamera");
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(obj != null)
        	return (String) obj[0];
        return null;
    }
    
    /**
     * @param info - String
     * @return String packed in JSON
     */
    private String packingInfo(String info){
    	String result = info;
    	if(info != null){
    		result = "{\"ISSUCCESS\":\"TRUE\",\"INFO\":" + result + "}";
    	}else
    		result = "{\"ISSUCCESS\":\"FALSE\",\"MSG\":\" Get information failed, please try again! \"}";
    	return result;
    }
    
    @RequestMapping("/getEeventData")
	@ResponseBody
    public String getEeventData(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
//    	Date date1 = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		System.out.println("--------------当前时间---------" + sdf.format(date1));
//		long time1 = System.currentTimeMillis();
//		System.out.println("--------------当前毫秒数--------------" + System.currentTimeMillis());
		if(client==null)
		{
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			client =dcf.createClient("http://10.41.87.107:8280/eventdata/1.0.0?wsdl", new QName("tns", "EventService"));
		}
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
		Object[] objs = client.invoke("getEeventData");
//		long time3 = System.currentTimeMillis();
//		System.out.println("-----------时间差1-------------" + (time3 - time1));
		Object o = objs[0];
		StringArray sa = (StringArray)o;
//		Date date = new Date();
//		long time2 = System.currentTimeMillis();
//		System.out.println("--------------当前时间---------" + sdf.format(date));
//		System.out.println("--------------当前毫秒数--------------" + System.currentTimeMillis());
//		System.out.println("-----------时间差2-------------" + (time2 - time1));
		Map<String, String> maps = GsonUtil.jsonToMap4String(sa.getString().get(0).toString().replace("'", "\""));
		System.out.println(maps.toString());
		String[] locates = maps.get("point").split(";");
		List<Event> events = new ArrayList<Event>();
		for(int i = 0;i < locates.length;i++)
		{
			Event event = new Event();
			System.out.println(maps.get("detail"));
			event.setDesc(maps.get("detail"));
			event.setTime(maps.get("time"));
			String[] coordinate = locates[i].split("/");
			event.setLat(coordinate[0]);
			event.setLon(coordinate[1]);
			event.setSite(coordinate[2]);
			events.add(event);
		}
		String data = GsonUtil.objectToJson(events);
		System.out.println(data);
		return data;
    }
    @RequestMapping("/sayHello")
	@ResponseBody
    public String sayHi(HttpServletRequest request,HttpServletResponse response)
    {
    	return "测试";
    }
    
    @RequestMapping("/getTrafficJamData")
	@ResponseBody
    public String getTrafficJamDataforCamera(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
    	JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//		long time1 = System.currentTimeMillis();
//		System.out.println("--------------当前毫秒数--------------" + System.currentTimeMillis());
		Client client =dcf.createClient("http://10.41.87.107:8280/eventdata/1.0.0?wsdl", new QName("tns", "CameraServer"));
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("Content-Type", Arrays.asList("text/xml; charset=utf-8"));
		headers.put("Authorization", Arrays.asList(token));
		client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
//		long time3 = System.currentTimeMillis();
//		System.out.println("-----------时间差1-------------" + (time3 - time1));
		Object[] objs = client.invoke("getTrafficJamDataforCamera");
		Object o = objs[0];
		StringArray sa = (StringArray)o;
		System.out.println(sa.getString().toString());
//		String trafficdata = "[{'info': 'jam', 'created': '2015-06-05T03:10:22.684944', 'lon': '103.8', 'cameraId': '1', 'state': '0', 'location': 'dasdas', 'lat': '1.29', 'picTime': '2015-06-11 13:11:34', 'id': '643'}, {'info': 'jam', 'created': '2015-06-05T04:54:04.025940', 'lon': '103.82314', 'cameraId': '2', 'state': '0', 'location': 'deadfd', 'lat': '1.3', 'picTime': '2015-06-10 13:11:34', 'id': '658'}, {'info': 'jam', 'created': '2015-06-05T04:54:07.614366', 'lon': '103.82678', 'cameraId': '3', 'state': '0', 'location': 'ewewq', 'lat': '1.285', 'picTime': '2015-05-11 12:11:34', 'id': '659'}, {'info': 'jam', 'created': '2015-06-05T03:10:27.976846', 'lon': '103.84562', 'cameraId': '5', 'state': '0', 'location': 'dsada', 'lat': '1.29645', 'picTime': '2015-07-11 13:11:34', 'id': '647'}, {'info': 'jam', 'created': '2015-06-05T04:54:15.138686', 'lon': '103.83562', 'cameraId': 7, 'state': '0', 'location': 'wwww', 'lat': '1.2978', 'picTime': '2015-08-11 13:11:34', 'id': '663'}]";
		String trafficdata = "";
		trafficdata = sa.getString().toString().replace("\\\"", "");
//		trafficdata.replace("\"", "'");
    	
//		trafficdata = trafficdata.replace("'", "\"");
//		System.out.println(gson.toJson(trafficdata).toString());
		List<Traffic> traffics = new ArrayList<Traffic>();
		JsonParser parser = new JsonParser();
		JsonArray jsonArray = null;
		JsonElement je = parser.parse(trafficdata);
		jsonArray = je.getAsJsonArray();
		System.out.println(jsonArray.toString());
		for(int i = 0;i < jsonArray.size();i++)
		{
			JsonElement jsonElement = jsonArray.get(i);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			Traffic traffic = new Traffic();
			traffic.setInfo(jsonObject.get("info").getAsString());
			traffic.setLat(jsonObject.get("lat").getAsString());
			traffic.setLocation(jsonObject.get("location").getAsString());
			traffic.setLon(jsonObject.get("lon").getAsString());
			traffics.add(traffic);
		}
		String trafficsstr = GsonUtil.objectToJson(traffics);
		System.out.println(trafficsstr);
		return trafficsstr;
    }
}
	
