package org.st.smartnation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 Map<String,Object> 的形式,不适用复杂数据类型。
     * @param headers
     * 			      请求头（Header）信息
     * @return result 
     * 			     所代表远程资源的响应结果
     * 			  
     */
	public static String sendGet(String url, Map<String,Object> params, Map<String,String> headers) {
        String result = "";
        BufferedReader in = null;
        if(url != null && !"".equals(url)){
        	try {
                String urlNameString = url + "?" + convertParams(params);
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                //设置 Header 额外信息
                if(headers != null && headers.size() > 0){
                	for (String key : headers.keySet()) {
                		connection.setRequestProperty(key, headers.get(key));
                	}
                }
                // 建立实际的连接            
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
//                for (String key : map.keySet()) {
//                    System.out.println(key + "--->" + map.get(key));
//                }
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
            // 使用finally块来关闭输入流finally {
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

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 Map<String,Object> 的形式,不适用复杂数据类型。
     * @param headers
     * 			      请求头（Header）信息
     * @return result 
     * 			      所代表远程资源的响应结果
     * 
     */
	public static String sendPost(String url, Map<String,Object> params, Map<String,String> headers) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        if(url != null && !"".equals(url)){
        	try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                //设置 Header 额外信息
                if(headers != null && headers.size() > 0){
                	for (String key : headers.keySet()) {
                		conn.setRequestProperty(key, headers.get(key));
                	}
                }
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(convertParams(params));
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！"+e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }  
     
     private static String convertParams(Map<String,Object> params){
    	 StringBuilder sb = new StringBuilder();
    	 if(params != null && params.size() > 0){
    		 Iterator<?> iter = params.entrySet().iterator();
        	 while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
        	     Object key = entry.getKey();
        	     Object val = entry.getValue();
        	     sb.append(key).append("=").append(val).append("&");
        	 }
        	 return sb.toString().replaceAll("&$", "");
    	 }
    	 return sb.toString();
     }
    
    public static void main(String[] args) {
        //发送 GET 请求
//        String s=HttpUtil.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);
        
        //发送 POST 请求
//        String sr=HttpUtil.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
    	
    	System.out.println(HttpUtil.convertParams(null));
    }
}
