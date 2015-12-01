package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Service("hobby")
@Entity
@Table(name = "hobby")
public class Hobby {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hobbyId", unique = true, nullable = false)
	private int hobbyId;
	
	@Column(name = "hobbyName")
	private String hobbyName;
	
	@Column(name = "topicId")
	private int topicId;
		
	@Column(name = "topicName")
	private String topicName;

	public int getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(int hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

}
