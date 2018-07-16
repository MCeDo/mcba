package cn.maiba.model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import cn.maiba.listener.OnlineUser;

public class User implements HttpSessionBindingListener{

	public static final String TABLE_NAME="t_user";
	private int id;
	private String userName;
	private String password;
	private String nickName;
	private int age;
	private String email;
	
	private boolean isAdmin;
	
	//经验值
	private int expe;
	//级别
	private String starLevel;
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public User(String userName, String password, String nickName, int age, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.age = age;
		this.email = email;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getExpe() {
		return expe;
	}
	public void setExpe(int expe) {
		this.expe = expe;
		if(this.expe < 100) {
			this.starLevel = "青铜";
		}else if(this.expe < 200) {
			this.starLevel = "白银";
		}else if(this.expe < 350) {
			this.starLevel = "黄金";
		}else if(this.expe < 500) {
			this.starLevel = "铂金";
		}else if(this.expe < 1000) {
			this.starLevel = "钻石";
		}
	}
	public String getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		OnlineUser.onlineUsers++;
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		if(OnlineUser.onlineUsers > 0) {
			OnlineUser.onlineUsers--;
		}
	}
	
	
}
