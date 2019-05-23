package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	
	@RequestMapping(value="/common/login",method=RequestMethod.GET)
	public String loginGET() {
		return "common/login";
	}
	
	@RequestMapping(value="/common/login",method=RequestMethod.POST)
	public void loginPOST(LoginRequest loginReq,
							String id, String pwd,
							HttpSession session,
							HttpServletResponse response,
							HttpServletRequest request) throws IOException {
		
		session.setAttribute("loginUser", loginReq.getId());
		System.out.println(loginReq);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>alert('로그인 성공했습니다');</script>");
	}
	
}




