package com.xiaokai.servlet.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	
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
		System.out.println("init");
		super.init();
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy");
		super.destroy();
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println(req.getRemoteHost());
		System.out.println(req.getProtocol());
		HttpServletRequest request = (HttpServletRequest) req;
		Enumeration headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			Object o = headerNames.nextElement();
			System.out.println(o.toString() + "       " + request.getHeader(o.toString()));
		}

		HttpServletResponse response = (HttpServletResponse) res;

		String origin = request.getHeader("origin");
		if(origin != null && origin.length() != 0){
			response.setHeader("Access-Control-Allow-Origin", origin);
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}

		res.getWriter().print("h1");
		HelloWorldAction2.a = "wanxiaodou";
		super.service(req, res);
	}

}
