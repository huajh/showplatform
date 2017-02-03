package Servlet.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBeans.Components.News;
import JavaBeans.People.Administor;
import Path.PathInfo;
import Path.FileUtil;

public class addNewsServlet extends HttpServlet {
	
	private ServletConfig config;
	ServletContext servletContext;
	
	public addNewsServlet() {
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

		PrintWriter out = response.getWriter();
		Administor administor = (Administor)request.getSession().getAttribute("administor");
		//String newsID = request.getParameter("newsID");
		News news = new News();
		
		String Title = request.getParameter("title");
		String Author = request.getParameter("author");
		String NewsContent = request.getParameter("newsContent");
		String newsType = request.getParameter("newsType");
		
		news.setTitle(Title);
		news.setAuthor(Author);
		news.setNewsContent(NewsContent);
		news.setNewsType(newsType);
		Date date = new Date();
		long name = date.getTime();
		news.setCreateTime(date);
		
		String newsFileName = FileUtil.getRandomFilename();		
		
		String url =servletContext.getRealPath("/")+PathInfo.NewsRootPath+"/"+newsFileName+"/";
		
		FileUtil.MkDirs(url);
		
		administor.addNews(news);
		
		request.getRequestDispatcher(PathInfo.AddNews).forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
