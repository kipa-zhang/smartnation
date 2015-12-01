package org.example.service;

import org.example.model.Login;
import org.example.model.SMUser;

public interface UserService {

	public SMUser verifyUserExist(String userAccount,String userPassword);
	
	public int registerUser(SMUser user,Login login);

	SMUser getUserById(int id);
	
	public boolean changePwd(int id, String opwd, String pwd);
	
	public boolean changeUserInfo(SMUser user);

}
