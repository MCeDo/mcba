package cn.maiba.dao;

import java.util.ArrayList;
import java.util.List;

import cn.maiba.model.ForbiddenUser;
import cn.maiba.model.PageBean;
import cn.maiba.model.User;

public class UserDao {

	public List<User> list() {
		Dao dao = new Dao();
		List<User> userList = dao.list(User.class, null);
		return userList;
	}
	
	public List<User> list(String userName) {
		List<User> list = new ArrayList<>();
		Dao dao = new Dao();
		String sql = "select * from t_user where userName=?";
		list = dao.list(User.class, sql, new Object[]{userName},null);
		return list;
	}
	
	
	/**
	 * 模糊查询用户
	 * @param userName
	 * @return
	 */
	public List<User> usersLike(String userName) {
		List<User> list = new ArrayList<>();
		Dao dao = new Dao();
		String sql = "select * from t_user where userName like '%"+userName+"%'";
		list = dao.list(User.class, sql, null, null);
		return list;
	}
	
	public User load(int id) {
		Dao dao = new Dao();
		User user = new User();
		String sql = "select * from t_user where id=?";
		user = (User) dao.list(User.class, sql, new Object[]{id}, null).get(0);
		return user;
	}
	
	public boolean save(User user) {
		return new Dao().save(user);
	}
	
	public boolean update(User user) {
		return new Dao().update(user);
	}
	
	public boolean delete(int id) {
		return new Dao().delete(User.class, id);
	}
	
	
}
