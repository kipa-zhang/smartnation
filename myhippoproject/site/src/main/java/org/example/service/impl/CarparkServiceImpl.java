package org.example.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.example.dao.BaseDao;
import org.example.service.CarparkService;
import org.example.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarparkServiceImpl implements CarparkService {

	@Autowired
	private BaseDao basedao;
	
	@Override
	public String getCarparkInfo() {
		String result = HttpRequestUtil.sendGet("http://datamall.mytransport.sg/ltaodataservice.svc/CarParkSet", null);
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("d"));
		int size = jsonArray.size();
	    JSONArray newJsonArray = new JSONArray();
	    for(int i= 0 ;i<size;i++){
	    	JSONObject json = JSONObject.fromObject(jsonArray.get(i));
		    json.remove("__metadata");
		    newJsonArray.add(json);
	    }
//	    System.out.println(newJsonArray.toString());
	    return newJsonArray.toString();
	}

	

}
