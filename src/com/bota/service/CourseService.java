package com.bota.service;

import java.util.List;
import java.util.Map;

import com.bota.bean.Course;

public interface CourseService {
	/**
	 * 根据课程号查询该课程是否存在
	 * @author bota
	 * @param courseNumber
	 * @return
	 */
	public String selectCourseByCourseNumber(long courseNumber);
	
	/**
	 * 修改课程信息
	 */
	public boolean updateCourseById(Course course);
	
	/**
	 * 添加课程
	 * @param course 课程实体
	 * @return
	 */
	public boolean addCourse(Course course);
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	public List<Map<String, Object>> selectAllCourse();
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	public Map<String, Object> selectAllCourse(int pageNum,int pageSize,Map<String, Object> paramMap);
	
	/**
	 * 查询课程的数量
	 * @return 
	 */
	Map<String, Object> selectCourseNumber();
	
	
	/**
	 * 根据老师id查询课程
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> selectCourseByTeacherId(long teacherId);
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	public Course selectOne(long id);
	/**
	 * 修改课程的信息
	 * @param course
	 * @return
	 */
	public boolean updateById(Course course);
	/**
	 * 根据id删除课程
	 * @param id
	 * @return
	 */
	public boolean deleteById(long id);

	/**
	 * 根据id批量删除课程
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String id);
}
