package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.Course;
import com.bota.bean.TeacherCourse;

public interface TeacherCourseDao {
    int deleteByPrimaryKey(Long id);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);
    
    /**
	 * 查询所有的课程
	 * @return
	 */
	List<Map<String, Object>> selectAllTeacherCourse();
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	List<Map<String, Object>> selectAllTeacherCourse(int pageNum,int pageSize);
	
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
	Map<String, Object> selectTeacherCourseNumber();

	/**
	 * 查询该课程是否存在
	 * @param id
	 * @return
	 */
	Map<String, Object> selectOneByCourseId(long id);
	
	/**
	 * 根据课程id,修改老师是否可修改课程的状态
	 * @param courseId
	 * @param isChange
	 * @return
	 */
	boolean updateOneByCourseId(long courseId, long isChange);
}