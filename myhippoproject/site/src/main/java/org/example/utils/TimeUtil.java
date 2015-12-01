package org.example.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(Calendar.getInstance().getTime());
		return date;
	}
	
}
