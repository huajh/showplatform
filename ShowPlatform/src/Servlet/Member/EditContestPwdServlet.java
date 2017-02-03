package Servlet.Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;

import JavaBeans.Contest.Contest;
import JavaBeans.People.Member;

public class EditContestPwdServlet extends HttpServlet {


	public EditContestPwdServlet() {
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
		
		int contestID = Integer.parseInt(request.getParameter("ContestID"));
		
		if(member.getCertifiedStatus()<Member.Advanced)
		{
			///
			///  Ȩ�޲���
			///
			return;
		}
		
		Contest contest = new Contest(contestID);
		
		if(!contest.isExist())
		{
			///
			///
			return;
		}
		
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String reNewPwd = request.getParameter("reNewPwd");
		
		if(oldPwd.trim().equals(""))
		{
			//  ԭ����Ϊ��
			return;
		}		
		if(member.loginContest(String.valueOf(contestID), oldPwd)==null)
		{
			/// ԭ�������
			return;
		}
		if(newPwd.trim().equals("")||reNewPwd.trim().equals(""))
		{
			//  ����Ϊ��
			return;
		}
		if(!newPwd.equals(reNewPwd))
		{
			// ����ǰ�󲻷�
			//
		}else{
			contest.setAccessPwd(newPwd);
			contest.Save();
			///
		}					
	
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
