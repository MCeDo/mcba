package cn.maiba.model;

public class TypeUser {

	private Type type;
	private User user;
	
	public TypeUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeUser(Type type, User user) {
		super();
		this.type = type;
		this.user = user;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
