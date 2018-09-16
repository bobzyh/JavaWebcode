package com.st.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.UserMapper;
import com.st.rbac.pojo.User;
import com.st.rbac.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User user) {
		return userMapper.login(user);
	}
}
