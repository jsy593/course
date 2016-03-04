package com.bota.util;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  
  
  
public class LoginInterceptor implements HandlerInterceptor {  
  
    private static final String LOGIN_URL = "login.html";  
  
    @Override  
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {  
    	String url = req.getRequestURI();
    	if(url.endsWith("login.do")){	
    		return true;
    	}
        HttpSession session = req.getSession(true);  
        // 从session 里面获取用户的信息  
        Object obj = session.getAttribute("user");  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
        if (obj == null || "".equals(obj.toString())) {  
            res.sendRedirect(LOGIN_URL);  
        }  
        System.out.println("hdsfsa ");
        return true;  
    }  
  
    @Override  
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {  
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {  
    }  
  
}