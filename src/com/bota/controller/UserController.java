package com.bota.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bota.bean.User;
import com.bota.service.UserService;
import com.bota.util.Dictionary;

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
	
	
	@RequestMapping("exit")
	public String exit(HttpSession session){		
		session.removeAttribute("user");
		return "redirect:login.html";
	}
	
	@RequestMapping("myselfPage")
	public String myselfPage(){ 
		return "personalCenter/myself";
	}
	@RequestMapping("studentsCourseSelection.do")
	public List<Map<String, Object>> studentsCourseSelection(long courseId){
		return userService.studentsCourseSelection(courseId);
	}
}
