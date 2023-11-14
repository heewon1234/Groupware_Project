package com.kdt.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginValidator implements HandlerInterceptor{
	
//	@Autowired
//	private HttpSession session;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String loginId = (String)session.getAttribute("loginId");
//		if(loginId != null) return true;
//		response.sendRedirect("/");
//		return false;
//	}
}
