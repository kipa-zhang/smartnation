package org.st.smartnation.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.st.smartnation.basedao.BaseDao;
import org.st.smartnation.dao.UserDao;
import org.st.smartnation.model.SNUser;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public SNUser getUser(int userId) {
		String sql = " from SNUser where userId =:userId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<SNUser> list = baseDao.find(sql, params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
