package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;
import Path.PathInfo;

public class addAdminServlet extends HttpServlet {

	public addAdminServlet() {
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
		String account = request.getParameter("account");
		Administor administor2 = new Administor(account);
		if(administor2.isExist())
		{
			out.println("<script>");
			out.println("alert('此用户名已存在！')");
			out.println("</script>");
		}
		else
		{
			String pwd = request.getParameter("pwd");
			administor.addAdministor(account, pwd);
			request.getRequestDispatcher(PathInfo.AddAdmin).forward(request, response);			
		}
	}

	public void init() throws ServletException {

	}

}
