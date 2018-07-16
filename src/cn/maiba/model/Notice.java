package cn.maiba.model;

public class Notice {

	public static String TABLE_NAME = "t_notice";
	
	private String content;
	private String author;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(String content, String author) {
		super();
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
