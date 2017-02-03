package Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.servicetag.SystemEnvironment;

import Path.PathInfo;

public class MemberCheckFilter implements Filter {

	private String url = "/";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
				
		System.out.println("doFilter Member ");
		
		System.out.println(req.getContextPath()+url);
		
		if(session.getAttribute("Member")==null)
		{
			System.out.println("Memeberoff");
			
			res.sendRedirect(req.getContextPath()+url);
		}else {
			System.out.println("Memeber in");
			return;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		url = config.getInitParameter("url");
	}

}
