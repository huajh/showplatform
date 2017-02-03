package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.Components.News;
import JavaBeans.People.Administor;
import Path.PathInfo;

public class EditNewsServlet extends HttpServlet {

	
	public EditNewsServlet() {
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
		
		News news = new News();
		int NewsID =Integer.parseInt(request.getParameter("newsID"));
		String Title = request.getParameter("title");
		String Author = request.getParameter("author");
		String NewsContent = request.getParameter("newsContent");
		String newsType = request.getParameter("newsType");
		
		news.setNewsID(NewsID);
		news.setTitle(Title);
		news.setAuthor(Author);
		news.setNewsContent(NewsContent);
		news.setNewsType(newsType);
		news.setCreateTime(new Date());
		
		news.Save();
		
		request.getRequestDispatcher(PathInfo.NewsList).forward(null, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
