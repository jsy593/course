package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.User;
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
		return userDaoImpl.studentsCourseSelection(courseId);
	}

	@Override
	public String selectUserByUserNumber(long userNumber) {
		return userDaoImpl.selectUserByUserNumber(userNumber);
	}
	
	@Override
	public Map<String, Object> selectOneByUserNumber(long userNumber) {
		return userDaoImpl.selectOneByUserNumber(userNumber);
	}
	
	
	@Override
	public Map<String, Object> verifyByUser(User user) {
		long usernumber = user.getUsernumber();
		int identity = user.getIdentity();
		String password = user.getPassword();
		return userDaoImpl.verifyByUser(usernumber,password,identity);
	}

	@Override
	public boolean updatePwd(Map<String, Object> map){
		long id = Long.parseLong(map.get("id").toString());
		String password = map.get("newPwd").toString();
		return userDaoImpl.updatePwd(id, password);
	}

	@Override
	public boolean updateUserById(User user) {
		return userDao.updateByPrimaryKeySelective(user) > 0;
	}
	
	@Override
	public boolean addUser(User user){
		return userDao.insertSelective(user) > 0;
				
	}
	
	
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllUser(){
		return userDaoImpl.selectAllUser();
	}
	
	/**
	 * 分页查询所有的用户
	 * @return
	 */
	@Override
	public Map<String, Object> selectAllUser(int pageNum,int pageSize,Map<String, Object> paramMap){
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		if(paramMap != null){
			if(paramMap.get("teacherId") != null && !paramMap.get("teacherId").equals("")){
				whereSql.append(" and co.teacherId="+paramMap.get("teacherId").toString());
				if(paramMap.get("courseId") != null && !paramMap.get("courseId").equals("")){
					whereSql.append(" and sc.courseId="+paramMap.get("courseId").toString());
				}
				return userDaoImpl.selectAllStudent(pageNum,pageSize,whereSql.toString());
			}
			if(paramMap.get("search") != null && !paramMap.get("search").equals("")){
				whereSql.append(" and (u.username like'%"+paramMap.get("search").toString()+"%' or u.usernumber like'%"+paramMap.get("search").toString()+"%') ");
			}
			if(paramMap.get("identity") != null && !paramMap.get("identity").equals("")){
				whereSql.append(" and u.identity = " + paramMap.get("identity").toString());
			}
			System.out.println(paramMap.get("classid"));			System.out.println(paramMap.containsKey("classid"));
			if(paramMap.get("classid") != null && !paramMap.get("classid").equals("")){
				whereSql.append(" and u.classid="+paramMap.get("classid").toString());
			}
			
			System.out.println(whereSql);
		}
		return userDaoImpl.selectAllUser(pageNum,pageSize,whereSql.toString());
	}
	
	/**
	 * 查询用户的数量
	 * @return 
	 */
	public Map<String, Object> selectUserNumber(){
		return userDaoImpl.selectUserNumber();
	}
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@Override
	public User selectOne(long id){
		return userDao.selectByPrimaryKey(id);
	}
	/**
	 * 修改用户的信息
	 * @param User
	 * @return
	 */
	@Override
	public boolean updateById(User User){
		return userDao.updateByPrimaryKeySelective(User) > 0;
	}
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return userDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return userDao.deleteByPrimaryKeys(ids);
	}
	
	
	@Override
	public List<Map<String,Object>> selectAllUserByIdentity(int identity){
		return userDaoImpl.selectAllUserByIdentity(identity);
	}
}
