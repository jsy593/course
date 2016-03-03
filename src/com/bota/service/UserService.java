package com.bota.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bota.bean.User;

public interface UserService {
	public List<Map<String, Object>> studentsCourseSelection(long courseId);
	
	/**
	 * 根据学号查询该用户是否存在
	 * @author bota
	 * @param userNumber
	 * @return
	 */
	public String selectUserByUserNumber(long userNumber);
	
	
	/**
	 * 验证登录信息
	 * @author bota
	 * @param user
	 * @return
	 */
	public Map<String, Object> verifyByUser(User user);

	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	public boolean updatePwd(Map<String,Object> map);
		
	/**
	 * 修改用户信息
	 */
	public boolean updateUserById(User user);
}
