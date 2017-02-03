package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.Contest.Contest;
import JavaBeans.People.Administor;
import JavaBeans.People.Member;
import JavaBeans.Works.Works;
import Path.PathInfo;
import Path.FileUtil;

public class AccessContestServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public AccessContestServlet() {
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
			throws ServletException, IOException
	{
		Administor administor = (Administor)request.getSession().getAttribute("administor");
		int contestID = (Integer)request.getAttribute("contestID");
		String status = request.getParameter("status"); 
		if(status.equals("Refuse"))
		{
			//
			// É¾³ýÄ¿Â¼
			//
			administor.RefuseContest(contestID);
		}
		else if(status.equals("Access"))
		{			
			String url =servletContext.getRealPath("/")+PathInfo.ContestRootPath+"/"+contestID+"/";			    
			FileUtil.MkDirs(url);
			administor.AccessContest(contestID);			
		}
		request.getRequestDispatcher(PathInfo.ExamineContestList).forward(request, response);

	}


	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
