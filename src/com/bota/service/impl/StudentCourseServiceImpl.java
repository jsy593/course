package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bota.bean.Course;
import com.bota.bean.StudentCourse;
import com.bota.bean.User;
import com.bota.dao.CourseDao;
import com.bota.dao.StudentCourseDao;
import com.bota.dao.UserDao;
import com.bota.service.StudentCourseService;


@Service("studentCourseServiceImpl")
public  class StudentCourseServiceImpl implements StudentCourseService{

	@Resource
	private StudentCourseDao studentCourseDaoImpl;
	
	@Resource
	private StudentCourseDao studentCourseDao;
	@Resource
	private CourseDao courseDaoImpl;
	
	@Resource
	private CourseDao courseDao;
	
	@Resource
	private UserDao userDaoImpl;
	
	@Override
	public boolean addStudentCourse(StudentCourse studentCourse){
		if(studentCourseDao.insertSelective(studentCourse) > 0){
			return courseDaoImpl.updateSpacePerson(-1, studentCourse.getCourseid());//修改最大容量人数
		}
		return false;
				
	}
	
	@Override
	@Transactional
	public boolean selectOneByCourserId(long studentId,long courseId){
		Map<String, Object> resultMap =  studentCourseDaoImpl.selectOneByCourseId(studentId,courseId);
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
	public boolean deleteById(long id, long courseId){
		if(studentCourseDao.deleteByPrimaryKey(id) > 0){
			return courseDaoImpl.updateSpacePerson(1, courseId);//修改最大容量人数
		}
		return false;
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
	
	@Override
	@Transactional
	public boolean addGrade(List<Map<String, Object>> listMap){
		int grade;
		long studentId;
		long courseId;
		Course course;
		for(Map<String, Object> map: listMap){
			grade = Integer.parseInt(map.get("grade").toString());
			studentId = Long.parseLong(map.get("studentId").toString());
			courseId = Long.parseLong(map.get("courseId").toString());
			if(studentCourseDaoImpl.addGrade(grade, studentId, courseId)){//如果添加成绩成功
				if(Integer.parseInt(map.get("grade").toString()) >= 60){//如果成绩及格，用户学分为当前学分加上该门课程的学分
					course = courseDao.selectByPrimaryKey(courseId);
					if(course != null){
						userDaoImpl.updateCreditById(studentId, course.getCredit());
					}else{
						return false;
					}
				}
				
			}else{
				return false;
			}
		}
		
		return true;
	}
	
}
