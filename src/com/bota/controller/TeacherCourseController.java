package com.bota.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bota.bean.Course;
import com.bota.bean.TeacherCourse;
import com.bota.service.CourseService;
import com.bota.service.TeacherCourseService;

@Controller
public class TeacherCourseController {
	
	@Autowired
	private TeacherCourseService teacherCourseService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("addTeacherCoursePage")
	public String addTeacherCoursePage(long id, HttpServletRequest request){
		Course course = courseService.selectOne(id);
		request.setAttribute("course", course);
		return "course/addTeacherCourse";
	}
	
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	@RequestMapping("teacherCourseList")
	@ResponseBody
	public List<Map<String, Object>> selectAllTeacherCourse(){
		return teacherCourseService.selectAllTeacherCourse();
	}
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	
	@RequestMapping("teacherCourseListByPage")
	public String selectAllTeacherCourse(int pageNum,int pageSize,HttpServletRequest request){
		request.setAttribute("teacherCourses", teacherCourseService.selectAllTeacherCourse(pageNum,pageSize));
		Map<String, Object> numberMap = teacherCourseService.selectTeacherCourseNumber();
		if(numberMap != null && numberMap.size() > 0){
			int count = Integer.parseInt(numberMap.get("count").toString());
			int totalPage  = 0;
			if(count % 5 != 0 ){
				totalPage =count/5 + 1; 
			}else{
				totalPage =count/5;
			}
			request.setAttribute("count",numberMap.get("count"));
			request.setAttribute("totalPage",totalPage);
		}
		request.setAttribute("pageNum", pageNum);
		
		return "course/teacherCourse";
	}
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	@RequestMapping("selectTeacherCourse")
	@ResponseBody
	public TeacherCourse selectOne(long id){
		return teacherCourseService.selectOne(id);
	}
	
	
	/**
	 * 添加申请记录
	 * @param id
	 * @return
	 */
	@RequestMapping("addTeacherCourse")
	@ResponseBody
	public boolean addTeacherCourse(TeacherCourse teacherCourse){
		long courseId = teacherCourse.getCourseid();
		Map<String, Object> resultMap = teacherCourseService.selectOneByCourseId(courseId);//如果以前有记录
		if(resultMap != null && resultMap.size() > 0){
			TeacherCourse tCourse = new TeacherCourse();
			tCourse.setId(Long.parseLong(resultMap.get("id").toString()));
			tCourse.setContent(teacherCourse.getContent());
			tCourse.setIsagree(0);//待查看
			tCourse.setIschange(0);//老师不能修改
			return teacherCourseService.updateById(tCourse);
		}else{
			Date date = new Date();
			teacherCourse.setCreatetime(date);
			return teacherCourseService.addTeacherCourse(teacherCourse);
		}
	}
	
	
	/**
	 * 审批申请
	 * @param TeacherCourse
	 * @return
	 */
	@RequestMapping("updateTeacherCourse")
	@ResponseBody
	public boolean updateById(TeacherCourse teacherCourse){
			if(teacherCourse.getIsagree() != null && teacherCourse.getIsagree() == 1){//如果同意申请
				teacherCourse.setIschange(1);//可修改该课程
			}
			return teacherCourseService.updateById(teacherCourse);
	}
	
	
	
	/**
	 * 根据id删除课程
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteTeacherCourse")
	@ResponseBody
	public boolean deleteById(long id){
		return teacherCourseService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除课程
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyTeacherCourses")
	@ResponseBody
	public boolean deleteById(String ids){
		return teacherCourseService.deleteByIds(ids);
	}
	
}
