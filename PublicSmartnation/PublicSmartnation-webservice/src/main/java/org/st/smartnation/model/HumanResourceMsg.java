package org.st.smartnation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="humanresourcemsg")
@XmlRootElement(name = "HumanResourceMsg")  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "id", "createTime", "content", "userId", "userName", "imgs" }) 
public class HumanResourceMsg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "createTime", nullable = false)
	private long createTime;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "userId", nullable = false)
	private int userId;
	
	@Column(name = "userName", nullable = false)
	private String userName;
	
	@Column(name = "imgs")
	private String imgs;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
