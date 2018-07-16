package cn.maiba.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.maiba.dao.ExpeDao;
import cn.maiba.log.UserLog;
import cn.maiba.model.User;

public class SessionListener implements HttpSessionAttributeListener {

	private Date logonDate = null;
	private User user = null;
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		//用户登录时记录用户登录时间
		if("user".equals(event.getName())) {
			logonDate = new Date();
			event.getSession().setAttribute("logonDate", logonDate);
			user = (User)event.getSession().getAttribute("user");
//			event.getSession().setAttribute("userId", user.getId());
//			event.getSession().setAttribute("userName", user.getUserName());
			UserLog.log("用户-"+user.getUserName()+"("+user.getId()+")"+"登录论坛");
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		//记录用户退出时间，计算时长
		if("user".equals(event.getName())) {
			Date logoutDate = new Date();
			HttpSession session = event.getSession();
//			Date logonDate = (Date)session.getAttribute("logonDate");
			int logonTimes = logoutDate.getMinutes() - logonDate.getMinutes();
			ExpeDao.addLogonTimes(user.getId()+"", logonTimes);
			UserLog.log("用户-"+user.getUserName()+"("+user.getId()+")"+"退出论坛");
//			ExpeDao.addLogonTimes(session.getAttribute("userId")+"", logonTimes);
//			String userName = (String)event.getSession().getAttribute("userName");
//			int userId = (int)event.getSession().getAttribute("userId");
//			UserLog.log("用户-"+userName+"("+userId+")"+"退出论坛");
//			session.removeAttribute("userName");
//			session.removeAttribute("userId");
			//用户退出登录销毁session
//			session.invalidate();
		}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
				
	}

}
