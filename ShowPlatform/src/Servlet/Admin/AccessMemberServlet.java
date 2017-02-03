package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;
import JavaBeans.People.Member;
import Path.PathInfo;

public class AccessMemberServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public AccessMemberServlet() {
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

		Administor administor = (Administor)request.getSession().getAttribute("administor");
		String[] memberID = request.getParameterValues("memberID");
		String[] status = request.getParameterValues("status");	
		
		for(int i=0;i<memberID.length;i++)
		{
			int accessType = Integer.parseInt(status[i]);
			administor.AccessMember(memberID[i], accessType);
		}
		
		request.getRequestDispatcher(PathInfo.ExamineMemberList).forward(request, response);
	}


	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
