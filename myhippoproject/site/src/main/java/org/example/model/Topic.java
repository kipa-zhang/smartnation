package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;

import org.springframework.stereotype.Service;

@Service("topic")
@Entity
@Table(name = "topic")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topicId", unique = true, nullable = false)
	private int topicId;
	
	@Column(name = "topicName", unique = true, nullable = false)
	private String topicName;
	
	@Column(name = "topicType")
	private String topicType;
	
	@Column(name = "customNum")
	private int customNum;

	@Column(name = "url",nullable = false)
	private String url;
	@Column(name = "icon",nullable = false)
	private String icon;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCustomNum() {
		return customNum;
	}

	public void setCustomNum(int customNum) {
		this.customNum = customNum;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	
	
}
