package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.StudentCourse;

public interface StudentCourseDao {
    int deleteByPrimaryKey(Long id);

    int insert(StudentCourse record);

    int insertSelective(StudentCourse record);

    StudentCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentCourse record);

    int updateByPrimaryKey(StudentCourse record);
    
    
    /**
	 * 查询所有的学生选课
	 * @return
	 */
	List<Map<String, Object>> selectAllStudentCourse();
	
	/**
	 * 分页查询所有的学生选课
	 * @return
	 */
	Map<String, Object> selectAllStudentCourse(int pageNum,int pageSize,String whereSql);
	
	/**
	 * 批量删除学生选课
	 * @param ids
	 * @return
	 */
	boolean deleteByPrimaryKeys(String ids);

	/**
	 * 查询学生选课的数量
	 * @return 
	 */
	Map<String, Object> selectStudentCourseNumber();

	/**
	 * 查询该学生选课是否存在
	 * @param id
	 * @return
	 */
	Map<String, Object> selectOneByCourseId(long id);

}