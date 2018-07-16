package cn.maiba.test;

import javax.servlet.http.HttpServletRequest;

public class UserService extends Father{

	private String name;
	private HttpServletRequest request;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void execute() {
		System.out.println(name+", ");
	}
	
}
