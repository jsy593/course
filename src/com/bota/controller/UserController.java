package com.bota.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

	@RequestMapping(value = "updateHeadImage")
	public String updateHeadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		// 解析器解析request的上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 先判断request中是否包含multipart类型的数据，
		if (multipartResolver.isMultipart(request)) {
			// 再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					String id = userMap.get("id").toString();
					String imageUrl = "upload/" + id + "/" + id + suffix;
					String path = request.getSession().getServletContext().getRealPath("/") + imageUrl;
					File filePath = new File(path);
					if (!filePath.exists()) {
						File parentFile = filePath.getParentFile();
						if (!parentFile.exists()) {
							FileUtil.createDirectory(parentFile.getPath());
						}
						filePath.createNewFile();
					}
					file.transferTo(filePath);
					User user = new User();
					user.setId(Long.parseLong(id));
					user.setImageurl(imageUrl);
					userService.updateUserById(user);
				}
			}
		}
		return "/success";
	}
	
}
