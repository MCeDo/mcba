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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.maiba.util.UrlUtil;


/**
 * Servlet Filter implementation class LogonFilter
 */
@WebFilter(value="/*",dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE})
public class LogonFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogonFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURI();
		if(url.contains("logon/")) {
			if(session.getAttribute("user")!=null) {
				chain.doFilter(httpRequest, httpResponse);
			}else {
				session.setAttribute("forwardURL", UrlUtil.getURL(httpRequest));
				request.getRequestDispatcher("/WEB-INF/jsp/result/user-non-logon.jsp").forward(request, response);
				return;
			}
		}else {
			chain.doFilter(httpRequest, httpResponse);
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
