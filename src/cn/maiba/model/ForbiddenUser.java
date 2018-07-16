package cn.maiba.model;

import java.util.Date;

public class ForbiddenUser {

	public static String TABLE_NAME = "t_forbiddenuser";
	
	private String userName;
	
	private Date forbiddenTime;

	public ForbiddenUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForbiddenUser(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getForbiddenTime() {
		return forbiddenTime;
	}

	public void setForbiddenTime(Date forbiddenTime) {
		this.forbiddenTime = forbiddenTime;
	}

}
