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

import org.hibernate.annotations.Generated;

@Entity
@Table(name = "businfo")
@XmlRootElement(name = "BusInfo")  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "id", "code", "startTime", "endTime" }) 
public class BusInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	//线路编号
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	//时分秒  小时*60+分钟
	@Column(name = "startTime")
	private int startTime;
	
	//时分秒  小时*60+分钟
	@Column(name = "endTime")
	private int endTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
