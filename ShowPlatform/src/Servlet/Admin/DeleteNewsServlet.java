package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;

public class DeleteNewsServlet extends HttpServlet {


	public DeleteNewsServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Administor administor = (Administor)request.getSession().getAttribute("administor");
		int newsID = (Integer)request.getAttribute("newsID");
		administor.deleteNews(newsID);
		//
		//
		//
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
