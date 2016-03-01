package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.User;

public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<Map<String, Object>> studentsCourseSelection(long courseId);
	
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
	 * @param usernumber 学号或工号
	 * @param password 密码
	 * @param identity 身份
	 * @return
	 */
	public Map<String, Object> verifyByUser(long usernumber, String password, int identity);
	
}