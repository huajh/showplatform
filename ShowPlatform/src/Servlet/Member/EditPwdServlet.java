package Servlet.Member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import InterOperate.LoginType;
import JavaBeans.People.Guest;
import JavaBeans.People.Member;
import Path.PathInfo;

public class EditPwdServlet extends HttpServlet {

	public EditPwdServlet() {
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
		
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String reNewPwd = request.getParameter("reNewPwd");
		
		String msg="";
		if(oldPwd.trim().equals(""))
		{
			///
			msg = "«Î ‰»Î√‹¬Î";
			request.getRequestDispatcher(PathInfo.EditInfo+"?msg="+msg).forward(request,response);	
			
		}
		if(Guest.Login(LoginType.MEMBERTYPE, member.getMemberID(), oldPwd)==null)
		{
			// ‘≠√‹¬Î¥ÌŒÛ
			msg = "√‹¬Î¥ÌŒÛ";
			request.getRequestDispatcher(PathInfo.EditInfo+"?msg="+msg).forward(request,response);	
			
		}		
		if(newPwd.trim().equals("")||reNewPwd.trim().equals(""))
		{			
			String msg2 = "√‹¬Î≤ªƒ‹Œ™ø’";
			request.getRequestDispatcher(PathInfo.EditInfo+"?msg2="+msg2).forward(request,response);				 
			
		}
		if(!newPwd.equals(reNewPwd))
		{
			String msg2 = "«∞∫Û√‹¬Î≤ª∑˚";
			request.getRequestDispatcher(PathInfo.EditInfo+"?msg2="+msg2).forward(request,response);				 
			
		}else{
			member.setPwd(newPwd);
			member.Save();			
			request.getRequestDispatcher(PathInfo.InfoCenter).forward(request,response);				
		}			
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
