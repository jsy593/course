package com.bota.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.User;
import com.bota.dao.UserDao;
import com.bota.util.Dictionary;

@Repository("userDaoImpl")
public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(User record) {
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> studentsCourseSelection(long courseId){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String studentSql ="select studentId from studentCourse where courseId='"+courseId+"'"; 
		List<Map<String, Object>> students  = super.findManyBySql(studentSql);
		if(students == null && students.size() <= 0){
			return null;
		}
		String sql = "";
		for(Map<String, Object> student : students){
			sql = "select u.username,u.id,c.name from user u,category c where u.class=c.id and u.id="+student.get("studentId");
			Map<String, Object> map = super.findBySql(sql);
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public String selectUserByUserNumber(long userNumber) {
		String sql = "select * from user where userNumber="+userNumber;
		Map<String, Object> map = super.findBySql(sql);
		if(map != null && map.size() > 0){
			return Dictionary.S_SUCCESS;
		}
		return Dictionary.S_FAIL;
	}

	@Override
	public Map<String,Object> verifyByUser(long userNumber, String password, int identity){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String userSql = "select * from user where userNumber="+userNumber+" and password='"+password+"'";
		Map<String, Object> userMap = super.findBySql(userSql);
		if(userMap == null || userMap.size() <= 0){
			resultMap.put("status", Dictionary.S_PWD_ERR);
			return resultMap;
		}
		String sql = "select * from user where userNumber="+userNumber+" and password='"+password
				+"' and identity="+identity;
		Map<String, Object> map = super.findBySql(sql);
		if(map == null || map.size() <= 0){
			resultMap.put("status", Dictionary.S_ROLE_ERR);
			return resultMap;
		}
		resultMap.put("status", Dictionary.S_SUCCESS);
		resultMap.put("map", map);
		return resultMap;
	}
	
}
