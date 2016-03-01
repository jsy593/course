package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.User;
import com.bota.dao.UserDao;
import com.bota.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDaoImpl;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public List<Map<String, Object>> studentsCourseSelection(long courseId) {
		return null;
	}

	@Override
	public String selectUserByUserNumber(long userNumber) {
		return userDaoImpl.selectUserByUserNumber(userNumber);
	}

	@Override
	public Map<String, Object> verifyByUser(User user) {
		long usernumber = user.getUsernumber();
		int identity = user.getIdentity();
		String password = user.getPassword();
		return userDaoImpl.verifyByUser(usernumber,password,identity);
	}

}
