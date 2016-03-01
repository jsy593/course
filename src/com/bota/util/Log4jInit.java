package com.bota.util;

import org.apache.log4j.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {
	@Override
	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("log4j");
		// if the log4j-init-file is not set, then no point in trying
		System.out.println("................log4j   start ");
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
			System.out.println(prefix + file);
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
