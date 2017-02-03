package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBOperator.DBOperator;
import InterOperate.StringUtil;
import JavaBeans.People.Guest;
import JavaBeans.People.Member;
import Path.PathInfo;
import Path.FileUtil;

public class RegisterServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public RegisterServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  
 
		request.setCharacterEncoding("utf-8");
		
		String uid = request.getParameter("uid");
		Member member = new Member(uid);
		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		String RegisterEmail = request.getParameter("RegisterEmail");
		
		System.out.println(uid);
		System.out.println(pwd);
		
		String msg = "",msg2 = "",msg3="";
		String forword = PathInfo.REGISTER;
		
		if(uid==null ||uid.equals(""))
		{
			msg = "账号不能为空";
		}
		else if(!StringUtil.isEmail(RegisterEmail))
		{
			msg2 = "邮箱格式不正确";
		}
		else if(pwd==null||pwd.equals("")||repwd==null||repwd.equals(""))
		{
			msg3 = "密码不能为空";
		}
		else if(!pwd.equals(repwd))
		{
			msg3 = "密码不一致";
		}
		else if(member.isExist())
		{
			msg = "亲，账号已经被注册哦~~";
			
		}else{
			member.setPwd(pwd);
			member.setRegisterEmail(RegisterEmail);			
			member.setRegisterTime(new Date());				
			member.setCertifiedStatus(Member.Primary); //			
			String url =servletContext.getRealPath("/")+PathInfo.UsersRootPath+"/"+member.getMemberID()+"/";
			member.setMemberPath(url);
			FileUtil.MkDirs(url);	        
			member.insert();
			request.getSession().setAttribute("Member", member);	
			forword = PathInfo.HOMEPAGE;
		}
		
		request.getRequestDispatcher(forword+"?msg="+msg+"&msg2="+msg2+"&msg3="+msg3)
			.forward(request, response);		
	}

	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
