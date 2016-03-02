package com.bota.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 登录过滤
 * 
 * @author bota
 */
public class SessionFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 不过滤的uri
		String[] notFilter = new String[] { "login.html", "index.html",
				"denglu.html", "login.jsp", "index.jsp" };

		// 请求的url
		String url = request.getRequestURI();
		String[] urlObje = url.split("/");
		// 是否过滤
		boolean doFilter = true;
		for (String s : notFilter) {
			if (url.indexOf(s) != -1) {
				// 如果uri中包含不过滤的uri，则不进行过滤
				doFilter = false;
				break;
			}
		}

		if (doFilter) {
			// 执行过滤
			// 从session中获取登录者实体
			Object obj = request.getSession().getAttribute("user");
			if (null == obj) {
				request.getRequestDispatcher("/login.html").forward(
						request, response);
			} else {
				// 如果session中存在登录者实体，则继续
				//filterChain.doFilter(request, response);
				String urlName = urlObje[urlObje.length-1];
				if(urlName.equalsIgnoreCase("course")) {
					urlName = "/login.html";
				}
				request.getRequestDispatcher(urlName).forward(
						request, response);
			}
		} else {
			// 如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}
	}
}