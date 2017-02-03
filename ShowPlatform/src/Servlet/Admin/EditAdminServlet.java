package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;

public class EditAdminServlet extends HttpServlet {

	
	public EditAdminServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Administor administor = (Administor)request.getSession().getAttribute("administor");
		String RealName = request.getParameter("realName");
		String Sex = request.getParameter("sex");
		int Age = (Integer)request.getAttribute("age");
		String Pwd = request.getParameter("pwd");
		String Account = request.getParameter("account");
		
		administor.setRealName(RealName);
		administor.setSex(Sex);
		administor.setAge(Age);
		administor.setPwd(Pwd);
		administor.setAccount(Account);
		
		administor.Save();
		//
		//
		//
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
