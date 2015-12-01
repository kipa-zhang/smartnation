package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.example.model.UserTopics;
import org.example.spring.controller.ParkingController;

public class FunctionUtil {
	public static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }
	
	public static String loadProperties(String name)
	{
		Properties pros = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("token.properties");
		try {
			pros.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pros.get(name).toString();
	}
	
	/**
	 * 替换特殊字符
	 * @param str
	 * @return
	 */
	public static String StringReplaceFilter(String str) throws PatternSyntaxException{
      // 只允许字母和数字，不包括空格       
      // String regEx = "[^a-zA-Z0-9]";
      // 清除掉所有特殊字符
      String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
      Pattern p = Pattern.compile(regEx);
      Matcher m = p.matcher(str);
      return m.replaceAll("").trim();
   }
	
	/**
	 * 是否存在特殊字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean StringMatchFilter(String str) throws PatternSyntaxException{
      // 只允许字母和数字，不包括空格       
      // String regEx = "[^a-zA-Z0-9]";
      // 清除掉所有特殊字符
      String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
      Pattern p = Pattern.compile(regEx);
      Matcher m = p.matcher(str);
      return m.matches();
   }
	
	public static boolean StringSizeFilter(String str,int minSize,int maxSize){
		char[] c = str.toCharArray();
		if(c.length >= minSize && c.length <= maxSize)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
//		Properties pros = new Properties();
//		InputStream in = ClassLoader.getSystemResourceAsStream("token.properties");
//		try {
//			pros.load(in);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(pros.get("token"));
//		System.out.println(loadProperties("token"));
//		ParkingController pc = new ParkingController();
//		try {
//			pc.getParkingData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		System.out.println(StringMatchFilter("_ddf"));
		
		
	}
	
}
