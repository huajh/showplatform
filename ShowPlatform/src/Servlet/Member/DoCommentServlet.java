package Servlet.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.handlers.message_rfc822;

import JavaBeans.People.Member;
import JavaBeans.Works.DesignWorks;
import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.PhotographyWorks;
import JavaBeans.Works.Works;
import Message.Message;
import Path.PathInfo;

public class DoCommentServlet extends HttpServlet {


	public DoCommentServlet() {
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
		String commentContent = request.getParameter("Commentcontent");
		int value = Integer.parseInt(request.getParameter("score"));
		int worksID = Integer.parseInt(request.getParameter("worksID"));
		System.out.println("value: "+value);
		System.out.println("commentContent: "+commentContent);
		System.out.println("worksID: "+worksID);
		String worksType = Works.getWorksType(worksID).trim();
		System.out.println("worksType: "+worksType);
		Works works = null; 
		if(worksType.equals(Works.DesignWorksType))
		{
			works = new DesignWorks(worksID);
		}else if(worksType.equals(Works.LiteratureWorksType))
		{
			works = new LiteratureWorks(worksID);
		}else if(worksType.equals(Works.PhotographyWorksType))
		{
			works = new PhotographyWorks(worksID);
		}
		works.load();
		String replyMemberID = works.getMemberID().trim();
		member.doScore(works, value);
		member.doComment(worksID, commentContent, replyMemberID);
		request.getRequestDispatcher(PathInfo.DesignworksDetail+"?WorksID="+worksID).forward(request,response);	
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
