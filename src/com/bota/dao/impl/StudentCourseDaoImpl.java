package com.bota.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.StudentCourse;
import com.bota.dao.StudentCourseDao;

@Repository("studentCourseDaoImpl")
public class StudentCourseDaoImpl extends CommonDaoImpl<StudentCourse> implements StudentCourseDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(StudentCourse record) {
		return 0;
	}

	@Override
	public int insertSelective(StudentCourse record) {
		return 0;
	}

	@Override
	public StudentCourse selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(StudentCourse record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(StudentCourse record) {
		return 0;
	}
	
	
	@Override
	public List<Map<String, Object>> selectAllStudentCourse(){
		String sql = "select *,date_format(createTime,'%Y-%m-%d') time from studentCourse";
		return super.findManyBySql(sql);
	}

	@Override
	public Map<String, Object> selectAllStudentCourse(int pageNum, int pageSize,String whereSql){
		int start = (pageNum -1) * pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select  sc.grade,u.id uid,u.usernumber,u.username,us.username teachername,date_format(sc.createTime,'%Y-%m-%d') time,c.coursenumber,c.credit,c.name cname,cl.name classname "
				+ "from studentCourse sc left join course c on sc.courseid = c.id "
				+ "left join  user u on sc.studentId=u.id left join  classes cl on u.classid=cl.id left join user us on c.teacherid=us.id ").append(whereSql).
				append(" order by u.createTime limit " +start + ","+ pageSize);
		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql.toString());
		
		//记录条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from studentCourse sc left join course c on sc.courseid = c.id left join  user u on sc.studentId=u.id "
				+" left join  classes cl on u.classid=cl.id left join user us on c.teacherid=us.id ").append(whereSql);
		System.out.println(countSql);
		long count = super.getCount(countSql.toString());
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("listMap", listMap);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> selectStudentCourseNumber(){
		String sql = "select count(*) count from studentCourse";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(StudentCourse.class,"id", ids);
	}

	@Override
	public Map<String, Object> selectOneByCourseId(long studentId,long courseid) {
		String sql = "select * from studentCourse where courseid=" + courseid +" and studentId="+studentId;
		return super.findBySql(sql);
	}

	
	@Override
	public boolean addGrade(int grade,long studentId,long courseid) {
		String sql = "update studentCourse set grade="+grade+" where courseid=" + courseid +" and studentId="+studentId;
		return super.updateClass(sql);
	}
	
	
}
