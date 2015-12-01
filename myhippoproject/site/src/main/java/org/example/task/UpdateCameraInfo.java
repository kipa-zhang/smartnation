package org.example.task;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.example.model.Camera;
import org.example.service.TrafficService;
import org.example.utils.DownloadUtil;
import org.example.utils.FileUtil;
import org.example.utils.HttpRequestUtil;
import org.example.utils.MapUtil;
import org.example.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateCameraInfo {

	@Autowired
	private TrafficService trafficService;
	
	//这里的URL每次只能 获得50条数据，实际有52条
	public void updateCameraInfo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(Calendar.getInstance().getTime());
		System.out.println(date+" : Start update Camera Info !");
		//这里的 次数 1 是根据 数据源的信息判断所得
		List<Camera> list = getCameraInfo("http://datamall.mytransport.sg/ltaodataservice.svc/CameraImageSet", 2);
		if(list != null){
			trafficService.updateTraficCameraInfo(list);
		}
		updateCameraImgs();
	}
	
	/**
	 * 0-50个数据  访问1次
	 * 50-100个数据  访问2次
	 * datamall 单次访问最大数据量是 50
	 * 以此类推
	 * @param url
	 * @param times 查询次数
	 * @return
	 */
	private List<Camera> getCameraInfo(String url,int times){
		List<Camera> list = new ArrayList<Camera>();
		for(int j = 0;j<times;j++){
			Map<String,Object> params = new HashMap<String, Object>();
	    	params.put("$skip", j*50);
			String info = HttpRequestUtil.sendGet(url, params);
			if(info != null && !"".equals(info)){
				JSONObject jsonObject = JSONObject.fromObject(info);
		        JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d")); 
		        int size = jsonArray.size();
		        for(int i= 0 ;i<size;i++){
		        	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		        	Camera camera = new Camera();
		        	camera.setCameraID(Integer.parseInt(json.getString("CameraID")));
		        	camera.setLon(json.getString("Longitude"));
		        	camera.setLat(json.getString("Latitude"));
		        	camera.setPicPath(json.getString("ImageURL"));
		        	camera.setDateTime(TimeUtil.getCurrentTime());
		        	list.add(camera);
		        }
			}
		}
		return list;
	}
	
	
	public void updateCameraImgs(){
		List<Camera> list = trafficService.getTraficCameraInfo();
		if(list != null)
			for(Camera camera : list){
				String imgPath = camera.getPicPath();
				try{
					String baseUrl = System.getProperty("webapprootkey");
		            DownloadUtil.downLoadFromUrl(imgPath,camera.getCameraID()+".jpg",baseUrl+"traimgs_cache\\");
		        }catch (Exception e) {  
		            // TODO: handle exception  
		        }
				
			}
		else 
			System.out.println("更新图片失败");
	}
	
	public void moveImgs(){
		System.out.println("开始移动图片");
		String baseUrl = System.getProperty("webapprootkey");
		// 获取源文件夹当前下的文件或目录   
        File[] file = (new File(baseUrl+"traimgs_cache")).listFiles();
        boolean ifReady = true;
        if(file != null){
        	for (int i = 0; i < file.length; i++) {  
        		if (file[i].isFile()) {  
        			if(file[i].length() == 0){
        				ifReady = false;
        			}else{
//        				System.out.println("文件大小 ： "+ file[i].length());
        			} 
        		}  
        	}
        	if(ifReady){
        		try {
        			String sourceUrl = baseUrl+"traimgs_cache";
        			String desUrl = baseUrl+"traimgs";
        			System.out.println("源路径： "+baseUrl+"traimgs_cache");
        			System.out.println("目标路径： "+baseUrl+"traimgs");
        			FileUtil.copy(sourceUrl, desUrl);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	}
        }
        System.out.println("结束移动图片");
	}
}
