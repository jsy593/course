package com.bota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bota.bean.User;
import com.bota.service.UserService;
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
		}else{
			request.setAttribute("error", "修改失败！");
		}
		return "personalCenter/myself";
	}
	
	/**
	 * 添加用户页面
	 * @return
	 */
	@RequestMapping("addUserPage")
	public String addUserPage(){
		return "person/addUser";
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public boolean addUser(User user){
		user.setImageurl("assets/avatars/user.jpg");
		return userService.addUser(user);
	}
}
