package cn.maiba.dao;

import cn.maiba.model.Experience;

public class ExpeDao {

	//增加登录次数
	public static boolean addLogon(String userId) {
		Dao dao = new Dao();
		Experience exp = (Experience) dao.list(Experience.class, "select * from t_experience where userId=?",
					new Object[]{userId}, null).get(0);
		exp.setLogonCount(exp.getLogonCount()+1);
		return dao.update(exp);
	}
	
	//增加发帖数量
	public static boolean addPublish(String userId) {
		Dao dao = new Dao();
		Experience exp = (Experience) dao.list(Experience.class, "select * from t_experience where userId=?",
					new Object[]{userId}, null).get(0);
		exp.setPublishNums(exp.getPublishNums()+1);
		return dao.update(exp);
	}
	
	//增加评论数量
	public static boolean addComment(String userId) {
		Dao dao = new Dao();
		Experience exp = (Experience) dao.list(Experience.class, "select * from t_experience where userId=?",
					new Object[]{userId}, null).get(0);
		exp.setCommentNums(exp.getCommentNums()+1);
		return dao.update(exp);
	}
	
	//增加登录时长
	public static boolean addLogonTimes(String userId, int logonTmes) {
		Dao dao = new Dao();
		Experience exp = (Experience) dao.list(Experience.class, "select * from t_experience where userId=?",
					new Object[]{userId}, null).get(0);
		exp.setLogonTimes(exp.getLogonTimes()+logonTmes);
		return dao.update(exp);
	}
}
