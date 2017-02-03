package Filter;
import java.io.IOException;
import javax.servlet.*;
import javax.xml.ws.RespectBinding;

public class EncodingFilter implements Filter {

	private FilterConfig config;
	private String encoding = "IOS8859_1";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		config = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
		String s = config.getInitParameter("encoding");
		if(s!=null)
		{
			encoding = s;
		}				
	}

}
