package Servlet.Member;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.applet.resources.MsgAppletViewer;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import JavaBeans.People.Member;
import Path.FileUtil;
import Path.PathInfo;

public class EditInfoServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public EditInfoServlet() {
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
		
		Member member = (Member)request.getSession().getAttribute("Member");		
		String msg="";
		SmartUpload upload = new SmartUpload();		
		try {					 		
			upload.initialize(config, request, response);
			upload.setAllowedFilesList("rar,jpg,png,gif,jpeg");
			upload.setDeniedFilesList("exe,bat,rar,html,jsp") ;
			upload.setMaxFileSize(1024*1024*2);
			upload.setTotalMaxFileSize(1024*1024*10);			
			upload.upload("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}			
		String RealName = upload.getRequest().getParameter("RealName");	
		String Sex = upload.getRequest().getParameter("Sex");		
		int Age = Integer.parseInt(upload.getRequest().getParameter("Age"));				
		String birthday = upload.getRequest().getParameter("Birthday");
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date Birthday = null;
		try {
			Birthday = format.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block	
			msg = "时间格式不正确";
			request.getRequestDispatcher(PathInfo.EditInfo+"?msg="+msg).forward(request,response);
			e.printStackTrace();
		}  				
		String Constellation = upload.getRequest().getParameter("Constellation");
		String LiveProvince = upload.getRequest().getParameter("LiveProvince");
		String LiveCity = upload.getRequest().getParameter("LiveCity");
		String GraduateSchool = upload.getRequest().getParameter("GraduateSchool");
		String Profession = upload.getRequest().getParameter("Profession");
		String email = upload.getRequest().getParameter("email");
		String Introduction = upload.getRequest().getParameter("Introduction");	
		String url =PathInfo.UsersRootPath+"/"+member.getMemberID()+"/";
		member.setMemberPath(url);
		System.out.println(url);
		url =servletContext.getRealPath("/")+url;
		System.out.println(url);
        FileUtil.MkDirs(url);		        
		try {
			Files files = upload.getFiles();
			int count = files.getCount();				
			for(int i=0;i<count;i++)
			{
			    File file =	files.getFile(i);			    
			    if(file.isMissing())
			    {
			    	continue;
			    }				
			    url += "head."+file.getFileExt();  //主要图片
		        System.out.println("url : "+url);
			    file.saveAs(url,SmartUpload.SAVE_PHYSICAL);		    
			}
			System.out.println(count);
			
		} catch (SmartUploadException e) {
			
			e.printStackTrace();
		}	
		
		member.setAge(Age);
		member.setBirthday(Birthday);
		member.setConstellation(Constellation);
		member.setGraduateSchool(GraduateSchool);
		member.setIntroduction(Introduction);
		member.setLiveCity(LiveCity);
		member.setLiveProvince(LiveProvince);
		member.setRealName(RealName);
		member.setSex(Sex);		
		member.setProfession(Profession);
		member.setRegisterEmail(email);		
		member.setBirthday(Birthday);
		member.Save();
		
		request.getRequestDispatcher(PathInfo.InfoCenter).forward(request,response);	
	}


	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}


}
