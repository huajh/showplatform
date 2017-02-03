package Servlet.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.protocol.ForwardException;

import JavaBeans.People.Member;
import Message.Message;
import Path.PathInfo;

public class ApplyAccessUploadServlet extends HttpServlet {


	public ApplyAccessUploadServlet() {
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
		PrintWriter out = response.getWriter();
		if(member.ApplyAccessUpLoad())
		{
			request.getRequestDispatcher(PathInfo.InfoCenter+"?ApplyUploadmsg="+Message.Apply_Send).forward(request,response);
			
		}else {
		//	out.println("<script>");
		//	out.println("alert('�Բ�������û������μӱ�����Ȩ��')");
		//	out.println("location.href='"+request.getContextPath()+PathInfo.InfoCenter+"'</script>");
			request.getRequestDispatcher(PathInfo.InfoCenter+"?ApplyUploadmsg="+Message.Apply_Fail).forward(request,response);
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
