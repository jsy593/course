package com.bota.service;

import java.util.List;
import java.util.Map;

import com.bota.bean.TeacherCourse;

public interface TeacherCourseService {
	/**
	 * 查询所有的课程
	 * @return
	 */
	public List<Map<String, Object>> selectAllTeacherCourse();
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	public List<Map<String, Object>> selectAllTeacherCourse(int pageNum,int pageSize);
	
	/**
	 * 查询课程的数量
	 * @return 
	 */
	Map<String, Object> selectTeacherCourseNumber();
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	public TeacherCourse selectOne(long id);
	/**
	 * 修改课程的信息
	 * @param teacherCourse
	 * @return
	 */
	public boolean updateById(TeacherCourse teacherCourse);
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

	/**
	 * 添加课程
	 * @param teacherCourse
	 * @return
	 */
	public boolean addTeacherCourse(TeacherCourse teacherCourse);
	
	/**
	 * 查询该课程是否存在
	 * @param id
	 * @return
	 */
	public boolean  selectOneByCourseId(long id);

}
