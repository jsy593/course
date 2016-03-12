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
	public boolean updateCreditById(long id,int credit){
		String sql = "update user set credit=credit+" +credit+ " where id="+ id;
		return super.updateClass(sql);
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
	public Map<String, Object> selectOneByUserNumber(long userNumber) {
		String sql = "select * from user where userNumber="+userNumber;
		Map<String, Object> map = super.findBySql(sql);
		return map;
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
	
	@Override
	public boolean updatePwd(long id,String password){
		String sql = "update user set password='"+ password +"' where id = "+ id;
		return super.updateClass(sql);
	}
	
	@Override
	public List<Map<String, Object>> selectAllUser(){
		String sql = "select *,date_format(createTime,'%Y-%m-%d') time from user";
		return super.findManyBySql(sql);
	}

	@Override
	public Map<String, Object> selectAllUser(int pageNum, int pageSize,String whereSql){
		int start = (pageNum -1) * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select  u.*,date_format(u.createTime,'%Y-%m-%d') time,c.name cname,m.name mname from user u "
				+ "left join  classes c on u.classId=c.id left join  major m on c.majorid=m.id ").append(whereSql).
		append(" order by u.createTime desc limit " +start + ","+ pageSize);
		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql.toString());
		System.out.println(sql);
		//记录条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from  user u left join  classes c on u.classId=c.id left join  major m on c.majorid=m.id ").append(whereSql);
		System.out.println(countSql);
		long count = super.getCount(countSql.toString());
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("listMap", listMap);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectAllStudent(int pageNum, int pageSize,String whereSql){
		int start = (pageNum -1) * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select  sc.id studentCourseId,sc.courseId,sc.grade,u.*,date_format(u.createTime,'%Y-%m-%d') time,co.name courseName,"
				+ "co.id courseId, co.name coursename,c.name cname,m.name mname from user u inner join studentCourse sc on u.id=sc.studentId "
				+ "inner join course co on co.id=sc.courseId "
				+ "left join  classes c on u.classId=c.id left join  major m on c.majorid=m.id ").append(whereSql).append(" order by u.createTime desc limit " +start + ","+ pageSize);
		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql.toString());
		
		//记录条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from user u inner join studentCourse sc on u.id=sc.studentId "
				+ "inner join course co on co.id=sc.courseId "
				+ "left join  classes c on u.classId=c.id left join  major m on c.majorid=m.id ").append(whereSql);
		System.out.println(countSql);
		long count = super.getCount(countSql.toString());
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("listMap", listMap);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectUserNumber(){
		String sql = "select count(*) count from user";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(User.class,"id", ids);
	}
	
	@Override
	public List<Map<String, Object>> selectAllUserByIdentity(int identity){
		String sql = "select * from user where identity="+identity;
		return super.findManyBySql(sql);
	}
	
	
	@Override
	public Map<String,Object> selectUserByTeacher(int pageNum, int pageSize,String whereSql){
		int start = (pageNum -1) * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select  sc.id studentCourseId,sc.courseId,u.*,date_format(u.createTime,'%Y-%m-%d') time,cl.name cname,m.name mname"
				+ " from  user u inner JOIN studentCourse sc on u.id=sc.studentid inner join course c on c.id=sc.courseid "
				+ "left join  classes cl on u.classId=cl.id left join  major m on cl.majorid=m.id ").append(whereSql).append(" limit " +start + ","+ pageSize);
		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql.toString());
		
		//记录条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from  user u inner JOIN studentCourse sc on u.id=sc.studentid inner join course c on c.id=sc.courseid "
				+ "left join  classes cl on u.classId=cl.id left join  major m on cl.majorid=m.id ").append(whereSql);
		System.out.println(countSql);
		long count = super.getCount(countSql.toString());
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("listMap", listMap);
		resultMap.put("count", count);
		return resultMap;
	}

}
