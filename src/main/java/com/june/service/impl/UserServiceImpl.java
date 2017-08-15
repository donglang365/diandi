package com.june.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.june.bean.User;
import com.june.dao.UserDao;
import com.june.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public User find(User user) {
		return userDao.selectOne(user);
	}


}
