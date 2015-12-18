package org.st.smartnation.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.model.Parking;
import org.st.smartnation.service.ParkingService;
import org.st.smartnation.util.HttpUtil;
import org.st.smartnation.util.PropertiesUtil;

public class ParkingTask {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	ParkingService parkingService;
	
	Map<String,String> datamallHeaders = PropertiesUtil.loadProperties("datamallHeaders.properties");
	Map<String,String> datamallUrls = PropertiesUtil.loadProperties("datamallUrls.properties");
	String datamallParkUrl = datamallUrls.get("parkUrl");
	
	/**
	 * 如果数据库为空，直接插入
	 * 如果数据库不为空，一条一条数据判断，若不存在，插入新数据，若存在，则更新
	 */
	public void updateParkingTableInfo(){
		
    	List<Parking> parkingInfoFromInternet = this.getResult();
    	List<Parking> parkingInfoFromTable = parkingService.getParkingInfo();
    	
    	if(parkingInfoFromTable != null && !parkingInfoFromTable.isEmpty()
    			&&parkingInfoFromInternet!=null
    			&&!parkingInfoFromInternet.isEmpty()){
    		
    		for (Parking parkingInternet : parkingInfoFromInternet) {
    			Parking parkingFromTable = parkingService.getParkingInfo(parkingInternet.getName());
    			if(parkingFromTable != null){
    				parkingInternet.setId(parkingFromTable.getId());
    				parkingService.updateParkingInfo(parkingInternet);
    			}else{
    				parkingService.insertParkingInfo(parkingInternet);
    			}
    		}
    	}else if(parkingInfoFromTable == null || parkingInfoFromTable.isEmpty()){
    		//将数据插入数据库
            parkingService.insertParkingInfo(parkingInfoFromInternet);
    	}else if(parkingInfoFromInternet==null||parkingInfoFromInternet.isEmpty()){
    		return;
    	}
    	
        logger.info(" update parking info size : " + parkingInfoFromInternet.size());
	}
	
	public List<Parking> getResult(){
		//从 datamall 获取数据
		//park 总个数目前不超过 50
		Map<String,Object> params = new HashMap<String, Object>();
    	params.put("$skip", 0);
    	String result = HttpUtil.sendGet(datamallParkUrl, params, datamallHeaders);
    	
    	//将数据打包处理
    	List<Parking> list = new ArrayList<Parking>();
    	JSONObject jsonObject = JSONObject.fromObject(result);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
        int size = jsonArray.size();
        for(int i= 0 ;i<size;i++){
        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
        	Parking parking = new Parking();
        	parking.setId(Integer.parseInt(json.getString("CarParkID")));
        	parking.setRemanentCarportNum(Integer.parseInt(json.getString("Lots")));
        	parking.setName(json.getString("Development"));
        	parking.setLon(json.getString("Longitude"));
        	parking.setLat(json.getString("Latitude"));
        	
        	System.out.println("CarParkID : "+json.getString("CarParkID")
        					+"  Area : "+json.getString("Area")
        					+"  Development : "+json.getString("Development")
        					+"  Lots : "+json.getString("Lots")
        					+"  Latitude : "+json.getString("Latitude")
        					+"  Longitude : "+json.getString("Longitude"));
        	
        	list.add(parking);
        }
		return list;
	}
}
