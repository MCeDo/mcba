package cn.maiba.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import cn.maiba.dao.ForBiddenUserDao;
import cn.maiba.model.ForbiddenUser;


@WebFilter(value="/User_handleLogon.do",dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD,DispatcherType.INCLUDE})
public class ForbiddenUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Forbidden Test");
		String userName = request.getParameter("userName");
		System.out.println(userName);
		ForbiddenUser fUser = null;
		if(userName != null) {
			fUser = new ForBiddenUserDao().load(userName);
			if(fUser != null) {
				request.setAttribute("failure_message", "该帐号被锁定一小时");
				request.getRequestDispatcher("/WEB-INF/jsp/userLogon.jsp").forward(request, response);
				return;
			}
		}
		
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
