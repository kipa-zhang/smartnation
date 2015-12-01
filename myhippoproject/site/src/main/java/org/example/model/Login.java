package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

@Service("login")
@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loginId", unique = true, nullable = false)
	private int loginId;
	
	@Column(name = "userId", unique = true, nullable = false)
	private int userId;
	
	@Column(name = "userAccount", unique = true, nullable = false)
	@Size(min=6,max=12,message="{userAccount}")
	private String userAccount;
	
	@Size(min=6,max=12,message="{userPassword}")
	@Column(name = "userPassword", nullable = false, length = 20)
	private String userPassword;
	
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
