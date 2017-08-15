package com.june.service;

import com.june.bean.User;

public interface UserService {
	public User findUserByName(String name);
	
	public void insert(User user);
	
	public User find(User user);
}
