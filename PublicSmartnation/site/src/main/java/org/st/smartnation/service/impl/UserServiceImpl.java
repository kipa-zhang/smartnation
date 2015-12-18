package org.st.smartnation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.dao.UserDao;
import org.st.smartnation.model.SNUser;
import org.st.smartnation.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public SNUser getUserInfo(int userId) {
		SNUser snUser = userDao.getUser(userId);
		return snUser;
	}

}
