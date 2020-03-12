package com.xhjc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

public class SysListener extends HttpServlet implements ServletContextListener {

	private static final long serialVersionUID = -6058759221216351309L;

	public void contextDestroyed(ServletContextEvent sce) {

	}

	//用于在容器开启时,操作
	public void contextInitialized(ServletContextEvent sce) {
	    String rootpath = sce.getServletContext().getRealPath("/");
	    System.out.println("-------------rootPath:"+rootpath);

	   if (rootpath != null) {
	     rootpath = rootpath.replaceAll("\\\\", "/");
	    } else {
	     rootpath = "/";
	    }
	    if (!rootpath.endsWith("/")) {
	     rootpath = rootpath + "/";
	    }
	   
	    System.out.println("Application Run Path:" + rootpath);
	    String urlrewrtie = sce.getServletContext().getInitParameter("urlrewrite");
	    String poststoragemode = sce.getServletContext().getInitParameter("poststoragemode");
	    
	    boolean burlrewrtie = false;
	    if (urlrewrtie != null) {
	     burlrewrtie = Boolean.parseBoolean(urlrewrtie);
	    }
	 
	    System.out.println("Use Urlrewrite:" + burlrewrtie);
	    System.out.println("poststoragemode:" + poststoragemode);

	   }

}
