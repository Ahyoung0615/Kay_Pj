package com.min.edu.comm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.edu.Utility;

public class Filter extends HttpFilter implements javax.servlet.Filter {

	private static final long serialVersionUID = -3628689132787311041L;
	private List<String> URLList;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String pattern = filterConfig.getInitParameter("pattern");
		URLList = Arrays.asList(pattern.split(","));
				
		System.out.println("제외 주소" + URLList);
	}
	
	@Override
	public void destroy() {
	
	}
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		String pathURL = req.getServletPath();
		System.out.println("pathURL ----" + pathURL);
		
		if(!URLList.contains(pathURL)) {
			HttpSession session = req.getSession();
			Object loginDto = session.getAttribute("loginDto");
			
			if(loginDto == null) {
				Utility.servlet_alert(response, "로그인 후 이용 가능합니다", "login.do");
			} else {
				chain.doFilter(request, response);
				
			}
		} else {
			chain.doFilter(request, response);
		}
		
	}
	
	
}
