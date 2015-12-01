package org.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.example.dao.BaseDao;
import org.example.model.Hobby;
import org.example.model.SMUser;
import org.example.model.Topic;
import org.example.service.HobbiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@WebService(targetNamespace = "http://impl.service.example.org/", endpointInterface = "org.example.service.HobbiesService", portName = "HobbiesServiceImplPort", serviceName = "HobbiesServiceImplService")
@Repository
public class HobbiesServiceImpl implements HobbiesService {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	@Transactional
	public boolean addHobbies(String hobbyName, int topicId) {
		Hobby hobby = new Hobby();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hobbyName", hobbyName);
		List<Hobby> hList = (List<Hobby>) baseDao.find("from Hobby where hobbyName =:hobbyName", params);
		if(hList.isEmpty()){
			hobby.setHobbyName(hobbyName);
			hobby.setTopicId(topicId);
		}else{
			params.put("topicId", topicId);
			List<Hobby> tList = (List<Hobby>) baseDao.find("from Hobby where hobbyName =:hobbyName and topicId =:topicId", params);
			if(tList.isEmpty()){
				hobby.setHobbyName(hobbyName);
				hobby.setTopicId(topicId);
			}else{
				return false;
			}
		}
		baseDao.save(hobby);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteTopic(String hobbyName, int topicId) {
		Hobby hobby = new Hobby();
		hobby.setHobbyName(hobbyName);
		hobby.setTopicId(topicId);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hobbyName", hobbyName);
		params.put("topicId", topicId);
		List<Hobby> hobbies = (List<Hobby>) baseDao.find("from Hobby where hobbyName =:hobbyName and topicId =:topicId", params);
		if(hobbies.isEmpty()){
			return false;
		}
		baseDao.delete(hobby);
		return true;
	}

	@Override
	@Transactional
	public List<Map> recommendTopics(int userId) {
		SMUser sUser = new SMUser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<SMUser> sUsers = (List<SMUser>) baseDao.find("from SMUser where userId =:userId", params);
		if(sUsers.isEmpty()){
			return null;
		}
		String hobby = sUsers.get(0).getUserInterest();
		if(hobby == null || hobby.length() == 0){
			return null;
		}
		
//		String[] hobbies = hobby.split(",");
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("names", hobby);
		List<Map> topics = (List<Map>) baseDao.find("select new Map(t.topicId as topicId,t.topicName as topicName,t.topicType as topicType,t.customNum as customNum,t.url as url,t.icon as icon) from Topic as t ,Hobby as h where h.topicId = t.topicId and h.hobbyName in( :names ) order by t.customNum", params2);
		return topics;
	}

}
