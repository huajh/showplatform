package Servlet.Member;

import java.awt.image.TileObserver;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


import InterOperate.StringUtil;
import JavaBeans.Contest.Contest;
import JavaBeans.People.Member;
import Path.FileUtil;
import Path.PathInfo;

public class PostContestServlet extends HttpServlet {

	private ServletConfig config;
	ServletContext servletContext;
	
	public PostContestServlet() {
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
				
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Member member = (Member)request.getSession().getAttribute("Member");			
		Contest newContest = member.createContest();				
		
		SmartUpload upload = new SmartUpload();
		
		try {
					 		
			upload.initialize(config, request, response);
			upload.setAllowedFilesList("jpg,png,gif,jpeg");
			upload.setDeniedFilesList("exe,bat") ;
			upload.setMaxFileSize(1024*1024*2);
			upload.setTotalMaxFileSize(1024*1024*10);			
			upload.upload("utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}			
		String Title = upload.getRequest().getParameter("Title");
		String ContestType = upload.getRequest().getParameter("ContestType");		
		String ContestContent = upload.getRequest().getParameter("ContestContent");
		String beignTime  = upload.getRequest().getParameter("BeignTime");
		String endTime  = upload.getRequest().getParameter("EndTime");
		System.out.println(Title);
		System.out.println(ContestType);
		System.out.println(ContestContent);
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");     
		Date BeignTime =null;
		Date EndTime = null;
		try {         
			BeignTime = format.parse(beignTime);
			EndTime = format.parse(endTime);  
			System.out.println("BeignTime: "+BeignTime);
			System.out.println("EndTime: "+EndTime);			
	     } 
		catch (Exception e) {  		
	            e.printStackTrace();  	            
		}  		
		int LeastLength = 100;
		String msg = "",msg2="",msg3="";	
		String forword = PathInfo.PostContest;
		if(Title==null||Title.equals(""))
		{
			msg="比赛名不能为空";
		}		
		else if(ContestType==null||ContestType.equals(""))
		{
			msg2="比赛类型不能为空";
		}
		
		else if(ContestContent==null || ContestContent.length()<LeastLength)
		{
			msg3="比赛说明太短（至少100个字符）";
			
		}
		else
		{
			String WorksType = ContestType+"Works";		
			newContest.setWorksType(WorksType);	
			String OrganizerID = member.getMemberID();			
			String  fileName= OrganizerID+"_"+FileUtil.getRandomFilename();
			
			String url =PathInfo.ContestRootPath+"/Remain_To_Verify/"+fileName+"/";
			newContest.setContestPath(url);
			url =servletContext.getRealPath("/")+url;
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
				    url += "Main."+file.getFileExt();  //主要图片
			        System.out.println("url : "+url);
				    file.saveAs(url,SmartUpload.SAVE_PHYSICAL);		    
				}
				System.out.println(count);
				
			} catch (SmartUploadException e) {
				
				e.printStackTrace();
			}				
			newContest.setTitle(Title);		
			newContest.setContestContent(ContestContent);			
			newContest.setContestType(ContestType);
			newContest.setBeignTime(BeignTime);
			newContest.setEndTime(EndTime);
			newContest.setOrganizerID(OrganizerID);
						
			if(member.PostContest(newContest))
			{				     
				msg="比赛申请已发出，请等待审核";				
				forword = PathInfo.successPage;				
			}else {
				/// fail
				msg="发生异常错误，申请比赛失败";
				forword = PathInfo.failedPage;				
			}
			forword = PathInfo.successPage;
		}				
		request.getRequestDispatcher(forword+"?msg="+msg+"&msg2="+msg2+"&msg3="+msg3).
			forward(request,response);				
	}

	public void init(ServletConfig config) throws ServletException {
	
		this.config = config;
		this.servletContext = config.getServletContext();
	}

}
