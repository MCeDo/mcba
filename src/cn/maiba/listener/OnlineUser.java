package cn.maiba.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.maiba.model.User;

public class OnlineUser implements HttpSessionAttributeListener,HttpSessionListener {

	public static int onlineUsers = 0;
	public static int onlineAll = 0;

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
//		if("user".equals(event.getName())) {
//			onlineUsers++;
//		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
//		if("user".equals(event.getName())) {
//			if(onlineUsers>0) {
//				onlineUsers--;
//			}
//		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSession().isNew()) {
			onlineAll++;
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		if(onlineAll>0) {
			onlineAll--;
		}
	}
}
