package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;
import Path.PathInfo;

public class ChangeContestStatusServlet extends HttpServlet {

	public ChangeContestStatusServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Administor administor = (Administor)request.getSession().getAttribute("administor");
		int contestID = (Integer)request.getAttribute("contestID");
		String status = request.getParameter("status");
		if(status.equals("startupload"))
			administor.StartUploadContest(contestID);
		else if(status.equals("startscore"))
			administor.StartScoreContest(contestID);
		else if (status.equals("finish"))
			administor.FinishContest(contestID);
		request.getRequestDispatcher(PathInfo.ContestManage).forward(request, response);	
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
