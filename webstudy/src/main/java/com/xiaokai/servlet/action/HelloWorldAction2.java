package com.xiaokai.servlet.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldAction2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static String a = "wenhongkai";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("2 ini");
		super.init();
	}
	
	@Override
	public void destroy() {
		System.out.println("2 destroy");
		super.destroy();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print(a);
		super.service(req, resp);
	}

}
