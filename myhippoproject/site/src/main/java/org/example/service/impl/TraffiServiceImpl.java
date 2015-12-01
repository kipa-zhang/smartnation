package org.example.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.dao.BaseDao;
import org.example.model.BusRoute;
import org.example.model.Camera;
import org.example.service.TrafficService;
import org.example.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TraffiServiceImpl implements TrafficService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	@Transactional
	public List<Camera> getTraficCameraInfo() {
		List<Camera> listCamera = null;
		listCamera = baseDao.find("from Camera");
		return listCamera;
	}

	@Override
	@Transactional
	public List<Camera> getTraficCameraInfo(String roads) {
		String sql = " from Camera where road in (:road) ";
		Map<String, Object> params = new HashMap<String, Object>();
		String[] road = roads.split(",");
		List<String> roadList =new ArrayList<String>();
		for(String str:road){
			roadList.add(str);
		}
		params.put("road", roadList);
		List<Camera> list = baseDao.find(sql, params);
		return list;
	}
	
	@Override
	@Transactional
	public void updateTraficCameraInfo(List<Camera> listCameras) {
		if(listCameras!= null){
			//查询已有信息
			List<Camera> existCameras = baseDao.find("from Camera");
			
			//比较，如果数据已近存在，则 update ，否则插入新增的 camera
			for(Camera camera:listCameras){
				boolean ifExist = false;
				for(Camera existCamera:existCameras){
					if(camera.getCameraID() == existCamera.getCameraID()){
						existCamera.setPicPath(camera.getPicPath());
						existCamera.setLon(camera.getLon());
						existCamera.setLat(camera.getLat());
						if(existCamera.getRoad() == "" || existCamera.getRoad() == null){
							String road = MapUtil.getRoad(camera.getLon(), camera.getLat(), "json");
							existCamera.setRoad(road);
						}
						baseDao.update(existCamera);
						ifExist = true;
					}
				}
				if(!ifExist){
					String road = MapUtil.getRoad(camera.getLon(), camera.getLat(), "json");
					camera.setRoad(road);
					baseDao.save(camera);
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(Calendar.getInstance().getTime());
			System.out.println(date+" : End update Camera Info !");
		}
		else
			System.out.println("update Trafic Camera Info data failed!");
		
	}

	

}
