package com.bota.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bota.bean.StudentCourse;
import com.bota.service.CourseService;
import com.bota.service.StudentCourseService;
import com.bota.service.UserService;
import com.bota.util.DateStrConvert;
import com.bota.util.Dictionary;

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
	public String addStudentCourse(StudentCourse studentCourse){
		if(!studentCourseService.selectOneByCourserId(studentCourse.getStudentid(),studentCourse.getCourseid())){
			return Dictionary.S_EXIST;
		}
		studentCourse.setCreatetime(new Date());
		System.out.println(studentCourse);
		if(studentCourseService.addStudentCourse(studentCourse)){
			return Dictionary.S_SUCCESS;
		}else{
			return Dictionary.S_FAIL;
		}
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
	
	
	/**
	 * 学生成绩查询
	 * @param pageNum 第几页
	 * @param pageSize 每页显示数量
	 * @param studentid 学生id
	 * @return
	 */
	 
	
	@RequestMapping("studentGrade")
	public ModelAndView studentGrade(int pageNum,int pageSize,String studentId,int status){
		ModelAndView model = new ModelAndView();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("studentid", studentId);
		if(status != -1){
			paramMap.put("status", status);
		}
		model.addObject("status", status);
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
		model.setViewName("studentCourse/studentGrade");
		return model;
	}
	
	
	
	@RequestMapping("studentCourseListByPage")
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
	 * 学生课表页面
	 * @param pageNum
	 * @param pageSize
	 * @param studentId
	 * @param createTime 时间区间
	 * @param flag 1:上学期 2：下学期
	 * @param request
	 * @return
	 */
	@RequestMapping("studentSchedule")
	public String selectAllStudentCourse(int pageNum,int pageSize,long studentId,String date,int flag, HttpServletRequest request){
		String start = null;
		String end = null;
		if(date == null || date.equals("-1")){
			Calendar cal = Calendar.getInstance();
	        int year = cal.get(Calendar.YEAR);//
	        int month=cal.get(Calendar.MONTH) + 1;//获取月份
			if(month>=2 && month <=8){//第二学期
				start = year+"-02-01";
				end= year+"-08-01";
				System.out.println((year-1)+"-"+year);
				request.setAttribute("date", (year-1)+"-"+year);
				request.setAttribute("flag", 2);
			}else if(month == 1){//如果是1月，显示第一学期的课表（假如现在是2016年1月，显示2015年08-2016年02月的课表）
				start = (year-1)+"-08-01";
				end= year+"-02-01";
				request.setAttribute("date", (year-1)+"-"+year);
				request.setAttribute("flag", 1);
			}else{//第一学期
				start = year+"-08-01";
				end= (year+1)+"-02-01";
				request.setAttribute("date", year+"-"+(year+1));
				request.setAttribute("flag", 1);
			}
		}else{
			String arr[] = date.split("-");
			if(flag == 1){
				start = arr[0]+"-08-01";
				end=arr[1]+"-02-01";
			}else{
				start = arr[1]+"-02-01";
				end = (Integer.parseInt(arr[1]) + 1)+"-08-01";
			}
			request.setAttribute("date", date);
			request.setAttribute("flag", flag);
		}
		Map<String, Object> map = studentCourseService.studentSchedule(pageNum,pageSize,start,end,studentId);
		request.setAttribute("studentCourses", map.get("listMap"));
		
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
		return "studentCourse/studentSchedule";
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
	public boolean deleteById(long id,long courseId){
		return studentCourseService.deleteById(id,courseId);
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
	
	/**
	 * 批量添加成绩
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("addGrades")
	@ResponseBody
	public boolean addGrades(HttpServletRequest request){
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		
		Object obj = request.getParameter("studentCourses");
		JSONArray jsonArray = new JSONArray(obj.toString());
		int iSize = jsonArray.length();
		for (int i = 0; i < iSize; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			map.put("grade", jsonObj.get("grade"));
			map.put("studentId", jsonObj.get("studentid"));
			map.put("courseId", jsonObj.get("courseid"));
			listMap.add(map);
		}
		boolean flag =  studentCourseService.addGrade(listMap);
		return flag;
	}
}
