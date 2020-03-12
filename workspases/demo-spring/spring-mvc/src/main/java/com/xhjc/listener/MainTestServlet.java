package com.xhjc.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MainTestServlet extends HttpServlet {

	private static final long serialVersionUID = -790034501106771934L;

	public void init() throws ServletException {
		System.out.println("下面的两个参数param1是在servlet中存放的");
		System.out.println(this.getInitParameter("param1"));
		System.out.println("下面的参数是存放在servletcontext中的");
		System.out.println(getServletContext().getInitParameter("context/param"));
	}
}
