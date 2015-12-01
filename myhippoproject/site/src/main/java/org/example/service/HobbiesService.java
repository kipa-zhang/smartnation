package org.example.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.example.model.Hobby;
import org.example.model.Topic;

//@WebService(name = "HobbiesService", targetNamespace = "http://service.example.org/")
public interface HobbiesService {

	public boolean addHobbies(String hobbyName, int topicId);
	
	public boolean deleteTopic(String hobbyName, int topicId);
	
	public List<Map> recommendTopics(int userId);
	
}
