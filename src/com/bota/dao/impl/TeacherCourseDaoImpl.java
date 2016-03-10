package com.bota.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.Course;
import com.bota.bean.TeacherCourse;
import com.bota.dao.TeacherCourseDao;

@Repository("teacherCourseDaoImpl")
public class TeacherCourseDaoImpl extends CommonDaoImpl<TeacherCourse> implements TeacherCourseDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(TeacherCourse record) {
		return 0;
	}

	@Override
	public int insertSelective(TeacherCourse record) {
		return 0;
	}

	@Override
	public TeacherCourse selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TeacherCourse record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TeacherCourse record) {
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> selectAllTeacherCourse(){
		String sql = "select id,name,date_format(createTime,'%Y-%m-%d') time from teacherCourse tc,course c where tc.courseId=c.id";
		return super.findManyBySql(sql);
	}

	@Override
	public List<Map<String, Object>> selectAllTeacherCourse(int pageNum, int pageSize){
		int start = (pageNum -1) * pageSize;
		String sql = "select tc.*,date_format(tc.createTime,'%Y-%m-%d') time,c.name cname from teacherCourse tc,course c "
				+ " where tc.courseId=c.id and isDelete=0 limit " +start + ","+ pageSize;
//		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql);
		return listMap;
	}
	
	@Override
	public Map<String, Object> selectTeacherCourseNumber(){
		String sql = "select count(*) count from teacherCourse tc,course c "
				+ " where tc.courseId=c.id and isDelete=0";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(TeacherCourse.class,"id", ids);
	}

	@Override
	public Map<String, Object> selectOneByCourseId(long id) {
		String sql = "select * from teacherCourse where courseId="+id;
		Map<String, Object> map =  super.findBySql(sql);
		return map ;
	}

	@Override
	public boolean updateOneByCourseId(long courseId,long isChange) {
		String sql = "update teacherCourse set isChange="+isChange+" where courseId="+courseId;
		return super.updateClass(sql);
	}
}
