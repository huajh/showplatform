package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;

public class DeleteWorksServlet extends HttpServlet {


	public DeleteWorksServlet() {
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

		Administor administor = (Administor)request.getSession().getAttribute("administor");
		int worksID = (Integer)request.getAttribute("worksID");
		administor.deleteWorks(worksID);
		//
		//
		//
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
