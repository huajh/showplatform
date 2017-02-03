package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.krb5.internal.crypto.crc32;


import InterOperate.LoginType;

import JavaBeans.Contest.Contest;
import JavaBeans.People.Administor;
import JavaBeans.People.Guest;
import JavaBeans.People.Member;

import Path.PathInfo;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}	
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
					
		PrintWriter out = response.getWriter();			
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String loginType = request.getParameter("loginType");	
		String verifycode = request.getParameter("verifycode");
		
		String msg="";
		
		if(loginType.equals(LoginType.MEMBERTYPE))
		{			
			Member currentMember= (Member)Guest.Login(LoginType.MEMBERTYPE, uid, pwd);	
			
			if(currentMember==null)
			{
				msg = "用户名与密码不符";
				request.getRequestDispatcher(PathInfo.LOGIN +"?msg="+msg).forward(request,response);				

			}else {
				currentMember.load();				
				request.getSession().setAttribute("Member", currentMember);
				currentMember.setLastVisitTime(new Date());	
				currentMember.visit();
				currentMember.Save();
				msg="loginSuccess";
				request.getRequestDispatcher(PathInfo.HOMEPAGE+"?msg="+msg).forward(request,response);							
			} 
		}else if(loginType.equals(LoginType.ADMINTYPE))
		{
			if (!verifycode.equals(request.getSession().getAttribute("rand").toString())) {
				msg = "验证码错误";
				request.getRequestDispatcher(PathInfo.AdminLogin +"?msg="+msg).forward(request,response);
				return;
			}
			Administor currentAdmin= (Administor)Guest.Login(LoginType.ADMINTYPE, uid, pwd);
			
			if(currentAdmin==null)
			{
				msg = "用户名与密码不符";
				request.getRequestDispatcher(PathInfo.AdminLogin +"?msg="+msg).forward(request,response);
				
			}else {		
				currentAdmin.load();
				request.getSession().setAttribute("Administor", currentAdmin);
				msg="loginSuccess";
				request.getRequestDispatcher(PathInfo.AdminIndex +"?msg="+msg).forward(request,response);	
			}
		}else if(loginType.equals(LoginType.CONTESTTYPE))
		{			
			Member currentMember = (Member)request.getSession().getAttribute("Member");
			
			if(currentMember!=null)
			{
				Contest currentContest = currentMember.loginContest(uid, pwd);
				if(currentContest==null)
				{
					msg = "用户名与密码不符~";
					////
					//// need modify					
					request.getRequestDispatcher(PathInfo.InfoCenter +"?msg="+msg).forward(request,response);
				}
				else{
					msg="loginSuccess";
					currentContest.load();
					request.getSession().setAttribute("Contest", currentContest);
					request.getRequestDispatcher(PathInfo.MyHostContest+"?msg="+msg).forward(request,response);	
				}
			}else {
				request.getRequestDispatcher(PathInfo.HOMEPAGE).forward(request,response);	
			}
		}	
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
