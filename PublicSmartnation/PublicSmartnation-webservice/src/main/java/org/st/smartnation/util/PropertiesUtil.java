package org.st.smartnation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesUtil {

	/**
	 * 从项目根路径下寻找 properties 文件，返回内容的键值对（HashMap<String,String>）
	 * @param fileName
	 * @return
	 */
	public static HashMap<String,String> loadProperties(String fileName)
    {
		HashMap<String,String> map = new HashMap<String, String>();
        Properties pros = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try {
            pros.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<String> enumeration = (Enumeration<String>) pros.propertyNames();
        while(enumeration.hasMoreElements()){
        	String key = enumeration.nextElement();
        	String value = pros.get(key).toString();
        	map.put(key, value);
        }
        return map;
    }
	
	public static void main(String[] args) {
		HashMap<String,String> map = PropertiesUtil.loadProperties("datamall.properties");
		System.out.println(map.size());
	}
}
