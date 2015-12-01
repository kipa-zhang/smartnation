package org.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.dao.BaseDao;
import org.example.model.Topic;
import org.example.model.UserTopics;
import org.example.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TopicServiceImpl implements TopicService {

	@Autowired
	private BaseDao baseDao;
	
	/**
	 * 给用户增加Topics
	 * @param userId
	 * @param topicIds
	 * @return
	 */
	@Transactional
	public void addTopicsToUser(int userId,String topicIds){
		List<UserTopics> list = this.createUserTopics(userId, topicIds);
		baseDao.save(list);
		for(UserTopics ut:list){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("topicId", ut.getTopicId());
//			baseDao.find("update Topic set customNum=customNum+1 where topicId = :topicId", params);
			List<Topic> listTopic = baseDao.find("from Topic where topicId = :topicId", params);
			Topic topic = listTopic.get(0);
			int Num = topic.getCustomNum();
			topic.setCustomNum(++Num);
			System.out.println("topic.getCustomNum(): "+topic.getCustomNum());
			baseDao.update(topic);
		}
	} 
	
	/**
	 * 删除用户的多个Topics
	 * @param userId
	 * @param topicIds
	 * @return
	 */
	@Transactional
	public void deleteTopicsFromUser(int userId,String topicIds){
		baseDao.delete(this.getUserTopics(userId, topicIds));
	}
	
	/**
	 * 查询用户定制的 Topics
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<Map> searchTopicsOfUser(int userId){
		
		String sql = "select new Map(t.topicId as topicId,t.topicName as topicName,t.topicType as topicType,t.customNum as customNum,t.url as url,t.icon as icon) from UserTopics as ut,Topic as t where ut.userId = :userId and t.topicId=ut.topicId ";
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("userId", userId);
		return baseDao.find(sql, params);
	}
	/**
	 * 查询非用户定制的 Topics
	 * @param userId
	 * @return
	 */
	@Transactional
	@Override
	public List<Topic> searchOtherTopicsOfUser(int userId){
		
		
		String sql ="from Topic t where t.topicId not in (select ut.topicId from UserTopics as ut where ut.userId= :userId)";
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("userId", userId);
		return baseDao.find(sql, params);
	}
	
	/**
	 * 查找所有的 Topic
	 * @return
	 */
	@Transactional
	public List<Topic> searchAllTopics(){
		String sql = " from Topic ";
		List<Topic> list = baseDao.find(sql);
		if(list.size()>0)
			return list;
		return null;
	}
	
	/**
	 * 验证 Topic 是否存在
	 * @param topicId
	 * @return
	 */
	@Transactional
	public Topic searchTopic(int topicId){
		String sql = " from Topic where topicId = :topicId ";
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("topicId", topicId);
		List<Topic> list = baseDao.find(sql, params);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	
	private List<UserTopics> createUserTopics(int userId,String topicIds){
		List<UserTopics> list = new ArrayList<UserTopics>();
		String[] topicids = topicIds.split(",");
		for(int i=0;i<topicids.length;i++){
			if(searchTopic(Integer.parseInt(topicids[i])) != null){
				UserTopics ut = new UserTopics();
				ut.setTopicId(Integer.parseInt(topicids[i]));
				ut.setUserId(userId);
				list.add(ut);
			}else{
				System.out.println("TopicId " + topicids[i] + " is not exist !");
			}
		}
		return list;
	}

	
	@Transactional
	private List<UserTopics> getUserTopics(int userId,String topicIds){
		List<UserTopics> list = new ArrayList<UserTopics>();
		String[] topicids = topicIds.split(",");
		String sql = "from UserTopics where userId = :userId and topicId in (";
		Map<String, Object> params = new HashMap<String, Object>(); 
		params.put("userId", userId);
		
		for(int i=0;i<topicids.length;i++){
			sql=sql+":id"+i+",";
			params.put("id"+i, Integer.parseInt(topicids[i]));
		}
		sql=sql.replaceAll(",$", "");
		sql=sql+")";
		List<UserTopics> userTopicsList = baseDao.find(sql, params);
		
		return userTopicsList;
	}


	@Override
	public List<Topic> findTopicsByStr(String queryStr) {
		String sql = " from Topic where topicname like :topicname";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("topicname", "%" +queryStr + "%");
		List<Topic> list = baseDao.find(sql, params);
		if(list.size()>0)
			return list;
		return null;
	}

	@Override
	public List<UserTopics> searchCheckedTopics(String topicIds) {
		if(topicIds == null)
			return null;
		String sql = " from UserTopics where topicid in (" + topicIds + ")";
		List<UserTopics> userTopics = baseDao.find(sql);
		return userTopics;
	}


	@Override
	public Topic findTopicsByName(String topicName) {
		String sql = " from Topic where topicname = :topicname";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("topicname", topicName);
		List<Topic> list = baseDao.find(sql, params);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
}
