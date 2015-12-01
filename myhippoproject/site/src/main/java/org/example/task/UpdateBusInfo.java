package org.example.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.example.beans.Coordinate;
import org.example.model.BusInfo;
import org.example.model.BusRoute;
import org.example.model.BusStop;
import org.example.service.BusService;
import org.example.utils.HttpRequestUtil;
import org.example.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateBusInfo {

	@Autowired
	private BusService busService;
	
	public void updateBusStopInfo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(Calendar.getInstance().getTime());
		System.out.println(date+" : Start update BusStop Info !");
		//这里的 次数 1 是根据 数据源的信息判断所得
		List<BusStop> busStopList = getBusStopInfo("http://datamall.mytransport.sg/ltaodataservice.svc/BusStopCodeSet", 120);
		System.out.println("busStopList : "+busStopList.size());
		if(busStopList != null){
			busService.updateBusStopInfo(busStopList);
		}
	} 
	
	public void updateBusRouteInfo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(Calendar.getInstance().getTime());
		System.out.println(date+" : Start update BusRoute Info !");
		//这里的 次数 1 是根据 数据源的信息判断所得
		List<BusRoute> SBSTRouteList = getBusRouteInfo("http://datamall.mytransport.sg/ltaodataservice.svc/SBSTRouteSet", 326, "SBST");
		List<BusRoute> SMRTRouteList = getBusRouteInfo("http://datamall.mytransport.sg/ltaodataservice.svc/SMRTRouteSet", 132, "SMRT");
		if(SBSTRouteList != null && SMRTRouteList != null){
			busService.updateBusRoute(SBSTRouteList);
			busService.updateBusRoute(SMRTRouteList);
		}
	} 
	
	public void updateBusServiceInfo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(Calendar.getInstance().getTime());
		System.out.println(date+" : Start update Bus Service Info !");
		//这里的 次数 1 是根据 数据源的信息判断所得
		List<BusInfo> SBSTInfoList = getBusServiceInfo("http://datamall.mytransport.sg/ltaodataservice.svc/SBSTInfoSet", 8, "SBST");
		List<BusInfo> SMRTInfoList = getBusServiceInfo("http://datamall.mytransport.sg/ltaodataservice.svc/SMRTInfoSet", 4, "SMRT");
		if(SBSTInfoList != null && SMRTInfoList != null){
			busService.updateBusInfo(SBSTInfoList);
			busService.updateBusInfo(SMRTInfoList);
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param times times>0
	 * @return
	 */
	private List<BusStop> getBusStopInfo(String url,int times){
		List<BusStop> list = new ArrayList<BusStop>();;
		for(int j = 0;j<times;j++){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Map<String,Object> params = new HashMap<String, Object>();
	    	params.put("$skip", j*50);
			String info = HttpRequestUtil.sendGet(url, params);
			if(info != null && !"".equals(info)){
				JSONObject jsonObject = JSONObject.fromObject(info);
		        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
		        int size = jsonArray.size();
		        for(int i= 0 ;i<size;i++){
		        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		        	BusStop bs = new BusStop();
		        	bs.setBusStopCode(json.getString("Code"));
		        	bs.setDescription(json.getString("Description"));
		        	bs.setRoad(json.getString("Road"));
		        	if(json.getString("Description")!=null &&json.getString("Description")!=""){
		        		Coordinate co = MapUtil.getCoordinate(json.getString("Description"), "sg", "json");
		        		if(co != null){
		        			bs.setLon(co.getLon());
		        			bs.setLat(co.getLat());
		        			System.out.println(" 找到位置的坐标信息 ");
		        		}else
		        			System.out.println(" 未找到位置的坐标信息 ");
		        	}
		        	list.add(bs);
		        }
			}
		}
		return list;
	}
	
	private List<BusRoute> getBusRouteInfo(String url,int times,String operator){
		List<BusRoute> list = new ArrayList<BusRoute>();
		for(int j = 0;j<times;j++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Map<String,Object> params = new HashMap<String, Object>();
	    	params.put("$skip", j*50);
			String info = HttpRequestUtil.sendGet(url, params);
			if(info != null && !"".equals(info)){
				JSONObject jsonObject = JSONObject.fromObject(info);
		        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
		        int size = jsonArray.size();
		        for(int i= 0 ;i<size;i++){
		        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		        	BusRoute br = new BusRoute();
		        	br.setTransportOperatorCode(operator);
		        	br.setSR_SVC_NUM(json.getString("SR_SVC_NUM"));
		        	br.setSR_SVC_DIR(json.getString("SR_SVC_DIR"));
		        	br.setSR_ROUT_SEQ(json.getString("SR_ROUT_SEQ"));
		        	br.setSR_BS_CODE(json.getString("SR_BS_CODE"));
		        	br.setSR_DISTANCE(json.getString("SR_DISTANCE"));
		        	br.setSR_FST_WD(json.getString("SR_FST_WD"));
		        	br.setSR_LST_WD(json.getString("SR_LST_WD"));
		        	br.setSR_FST_SAT(json.getString("SR_FST_SAT"));
		        	br.setSR_LST_SAT(json.getString("SR_LST_SAT"));
		        	br.setSR_FST_SUN(json.getString("SR_FST_SUN"));
		        	br.setSR_LST_SUN(json.getString("SR_LST_SUN"));
		        	list.add(br);
		        	System.out.println("获取 BusRoute 数据");
		        }
			}
		}
		return list;
	}
	
	private List<BusInfo> getBusServiceInfo(String url,int times,String operator){
		List<BusInfo> list = new ArrayList<BusInfo>();
		for(int j = 0;j<times;j++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Map<String,Object> params = new HashMap<String, Object>();
	    	params.put("$skip", j*50);
			String info = HttpRequestUtil.sendGet(url, params);
			if(info != null && !"".equals(info)){
				JSONObject jsonObject = JSONObject.fromObject(info);
		        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
		        int size = jsonArray.size();
		        for(int i= 0 ;i<size;i++){
		        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		        	BusInfo bi = new BusInfo();
		        	bi.setTransportOperatorCode(operator);
		        	bi.setSI_SVC_NUM(json.getString("SI_SVC_NUM"));
		        	bi.setSI_SVC_DIR(json.getString("SI_SVC_DIR"));
		        	bi.setSI_SVC_CAT(json.getString("SI_SVC_CAT"));
		        	bi.setSI_BS_CODE_ST(json.getString("SI_BS_CODE_ST"));
		        	bi.setSI_BS_CODE_END(json.getString("SI_BS_CODE_END"));
		        	bi.setSI_FREQ_AM_PK(json.getString("SI_FREQ_AM_PK"));
		        	bi.setSI_FREQ_AM_OF(json.getString("SI_FREQ_AM_OF"));
		        	bi.setSI_FREQ_PM_PK(json.getString("SI_FREQ_PM_PK"));
		        	bi.setSI_FREQ_PM_OF(json.getString("SI_FREQ_PM_OF"));
		        	bi.setSI_LOOP(json.getString("SI_LOOP"));
		        	list.add(bi);
		        	System.out.println("获取 BusInfo 数据");
		        }
			}
		}
		return list;
	}
	
}
