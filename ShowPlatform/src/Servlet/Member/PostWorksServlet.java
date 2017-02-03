package Servlet.Member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import JavaBeans.Contest.Contest;
import JavaBeans.People.Member;
import JavaBeans.Works.LiteratureWorks;
import JavaBeans.Works.Works;
import Path.PathInfo;
import Path.FileUtil;

public class PostWorksServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public PostWorksServlet() {
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
		System.out.println("doPost");
		
		Member member = (Member)request.getSession().getAttribute("Member");	
		if(member.getCertifiedStatus()<Member.Medium)
		{
			/// 权限不够
			System.out.println(" 权限不够");
			return;
		}
		int contestID = Integer.parseInt(request.getParameter("ContestID"));
		Contest contest = new Contest(contestID);		
		if(!contest.isExist())
		{		
			/// contest not exist	
			return ;
		}	
		
		SmartUpload upload = new SmartUpload();		
		
		upload.initialize(config, request, response);	
		try {
			upload.setAllowedFilesList("rar,jpg, gif, png jpeg");
			upload.setDeniedFilesList("exe,bat");
			upload.setMaxFileSize(1024*1024*2);
			upload.setTotalMaxFileSize(1024*1024*10);
			upload.upload();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		contest.load();	
		
		String worksType = contest.getWorksType().trim();
		
		System.out.println(worksType);
		
		Works works = null;			
		if(worksType.equals(Works.DesignWorksType))			
		{
			works = member.createDesignWorks();	
		}
		else if(worksType.equals(Works.LiteratureWorksType)) 
		{
			works = member.createLiteratureWorks();				
			
		}else if(worksType.equals(Works.PhotographyWorksType)) 
		{
			works = member.createPhotographyWorks();			
		}else {
			return ;
		}		
		String Name = upload.getRequest().getParameter("Name");
		String Summary = upload.getRequest().getParameter("Summary");
		String CreativePlace = upload.getRequest().getParameter("CreativePlace");	
			
		works.setName(Name);
		works.setSummary(Summary);
		works.setCreativePlace(CreativePlace);
		works.setWorksType(worksType);	
		works.setContestID(contestID);
		works.setMemberID(member.getMemberID());
		
		String tmp_WorksPath = member.getMemberID()+"_"+FileUtil.getRandomFilename();
		String url =PathInfo.ContestRootPath+"/"+contestID+"/tmp/"+member.getMemberID()+"/"+tmp_WorksPath;		
		works.setWorksPath(url);
		url =servletContext.getRealPath("/")+url;        
        String mainUrl = url + "/Main/";
        String moreUrl = url + "/more/";
        String attachUrl = url + "/attach/";
        FileUtil.MkDirs(url);
        FileUtil.MkDirs(mainUrl);
        FileUtil.MkDirs(moreUrl);
        FileUtil.MkDirs(attachUrl);
        
		try {
			Files files = upload.getFiles();
			int count = files.getCount();	
			System.out.println("count: "+count);
			int cc = 1,cc2 = 1;
			for(int i=0;i<count;i++)
			{
			    File file =	files.getFile(i);
			    if(file.isMissing())
			    {
			    	continue;
			    }
			    String newUrl = "";
			    if(i==0)
			    {			    	 
			    	newUrl = mainUrl+"main."+file.getFileExt();			
			    }
			    else if(i==1)
			    {
			    	newUrl = moreUrl+member.getMemberID()+"_"+cc+"."+file.getFileExt();
			    	cc++;
			    }
			    else{
			    	newUrl = attachUrl+member.getMemberID()+"_"+cc2+"."+file.getFileExt();
			    	cc2++;
			    }
			    System.out.println(newUrl);
			    file.saveAs(newUrl,SmartUpload.SAVE_PHYSICAL);		    
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}						
		if(worksType.equals(Works.LiteratureWorksType))
		{
			LiteratureWorks literatureWorks = (LiteratureWorks)works;
			String content = request.getParameter("content");
			literatureWorks.setContent(content);
			literatureWorks.insert();
		}else {	
			System.out.println("insert");
			works.insert();
		}
		request.getRequestDispatcher(PathInfo.DesignWorksList).forward(request,response);		
	}


	public void init(ServletConfig config) throws ServletException {
		
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
