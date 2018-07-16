package cn.maiba.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE})
public class CharsetFilter implements Filter {

	private String encoding;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fconfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = fconfig.getInitParameter("requestEncoding");
		if(encoding==null) {
			encoding = "utf-8";
		}
	}

}
