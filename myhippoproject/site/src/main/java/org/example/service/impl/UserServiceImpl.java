package org.example.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.dao.BaseDao;
import org.example.model.Login;
import org.example.model.SMUser;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDao baseDao;
	
	/**
	 * 登陆成功返回用户信息
	 * 否则空
	 */
	@Override
	@Transactional
	public SMUser verifyUserExist(String userAccount, String userPassword) {
		String sql = " from Login where userAccount=:userAccount and userPassword=:userPassword ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userAccount", userAccount);
		params.put("userPassword", userPassword);

		List<Login> list = baseDao.find(sql, params);
		if(list!= null && list.size()>0){
			String sql2 = " from SMUser where userAccount=:userAccount ";
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("userAccount", userAccount);
			List<SMUser> list2 = baseDao.find(sql2, params2);
			return list2.get(0);
		}
		return null;
	}
	@Override
	@Transactional
	public SMUser getUserById(int id) {
		return (SMUser) baseDao.get(SMUser.class, id);
	}

	/**
	 * 注册成功返回 ID（不为0）
	 * 否则 0
	 */
	@Override
	@Transactional
	public int registerUser(SMUser user,Login login) {
		String sql = " from SMUser where userAccount=:userAccount ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userAccount", user.getUserAccount());
		List<SMUser> list = baseDao.find(sql, params);
		if(list != null && list.size()>0){
			System.out.println("账户名已存在");
			return 0;
		}
		Serializable save = baseDao.save(user);
		
		login.setUserId((Integer) save);
		baseDao.save(login);
		return Integer.parseInt(baseDao.save(user).toString());
	}
	@Transactional
	@Override
	public boolean changePwd(int id, String opwd, String pwd) {
		String sql = " from Login where userId=:userId ";
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId",id);
		List<Login> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			if(!opwd.equals(list.get(0).getUserPassword())){
				System.out.println(opwd+" ; "+list.get(0).getUserPassword());
				return false;
			}
			Login login = list.get(0);
			login.setUserId(id);
			login.setUserPassword(pwd);
			baseDao.update(login);
			return true;
		}
		return false;
	}
	@Transactional
	@Override
	public boolean changeUserInfo(SMUser user) {
		String sql = " from SMUser where userAccount=:userAccount ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userAccount", user.getUserAccount());

		List<SMUser> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			System.out.println("=================aaaaaaaaaaaaaaaaaaa" + user.getUserId());
			SMUser smUser = list.get(0);
			smUser.setUserAccount(user.getUserAccount());
			smUser.setUserAddress(user.getUserAddress());
			smUser.setUserAge(user.getUserAge());
			smUser.setUserInterest(user.getUserInterest());
			smUser.setUserName(user.getUserName());
			smUser.setUserRoles(user.getUserRoles());
			baseDao.update(smUser);
			return true;
		}
		return false;
	}
	
}
