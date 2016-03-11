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

import com.bota.bean.Course;
import com.bota.service.CourseService;
import com.bota.service.MajorService;
import com.bota.service.TeacherCourseService;
import com.bota.service.UserService;
import com.bota.util.DateStrConvert;

/**
 * 
 * @author bota
 */
@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherCourseService teacherCourseService;
	
	/**
	 * 添加课程
	 */
	@RequestMapping("addCourse")
	@ResponseBody
	public boolean addCourse(Course course,String createTime){
		Date date = DateStrConvert.strToDate(createTime, "yyyy-MM-dd");
		course.setCreatetime(date);
		course.setNumberspace(course.getNumberlimit());
		System.out.println(course);
		return courseService.addCourse(course);
	}
	

	/**
	 * 添加课程页面
	 * @return
	 */
	@RequestMapping("addCoursePage")
	public String addCoursePage(HttpServletRequest request){
		List<Map<String, Object>> teacherMap = userService.selectAllUserByIdentity(1);//1代表老师的角色
		List<Map<String, Object>> majorMap = majorService.selectAllMajor();
		request.setAttribute("teachers", teacherMap);
		request.setAttribute("majors", majorMap);
		return "course/addCourse";
	}
	
	
	/***
	 * 修改课程页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("editCoursePage")
	public String editCoursePage(long id,HttpServletRequest request){
		Course course = courseService.selectOne(id);
		List<Map<String, Object>> teacherMap = userService.selectAllUserByIdentity(1);//1代表老师的角色
		List<Map<String, Object>> majorMap = majorService.selectAllMajor();
		request.setAttribute("teachers", teacherMap);
		request.setAttribute("majors", majorMap);
		request.setAttribute("course", course);
		return "course/editCourse";
	}
	
	/**
	 * 查询所有的课程
	 * @return
	 */
	@RequestMapping("courseList")
	@ResponseBody
	public List<Map<String, Object>> selectAllCourse(){
		return courseService.selectAllCourse();
	}
	
	/**
	 * 分页查询所有的课程
	 * @return
	 */
	
	@RequestMapping("courseListBySearch")
	public ModelAndView selectAllCourses(int pageNum,int pageSize,long teacherId,String search, long specialtyId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(teacherId != -1){
			paramMap.put("teacherId", teacherId);
		}
		if(specialtyId != -1){
			paramMap.put("specialtyId", specialtyId);
		}
		paramMap.put("search", search);
		ModelAndView model = new ModelAndView();
		Map<String, Object> map = courseService.selectAllCourse(pageNum,pageSize,paramMap);
		model.addObject("courses", map.get("listMap"));
		model.addObject("teachers", userService.selectAllUserByIdentity(1));//1代表老师角色
		model.addObject("majors", majorService.selectAllMajor());
		
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
			model.addObject("teacherId", paramMap.get("teacherId"));
			model.addObject("specialtyId", paramMap.get("specialtyId"));
		}
		model.setViewName("course/course");
		return model;
	}
	
	/**
	 * 查询老师的课程
	 * @param pageNum
	 * @param pageSize
	 * @param teacherId
	 * @param request
	 * @return
	 */
	@RequestMapping("courseListByTeacher")
	public String selectAllCourseByTeacher(int pageNum,int pageSize,String teacherId,HttpServletRequest request){
		Map<String, Object> paramMap = null;
		paramMap = new HashMap<String, Object>();
		paramMap.put("teacherId",teacherId);
		
		Map<String, Object> map = courseService.selectAllCourseByTeacher(pageNum,pageSize,paramMap);
		List<Map<String, Object>> teacherMap = userService.selectAllUserByIdentity(1);//1代表老师的角色
		List<Map<String, Object>> majorMap = majorService.selectAllMajor();
		request.setAttribute("courses", map.get("listMap"));
		request.setAttribute("teachers", teacherMap);
		request.setAttribute("majors", majorMap);
		
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
		return "course/course";
	}
	
	
	
	@RequestMapping("courseListByPage")
	public String selectAllCourse(int pageNum,int pageSize,HttpServletRequest request){
		Map<String, Object> paramMap = null;
		Map<String, Object> map = courseService.selectAllCourse(pageNum,pageSize,paramMap);
		List<Map<String, Object>> teacherMap = userService.selectAllUserByIdentity(1);//1代表老师的角色
		List<Map<String, Object>> majorMap = majorService.selectAllMajor();
		request.setAttribute("courses", map.get("listMap"));
		request.setAttribute("teachers", teacherMap);
		request.setAttribute("majors", majorMap);
		
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
		return "course/course";
	}
	
	/**
	 * 学生页面显示的可以选的课程
	 * @param pageNum
	 * @param pageSize
	 * @param isFinish 0：不可选  1：可选
	 * @param request
	 * @return
	 */
	@RequestMapping("courseListByStudent")
	public String selectAllCourseByStudent(int pageNum,int pageSize,int isFinish,int studentId, HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isFinish",isFinish);
		paramMap.put("studentId",studentId);
		return commonExecute(pageNum,pageSize,paramMap,request);
	}
	
	public  String commonExecute(int pageNum,int pageSize,Map<String, Object> paramMap,HttpServletRequest request){
		Map<String, Object> map = courseService.selectAllCourseByStudent(pageNum,pageSize,paramMap);
		List<Map<String, Object>> teacherMap = userService.selectAllUserByIdentity(1);//1代表老师的角色
		List<Map<String, Object>> majorMap = majorService.selectAllMajor();
		request.setAttribute("courses", map.get("listMap"));
		request.setAttribute("teachers", teacherMap);
		request.setAttribute("majors", majorMap);
		
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
		return "course/course";
	}
	
	/**
	 * 根据id查询课程
	 * @param id
	 * @return
	 */
	@RequestMapping("selectCourse")
	@ResponseBody
	public Course selectOne(long id){
		return courseService.selectOne(id);
	}
	
	/**
	 * 修改课程的信息
	 * @param Course
	 * @return
	 */
	@RequestMapping("editCourse")
	@ResponseBody
	public boolean updateById(Course course, String createTime,HttpSession session){
		Date date = DateStrConvert.strToDate(createTime, "yyyy-MM-dd");
		course.setCreatetime(date);
		@SuppressWarnings("unchecked")
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		if(userMap.get("identity").equals(1)){
			courseService.updateCourse(course);//需要修改teacherCourse表
		}
		return courseService.updateById(course);
	}
	
	
	/**
	 * 修改课程的可选状态
	 * @param Course
	 * @return
	 */
	@RequestMapping("editStatus")
	@ResponseBody
	public boolean editStatus(Course course){
		return courseService.updateById(course);
	}
	
	/**
	 * 根据id删除课程
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteCourse")
	@ResponseBody
	public boolean deleteById(long id){
		return courseService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除课程
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyCourses")
	@ResponseBody
	public boolean deleteById(String ids){
		return courseService.deleteByIds(ids);
	}
	
	
}
