package Servlet.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;

import JavaBeans.People.Member;
import JavaBeans.Works.DesignWorks;
import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.PhotographyWorks;
import JavaBeans.Works.Works;

public class DoScoreServlet extends HttpServlet {


	public DoScoreServlet() {
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
		
		Member member = (Member)request.getSession().getAttribute("Member");
		if(member.getCertifiedStatus()<Member.Primary)
		{
			/// 非正式会员
			return;
		}
		String worksType = request.getParameter("worksType");
		int worksID = Integer.parseInt(request.getParameter("worksID"));
		int value = Integer.parseInt(request.getParameter("value"));
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
		if(member.doScore(works, value))
		{
			//success
		}else {
			// fail
		}
		

	}

	public void init() throws ServletException {

		
	}

}
