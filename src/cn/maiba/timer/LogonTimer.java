package cn.maiba.timer;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.maiba.dao.ForBiddenUserDao;
import cn.maiba.model.ForbiddenUser;

public class LogonTimer extends Timer {

	/**
	 * 每小时扫描一次t_forbiddenuser，将锁定时间超过一小时的用户解锁
	 */
	public static void unLockUser() {
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<ForbiddenUser> fUserList = new ForBiddenUserDao().list();
				if(fUserList != null && !fUserList.isEmpty()) {
					Calendar now = Calendar.getInstance();
					for (ForbiddenUser fUser : fUserList) {
						if(now.getTimeInMillis() - fUser.getForbiddenTime().getTime()>60*1000) {
							//将被锁定一小时的用户解锁，从t_forbiddenuser表中删除
							new ForBiddenUserDao().delete(fUser.getUserName());
							System.out.println("unlock");
						}
					}
				}
			}
		}, 0, 1000);
	}

}
