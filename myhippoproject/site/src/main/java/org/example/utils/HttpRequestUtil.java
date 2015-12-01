package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpRequestUtil {

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数
     */
	public static String sendGet(String url, Map<String,Object> params) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + getParams(params);
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("AccountKey", "/XWVQLO7I3hWrxxe1bAgMg==");
            connection.setRequestProperty("UniqueUserId", "38846346-ae65-4930-8168-41440fe9e307");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            	//遍历所有的响应头字段
//            	for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
     }
     
     private static String getParams(Map<String,Object> params){
    	 StringBuilder sb = new StringBuilder();
    	 if(params != null){
    		 Iterator<?> iter = params.entrySet().iterator();
        	 while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
        	     Object key = entry.getKey();
        	     Object val = entry.getValue();
        	     sb.append(key).append("=").append(val).append("&");
        	 }
        	 if(sb.toString() != ""){
        		 return sb.toString().replaceAll("&$", "");
        	 }
    	 }
    	 
    	 return sb.toString();
     }
     

     public static void main(String[] args) {
    	//发送 GET 请求
    	 Map<String,Object> params = new HashMap<String, Object>();
    	 params.put("$skip", 100);
    	 params.put("CameraID", 1706);
        String s=HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/CameraImageSet", params);
        System.out.println(s);
        JSONObject jsonObject = JSONObject.fromObject(s);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
        int size = jsonArray.size();
        for(int i= 0 ;i<size;i++){
//        	JSONObject json = JSONObject.fromObject(JSONObject.fromObject(jsonArray.get(i)).get("__metadata"));
//        	System.out.println(json.get("uri"));
        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
        	System.out.println(json.get("CameraID"));
        }
    	 
//    	 Map<String,Object> params = new HashMap<String, Object>();
//    	 params.put("$skip", 5105);
//        String s=HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/BusStopCodeSet", params);
//        System.out.println(s);
//      JSONObject jsonObject = JSONObject.fromObject(s);
//      JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
//      int size = jsonArray.size();
//      for(int i= 0 ;i<size;i++){
//      	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
//      	System.out.println(json.get("Code"));
//      }
        
    	 
//    	 Map<String,Object> params = new HashMap<String, Object>();
//    	 params.put("$skip", 0);
//    	 params.put("BusStopID", 83139);
//        String s=HttpRequestUtil.sendGet("http://datamall2.mytransport.sg/ltaodataservice/BusArrival", params);
//        System.out.println(s.replace(" ", ""));
//	      JSONObject jsonObject = JSONObject.fromObject(s);
//	      JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("Services")); 
//      int size = jsonArray.size();
//      for(int i= 0 ;i<size;i++){
//      	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
//      	System.out.println(json.get("ServiceNo"));
//      }
    	 
//    	 Map<String,Object> params = new HashMap<String, Object>();
//    	 params.put("$skip", 6519);
//        String s=HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/SMRTRouteSet", params);
//        System.out.println(s.replace(" ", ""));
//	      JSONObject jsonObject = JSONObject.fromObject(s);
//	      JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
//      int size = jsonArray.size();
//      for(int i= 0 ;i<size;i++){
//      	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
//      	System.out.println(json.get("SR_SVC_NUM"));
//      }
    	 
//    	 Map<String,Object> params = new HashMap<String, Object>();
//    	 params.put("$skip", 0);
//        String s=HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/IncidentSet", params);
//        System.out.println(s);
//	      JSONObject jsonObject = JSONObject.fromObject(s);
//	      JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
//	      int size = jsonArray.size();
//	      JSONArray newJsonArray = new JSONArray();
//	      for(int i= 0 ;i<size;i++){
//	    	  JSONObject json = JSONObject.fromObject(jsonArray.get(i));
//	    	  json.remove("__metadata");
//	    	  newJsonArray.add(json);
//	      }
//	      System.out.println(newJsonArray.toString());
    	 
//    	 Map<String,Object> params = new HashMap<String, Object>();
//	    	params.put("cameraids", 1001);
//			String result = HttpRequestUtil.sendGet("http://www.mytransport.sg/content/mytransport/home/myconcierge/trafficcameras/jcr:content/par/cameras_slideshow.getcameradata.html", params);
//			System.out.println(result);
//			JSONObject jsonObject = JSONObject.fromObject(result);
//			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("images"));
//			JSONObject json = JSONObject.fromObject(jsonArray.get(0));
			
//    	 Map<String,Object> params = new HashMap<String, Object>();
//	    	params.put("cameraids", 1001);
//			String result = HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/CarParkSet", null);
//			System.out.println(result);
//			JSONObject jsonObject = JSONObject.fromObject(result);
//			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d"));
//			int size = jsonArray.size();
//		    JSONArray newJsonArray = new JSONArray();
//		    for(int i= 0 ;i<size;i++){
//		    	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
//			    json.remove("__metadata");
//			    newJsonArray.add(json);
//		    }
//		    System.out.println(newJsonArray.toString());
			
	}
}