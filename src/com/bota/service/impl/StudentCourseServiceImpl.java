package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.StudentCourse;
import com.bota.dao.StudentCourseDao;
import com.bota.service.StudentCourseService;


@Service("studentCourseServiceImpl")
public  class StudentCourseServiceImpl implements StudentCourseService{

	@Resource
	private StudentCourseDao studentCourseDaoImpl;
	
	@Resource
	private StudentCourseDao studentCourseDao;
	
	@Override
	public boolean addStudentCourse(StudentCourse studentCourse){
		return studentCourseDao.insertSelective(studentCourse) > 0;
				
	}
	
	@Override
	public boolean selectOneByCourserId(long studentId,long courseId){
		Map<String, Object> resultMap =  studentCourseDao.selectOneByCourseId(studentId,courseId);
		if(resultMap != null && resultMap.size() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 查询所有的学生选课
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllStudentCourse(){
		return studentCourseDaoImpl.selectAllStudentCourse();
	}
	
	/**
	 * 分页查询所有的学生选课
	 * @return
	 */
	@Override
	public Map<String, Object> selectAllStudentCourse(int pageNum,int pageSize,Map<String, Object> paramMap){
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		if(paramMap != null){
			if(paramMap.get("search") != null && !paramMap.get("search").equals("")){
				whereSql.append(" and (u.username like'%"+paramMap.get("search").toString()+"%' or u.usernumber like'%"+paramMap.get("search").toString()+"%') ");
			}
			System.out.println(paramMap.get("courseid"));	
			System.out.println(paramMap.containsKey("courseid"));
			if(paramMap.get("courseid") != null && !paramMap.get("courseid").equals("")){
				whereSql.append(" and sc.courseid="+paramMap.get("courseid").toString());
			}
			System.out.println(whereSql);
		}
		return studentCourseDaoImpl.selectAllStudentCourse(pageNum,pageSize,whereSql.toString());
	}
	
	/**
	 * 查询学生选课的数量
	 * @return 
	 */
	public Map<String, Object> selectStudentCourseNumber(){
		return studentCourseDaoImpl.selectStudentCourseNumber();
	}
	
	/**
	 * 根据id查询学生选课
	 * @param id
	 * @return
	 */
	@Override
	public StudentCourse selectOne(long id){
		return studentCourseDao.selectByPrimaryKey(id);
	}
	/**
	 * 修改学生选课的信息
	 * @param StudentCourse
	 * @return
	 */
	@Override
	public boolean updateById(StudentCourse StudentCourse){
		return studentCourseDao.updateByPrimaryKeySelective(StudentCourse) > 0;
	}
	/**
	 * 根据id删除学生选课
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return studentCourseDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除学生选课
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return studentCourseDao.deleteByPrimaryKeys(ids);
	}

	
}
