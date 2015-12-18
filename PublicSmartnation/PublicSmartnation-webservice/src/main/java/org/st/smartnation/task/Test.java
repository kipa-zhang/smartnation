package org.st.smartnation.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public void getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}
