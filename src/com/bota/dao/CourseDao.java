package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.Course;

public interface CourseDao {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
    /**
	 * 根据学号查询该课程是否存在
	 * @author bota
	 * @param CourseNumber
	 * @return
	 */
	public String selectCourseByCourseNumber(long courseNumber);
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	List<Map<String, Object>> selectAllCourse();
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	Map<String, Object> selectAllCourse(int pageNum,int pageSize,String whereSql);
	
	/**
	 * 批量删除课程
	 * @param ids
	 * @return
	 */
	boolean deleteByPrimaryKeys(String ids);

	/**
	 * 查询课程的数量
	 * @return 
	 */
	Map<String, Object> selectCourseNumber();
	/**
	 *	根据老师id查询课程
	 * @param teacherId
	 * @return
	 */
	List<Map<String, Object>> selectCourseByTeacherId(long teacherId);
	
	/**
	 * 学生选课
	 * @param pageNum 第几页
	 * @param pageSize 每页显示数量
	 * @param whereSql where语句
	 * @param studentId 学生id
	 * @return
	 */
	Map<String, Object> selectAllCourseByStudent(int pageNum, int pageSize, String whereSql, String studentId);
	
	/**
	 * 修改剩余可选人数
	 * @param flag 
	 * @param courseId 课程id
	 * @return
	 */
	public boolean updateSpacePerson(int flag, long courseId);
}