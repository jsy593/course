package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.TeacherCourse;
import com.bota.dao.TeacherCourseDao;
import com.bota.service.TeacherCourseService;

@Service("TeacherCourseServiceImpl")
public class TeacherCourseServiceImpl implements TeacherCourseService{
	
	@Resource
	private TeacherCourseDao teacherCourseDao;
	
	@Resource
	private TeacherCourseDao teacherCourseDaoImpl;
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllTeacherCourse(){
		return teacherCourseDaoImpl.selectAllTeacherCourse();
	}
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllTeacherCourse(int pageNum,int pageSize){
		return teacherCourseDaoImpl.selectAllTeacherCourse(pageNum,pageSize);
	}
	
	/**
	 * 查询课程的数量
	 * @return 
	 */
	public Map<String, Object> selectTeacherCourseNumber(){
		return teacherCourseDaoImpl.selectTeacherCourseNumber();
	}
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	@Override
	public TeacherCourse selectOne(long id){
		return teacherCourseDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据课程id查询课程
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> selectOneByCourseId(long id){
		return teacherCourseDaoImpl.selectOneByCourseId(id);
	}
	/**
	 * 修改课程的信息
	 * @param teacherCourse
	 * @return
	 */
	@Override
	public boolean updateById(TeacherCourse teacherCourse){
		return teacherCourseDao.updateByPrimaryKeySelective(teacherCourse) > 0;
	}
	/**
	 * 根据id删除课程
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return teacherCourseDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除课程
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return teacherCourseDao.deleteByPrimaryKeys(ids);
	}

	/**
	 * 添加课程
	 */
	@Override
	public boolean addTeacherCourse(TeacherCourse teacherCourse) {
		return teacherCourseDao.insertSelective(teacherCourse) > 0;
	}
	
}
