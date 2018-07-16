package cn.maiba.model;

public class Type {

	public static final String TABLE_NAME = "t_type";
	
	private int id;
	
	private String type;
	
	private int userId;

	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Type(String type) {
		super();
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
