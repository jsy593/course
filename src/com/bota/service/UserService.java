package com.bota.service;

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
	
	/**
	 * 添加用户
	 * @param user 用户实体
	 * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	public List<Map<String, Object>> selectAllUser();
	
	/**
	 * 分页查询所有的用户
	 * @return
	 */
	public Map<String, Object> selectAllUser(int pageNum,int pageSize,Map<String, Object> paramMap);
	
	/**
	 * 查询用户的数量
	 * @return 
	 */
	Map<String, Object> selectUserNumber();
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User selectOne(long id);
	/**
	 * 修改用户的信息
	 * @param user
	 * @return
	 */
	public boolean updateById(User user);
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteById(long id);

	/**
	 * 根据id批量删除用户
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String id);

}
