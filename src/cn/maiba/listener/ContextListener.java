package cn.maiba.listener;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.maiba.dao.ForBiddenUserDao;
import cn.maiba.model.ForbiddenUser;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		//每小时扫描一次，解锁已锁定超过一小时的用户
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<ForbiddenUser> fUserList = new ForBiddenUserDao().list();
				System.out.println(fUserList.size());
				if(fUserList != null) {
					Calendar now = Calendar.getInstance();
					System.out.println("定时器扫描，解除锁定用户");
					for (ForbiddenUser fUser : fUserList) {
						if(now.getTimeInMillis() - fUser.getForbiddenTime().getTime()>2*60*1000) {
							//将被锁定一小时的用户解锁，从t_forbiddenuser表中删除
							new ForBiddenUserDao().delete(fUser.getUserName());
							System.out.println("un...lock");
						}
					}
				}
			}
		}, 0, 60*1000);
	}

}
