package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

@Service("smuser")
@Entity
@Table(name = "smuser")
public class SMUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", unique = true, nullable = false)
	private int userId;
	
	@Size(min=6,max=30,message="{userName}")
	@Column(name = "userName", length = 100, nullable = false)
	private String userName;
	
	
	@Column(name = "userAge")
	private String userAge;
	
	@Size(min=6,max=12,message="{userAccount}")
	@Column(name = "userAccount", unique = true, nullable = false)
	private String userAccount;
	
	@Column(name = "userRoles")
	private String userRoles;

	@Column(name = "userInterest")
	private String userInterest;
	
	@Column(name = "userAddress")
	private String userAddress;
	
	public String getUserInterest() {
		return userInterest;
	}

	public void setUserInterest(String userInterest) {
		this.userInterest = userInterest;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
