package com.xiaokai.servlet.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * set utf-8 
 * 
 * @author Administrator
 */
public class EncodingFilter implements Filter {
	
	private static final String ENCODING = "UTF-8";

	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding(ENCODING);
		request.setCharacterEncoding(ENCODING); 
		chain.doFilter(request, response);
	}

	public void destroy() {
		// do nothing
	}
}
