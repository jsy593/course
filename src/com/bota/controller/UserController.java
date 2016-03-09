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

import com.bota.bean.User;
import com.bota.service.ClassService;
import com.bota.service.CourseService;
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
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private CourseService courseService;
	/**
	 * 跳转到个人中心页面
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public String loginPage(User user,String password,HttpSession session){
		
		String result = userService.selectUserByUserNumber(user.getUsernumber());
		if(result == Dictionary.S_FAIL){
			return result;
		}else{
			Map<String, Object> ending = userService.verifyByUser(user);
			if(ending.get("status").toString() == Dictionary.S_SUCCESS){
				session.setAttribute("user", ending.get("map"));
			}
			return ending.get("status").toString();
		}
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("exit")
	public String exit(HttpSession session){		
		session.removeAttribute("user");
		return "redirect:login.html";
	}
	
	/**
	 * 我的信息页面
	 * @return
	 */
	@RequestMapping("myselfPage")
	public String myselfPage(){ 
		return "personalCenter/myself";
	}
	
	/**
	 * 根据课程查询所有的学生的id
	 * @param courseId
	 * @return
	 */
	@RequestMapping("studentsCourseSelection.do")
	public List<Map<String, Object>> studentsCourseSelection(long courseId){
		return userService.studentsCourseSelection(courseId);
	}
	
	/**
	 * 修改密码页面
	 * @return
	 */
	@RequestMapping("updatePwdPage")
	public String updatePwdPage(){
		return "personalCenter/updatePwd";
	}
	
	
	@RequestMapping("updatePwd")
	@ResponseBody
	public Map<String, Object> updatePwd(MapAction mapVo,HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = mapVo.getMapVo();
		String oldPwd = map.get("oldPwd").toString();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if(!user.get("password").equals(oldPwd)){
			resultMap.put("status", Dictionary.S_FAIL);
			resultMap.put("message", "原密码错误！");
		}else{
			map.put("id", user.get("id"));
			map.remove("oldPwd");
			boolean result = userService.updatePwd(map);
			if(result){
				resultMap.put("status", Dictionary.S_SUCCESS);
				resultMap.put("message", "修改成功！");
				user.put("password", map.get("newPwd"));
			}else{
				resultMap.put("status", Dictionary.S_FAIL);
				resultMap.put("message", "修改失败！");
			}
		}
		return resultMap;
	}
	
	
	/**
	 * 修改头像页面
	 * @return
	 */
	@RequestMapping("updateHeadImagePage")
	public String updateHeadImagePage(){
		return "personalCenter/updateHeadImage";
	}

	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateHeadImage")
	public String updateHeadImage(HttpServletRequest request, RedirectAttributes attribute) throws Exception {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		String id = userMap.get("id").toString();
		String imageUrl = FileUtil.upload(request, "upload", id);
		User user = new User();
		user.setId(Long.parseLong(id));
		user.setImageurl(imageUrl);
		if( userService.updateUserById(user)){
			request.setAttribute("message", "修改成功！");
			userMap.put("imageUrl", imageUrl);
			session.setAttribute("user", userMap);
		}else{
			request.setAttribute("error", "修改失败！");
		}
		return "personalCenter/myself";
	}
	
	
	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public boolean addUser(User user,String createTime){
		Date date = DateStrConvert.strToDate(createTime, "yyyy-MM-dd");
		if(user.getIdentity() != 2){//如果用户不是学生，就不要给他设置班级
			user.setClassid(null);
		}
		user.setCreatetime(date);
		user.setImageurl("images/2.jpg");//默认图片地址
		System.out.println(user);
		return userService.addUser(user);
	}
	

	/**
	 * 添加用户页面
	 * @return
	 */
	@RequestMapping("addUserPage")
	public String addUserPage(HttpServletRequest request){
		List<Map<String, Object>> listMap = classService.selectAllClasses();
		request.setAttribute("classes", listMap);
		return "user/addUser";
	}
	
	
	/***
	 * 修改用户页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("editUserPage")
	public String editUserPage(long id,HttpServletRequest request){
		User user = userService.selectOne(id);
		if(user.getIdentity() == 2){
			List<Map<String, Object>> listMap = classService.selectAllClasses();
			request.setAttribute("classes", listMap);
		}
		request.setAttribute("user", user);
		return "user/editUser";
	}
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	@RequestMapping("userList")
	@ResponseBody
	public List<Map<String, Object>> selectAllUser(){
		return userService.selectAllUser();
	}
	
	/**
	 * 分页查询所有的用户（管理员打开的页面）
	 * @return
	 */
	
	@RequestMapping("userListBySearch")
	public ModelAndView selectAllUsers(int pageNum,int pageSize,String identity,String search, String classid){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("identity", identity);
		paramMap.put("search", search);
		if(!classid.equals("-1")){
			paramMap.put("classid", classid);
		}
		ModelAndView model = new ModelAndView();
		Map<String, Object> map = userService.selectAllUser(pageNum,pageSize,paramMap);
		model.addObject("users", map.get("listMap"));
		model.addObject("classes", classService.selectAllClasses());
		
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
			model.addObject("identity", paramMap.get("identity"));
			model.addObject("classid", paramMap.get("classid"));
		}
		model.setViewName("user/user");
		return model;
	}
	
	
	@RequestMapping("userListByPage")
	public String selectAllUser(int pageNum,int pageSize,String identity, HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("identity", identity);
		Map<String, Object> map = userService.selectAllUser(pageNum,pageSize,paramMap);
		request.setAttribute("users", map.get("listMap"));
		request.setAttribute("classes", classService.selectAllClasses());
		
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
		request.setAttribute("identity",identity);
		request.setAttribute("pageNum",pageNum);
		return "user/user";
	}
	
	/**
	 * 老师打开的页面
	 * @param pageNum 页码
	 * @param pageSize  每页显示数量
	 * @param teacherId 老师id
	 * @param request
	 * @return
	 */
	@RequestMapping("studentListByPage")
	public String selectAllStudent(int pageNum,int pageSize,long teacherId, HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherId", teacherId);
		return commonExecute(pageNum,pageSize,paramMap,request);
	}
	
	/**
	 * 老师搜索的页面
	 * @param pageNum 页码
	 * @param pageSize  每页显示数量
	 * @param teacherId 老师id
	 * @param courseId  课程id
	 * @param request
	 * @return
	 */
	@RequestMapping("studentListBySearch")
	public String selectAllStudentBySearch(int pageNum,int pageSize,long teacherId,long courseId,String search,HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherId", teacherId);
		if(courseId != -1){
			paramMap.put("courseId", courseId);
		}
		paramMap.put("search", search);
		request.setAttribute("search",search);
		request.setAttribute("courseId",courseId);
		return commonExecute(pageNum,pageSize,paramMap,request);
	}
	
	/**
	 * 公共方法
	 * @param pageNum 页码
	 * @param pageSize 每页显示数量
	 * @param paramMap 条件map
	 * @param request
	 * @return
	 */
	public String commonExecute(int pageNum,int pageSize,Map<String, Object> paramMap, HttpServletRequest request){
		Map<String, Object> map = userService.selectAllUser(pageNum,pageSize,paramMap);
		request.setAttribute("users", map.get("listMap"));
		request.setAttribute("classes", classService.selectAllClasses());
		
		long teacherId = Long.parseLong(paramMap.get("teacherId").toString());
		request.setAttribute("courses",courseService.selectCourseByTeacherId(teacherId));
		
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
		request.setAttribute("teacherId",teacherId);
		request.setAttribute("pageNum",pageNum);
		return "user/user";
	}
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	@RequestMapping("selectUser")
	@ResponseBody
	public User selectOne(long id){
		return userService.selectOne(id);
	}
	
	
	/**
	 * 修改用户的信息
	 * @param User
	 * @return
	 */
	@RequestMapping("editUser")
	@ResponseBody
	public boolean updateById(User user, String createTime){
		Date date = DateStrConvert.strToDate(createTime, "yyyy-MM-dd");
		user.setCreatetime(date);
		return userService.updateById(user);
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public boolean deleteById(long id){
		return userService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyUsers")
	@ResponseBody
	public boolean deleteById(String ids){
		return userService.deleteByIds(ids);
	}
	
	
	
	
}
