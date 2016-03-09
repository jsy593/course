package com.bota.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bota.bean.StudentCourse;
import com.bota.service.ClassService;
import com.bota.service.CourseService;
import com.bota.service.StudentCourseService;
import com.bota.service.UserService;
import com.bota.util.DateStrConvert;
import com.bota.util.Dictionary;
import com.bota.util.FileUtil;
import com.bota.util.MapAction;

/**
 * 
 * @author bota
 */
@Controller
public class StudentCourseController {
	
	@Autowired
	private StudentCourseService studentCourseService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 添加学生选课
	 */
	@RequestMapping("addStudentCourse")
	@ResponseBody
	public boolean addStudentCourse(StudentCourse studentCourse){
		studentCourse.setCreatetime(new Date());
		System.out.println(studentCourse);
		return studentCourseService.addStudentCourse(studentCourse);
	}
	
	
	/**
	 * 老师添加学生
	 */
	@RequestMapping("teacherAddStudentCourse")
	@ResponseBody
	public String teacherAddStudentCourse(long courseId,long userNumber){
		Map<String, Object> map = userService.selectOneByUserNumber(userNumber);
		long studentId = 0;
		if(map == null || map.size() < 0){
			return Dictionary.S_NOT_EXIST;
		}else{
			studentId = Long.parseLong(map.get("id").toString());
		}
		
		if(!studentCourseService.selectOneByCourserId(studentId,courseId)){
			return Dictionary.S_EXIST;
		}
		
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setCreatetime(new Date());
		studentCourse.setCourseid(courseId);
		studentCourse.setStudentid(studentId);
		System.out.println(studentCourse);
		if(studentCourseService.addStudentCourse(studentCourse)){
			return Dictionary.S_SUCCESS;
		}else{
			return Dictionary.S_FAIL;
		}
	}
	

	/**
	 * 添加学生选课页面
	 * @return
	 */
	@RequestMapping("addStudentCoursePage")
	public String addStudentCoursePage(HttpServletRequest request){
		List<Map<String, Object>> listMap = courseService.selectAllCourse();
		request.setAttribute("courses", listMap);
		return "studentCourse/addStudentCourse";
	}
	
	
	/***
	 * 修改学生选课页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("editStudentCoursePage")
	public String editStudentCoursePage(long id,HttpServletRequest request){
		StudentCourse studentCourse = studentCourseService.selectOne(id);
		request.setAttribute("studentCourse", studentCourse);
		return "studentCourse/editStudentCourse";
	}
	
	/**
	 * 查询所有的学生选课
	 * @return
	 */
	@RequestMapping("studentCourseList")
	@ResponseBody
	public List<Map<String, Object>> selectAllStudentCourse(){
		return studentCourseService.selectAllStudentCourse();
	}
	
	/**
	 * 分页查询所有的学生选课
	 * @return
	 */
	
	@RequestMapping("studentCourseListBySearch")
	public ModelAndView selectAllStudentCourses(int pageNum,int pageSize,String search, String courseid){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("search", search);
		if(!courseid.equals("-1")){
			paramMap.put("courseid", courseid);
		}
		ModelAndView model = new ModelAndView();
		Map<String, Object> map = studentCourseService.selectAllStudentCourse(pageNum,pageSize,paramMap);
		model.addObject("studentCourses", map.get("listMap"));
		model.addObject("courses", courseService.selectAllCourse());
		
		if(map.get("count") != null){
			int count = Integer.parseInt(map.get("count").toString());
			int totalPage  = 0;
			if(count % 5 != 0 ){
				totalPage =count/5 + 1; 
			}else{
				totalPage =count/5;
			}
			model.addObject("count", count);
			model.addObject("totalPage", totalPage);
		}
		model.addObject("pageNum", pageNum);
		if(paramMap != null){
			model.addObject("search", paramMap.get("search"));
			model.addObject("courseid", paramMap.get("courseid"));
		}
		model.setViewName("studentCourse/studentCourse");
		return model;
	}
	
	
	@RequestMapping("StudentCourseListByPage")
	public String selectAllStudentCourse(int pageNum,int pageSize, HttpServletRequest request){
		
		Map<String, Object> map = studentCourseService.selectAllStudentCourse(pageNum,pageSize,null);
		request.setAttribute("studentCourses", map.get("listMap"));
		request.setAttribute("courses", courseService.selectAllCourse());
		
		if(map.get("count") != null){
			int count = Integer.parseInt(map.get("count").toString());
			int totalPage  = 0;
			if(count % 5 != 0 ){
				totalPage =count/5 + 1; 
			}else{
				totalPage =count/5;
			}
			request.setAttribute("count",count);
			request.setAttribute("totalPage",totalPage);
		}
		request.setAttribute("pageNum",pageNum);
		return "studentCourse/studentCourse";
	}
	
	/**
	 * 根据id查询学生选课
	 * @param id
	 * @return
	 */
	@RequestMapping("selectStudentCourse")
	@ResponseBody
	public StudentCourse selectOne(long id){
		return studentCourseService.selectOne(id);
	}
	

	
	/**
	 * 修改学生选课的信息
	 * @param StudentCourse
	 * @return
	 */
	@RequestMapping("editStudentCourse")
	@ResponseBody
	public boolean updateById(StudentCourse StudentCourse, String createTime){
		Date date = DateStrConvert.strToDate(createTime, "yyyy-MM-dd");
		StudentCourse.setCreatetime(date);
		return studentCourseService.updateById(StudentCourse);
	}
	
	/**
	 * 根据id删除学生选课
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteStudentCourse")
	@ResponseBody
	public boolean deleteById(long id){
		return studentCourseService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除学生选课
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyStudentCourses")
	@ResponseBody
	public boolean deleteById(String ids){
		return studentCourseService.deleteByIds(ids);
	}
	
	
	
	
}
