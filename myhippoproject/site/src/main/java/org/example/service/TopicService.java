package org.example.service;

import java.util.List;
import java.util.Map;

import org.example.model.Topic;
import org.example.model.UserTopics;

public interface TopicService {

	public void addTopicsToUser(int userId,String topicIds);
	
	public void deleteTopicsFromUser(int userId,String topicIds);
	
	public List<Map> searchTopicsOfUser(int userId);
	
	public List<Topic> searchAllTopics();
	
	public Topic searchTopic(int topicId);

	public List<Topic> findTopicsByStr(String queryStr);

	public List<UserTopics> searchCheckedTopics(String topicIds);

	public Topic findTopicsByName(String topicName);

	List<Topic> searchOtherTopicsOfUser(int userId);
	
}
