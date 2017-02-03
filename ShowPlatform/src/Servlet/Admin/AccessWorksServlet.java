package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.People.Administor;
import JavaBeans.Works.Works;
import Path.PathInfo;
import Path.FileUtil;

public class AccessWorksServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public AccessWorksServlet() {
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
		Administor administor = (Administor)request.getSession().getAttribute("administor");
		
		int contestID = Integer.parseInt(request.getParameter("contestID"));
		int worksID =Integer.parseInt(request.getParameter("worksID"));
		String status = request.getParameter("status");
		
		if(status.equals("Allow"))
		{
			String url =servletContext.getRealPath("/")+PathInfo.ContestRootPath+"/"+contestID+"/Works/"+worksID+"/";
			//
			// 移动文件夹 ？？
			//
			FileUtil.MkDirs(url);
	        try {
				administor.AccessWorks(worksID,url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(status.equals("Refuse"))
		{
			//
			// 删除目录 ？？
			//
			administor.refuseWorks(worksID);
		}
		request.getRequestDispatcher(PathInfo.ExamineWorksList).forward(request, response);
	}

	
	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
