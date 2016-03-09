package com.bota.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.Course;
import com.bota.dao.CourseDao;
import com.bota.util.Dictionary;

@Repository("courseDaoImpl")
public class CourseDaoImpl extends CommonDaoImpl<Course> implements CourseDao{
	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Course record) {
		return 0;
	}

	@Override
	public int insertSelective(Course record) {
		return 0;
	}

	@Override
	public Course selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Course record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Course record) {
		return 0;
	}
	
	@Override
	public String selectCourseByCourseNumber(long courseNumber) {
		String sql = "select * from course where courseNumber="+courseNumber;
		Map<String, Object> map = super.findBySql(sql);
		if(map != null && map.size() > 0){
			return Dictionary.S_SUCCESS;
		}
		return Dictionary.S_FAIL;
	}

	
	@Override
	public List<Map<String, Object>> selectAllCourse(){
		String sql = "select *,date_format(createTime,'%Y-%m-%d') time from course";
		return super.findManyBySql(sql);
	}

	@Override
	public Map<String, Object> selectAllCourse(int pageNum, int pageSize,String whereSql){
		int start = (pageNum -1) * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select  sc.id studentCourseId,c.*,date_format(c.createTime,'%Y-%m-%d') time,u.username  teachername,m.name mname,tc.isAgree from Course c "
				+ "left join  user u on c.teacherId=u.id left join  major m on c.specialtyId=m.id "
				+ "left join teacherCourse tc on c.id= tc.courseid left join studentCourse sc on c.id=sc.courseid left join user us on us.id = sc.studentid ").append(whereSql).append(" limit " +start + ","+ pageSize);
		List<Map<String, Object>> listMap = super.findManyBySql(sql.toString());
		
		//记录条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from Course c left join  user u on c.teacherId=u.id"
				+ " left join  major m on c.specialtyId=m.id left join teacherCourse tc on c.id= tc.courseid "
				+ "left join studentCourse sc on c.id=sc.courseid left join user us on us.id = sc.studentid").append(whereSql);
		long count = super.getCount(countSql.toString());
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("listMap", listMap);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectCourseNumber(){
		String sql = "select count(*) count from course";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(Course.class,"id", ids);
	}

	@Override
	public List<Map<String, Object>> selectCourseByTeacherId(long teacherId) {
		String sql = "select id,name from course where teacherId="+teacherId;
		return super.findManyBySql(sql);
	}
}
