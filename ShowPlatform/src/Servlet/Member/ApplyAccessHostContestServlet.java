package Servlet.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Member;
import Message.Message;

import Path.PathInfo;

public class ApplyAccessHostContestServlet extends HttpServlet {

	
	public ApplyAccessHostContestServlet() {
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
		
		Member member = (Member)request.getSession().getAttribute("Member");
		
		if(member.ApplyAccessHostContest())
		{
			//success
			request.getRequestDispatcher(PathInfo.InfoCenter+"?ApplyContestmsg="+Message.Apply_Send).forward(request,response);	
			
		}else {
			//fail
			request.getRequestDispatcher(PathInfo.InfoCenter+"?ApplyContestmsg="+Message.Apply_Fail).forward(request,response);	
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
