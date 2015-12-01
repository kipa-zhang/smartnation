package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.example.beans.Coordinate;
import org.example.model.BusInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MapUtil {

	/**
	 * 发送完整请求，获得数据
	 * @param url
	 * @return
	 */
	private static String getResult(String url){
		String result = "";
		BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            System.out.println(realUrl.toString());
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //添加头信息
            //connection.setRequestProperty("accept", "application/json");
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
        System.out.println(result);
		return result;
	}
	
	/**
	 * 根据地址获得坐标
	 * @param address 
	 * @param countryCode e.g. gb for the United Kingdom, de for Germany
	 * @param format e.g. [html|xml|json|jsonv2]
	 * @return
	 */
	public static Coordinate getCoordinate(String address,String countryCode,String format) {
        String result = "";
        String url = String.format(  
    		    "http://nominatim.openstreetmap.org/search?q=%s&countrycodes=%s&format=%s",  
    		    address.replaceAll(" ", "+"), countryCode, format);
        result = getResult(url);
        JSONArray jsonArray = JSONArray.fromObject(result);
        if(jsonArray.size()>0){
        	
        	JSONObject jsonObject =JSONObject.fromObject(jsonArray.get(0));
        	Coordinate co = new Coordinate();
        	co.setLat(jsonObject.getString("lat"));
        	co.setLon(jsonObject.getString("lon"));
        	return co;
        }
        return null;
     }
	
	public static String getRoad(String lon,String lat,String format){
		String result = "";
		String url = String.format(  
    		    "http://nominatim.openstreetmap.org/reverse?lon=%s&lat=%s&format=%s",  
    		    lon,lat, format);
		result = getResult(url);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if(jsonObject.get("error") == null){
        	JSONObject jsonAdd = JSONObject.fromObject(jsonObject.get("address"));
            Object road = null;
            road = jsonAdd.get("road");
            if(road != null){
            	return road.toString();
            }
            road = jsonAdd.get("cycleway"); 
            if(road != null){
            	return road.toString();
            }
        }
        return "";
	}
	
	public static void main(String[] args) {
//		Coordinate co = new Coordinate();
//		co = getCoordinate("Shaw Twrs","sg","json");
//		System.out.println(co.getLon());
//		System.out.println(co.getLat());
//		System.out.println(getRoad("103.84309", "1.330031", "json"));
		System.out.println(getResult("http://www.mytransport.sg/content/mytransport/home/myconcierge/trafficcameras/jcr:content/par/cameras_slideshow.getcameradata.html?cameraids=1001"));
	}
}
