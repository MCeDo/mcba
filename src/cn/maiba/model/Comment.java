package cn.maiba.model;

import java.util.Date;

public class Comment {

	public static String TABLE_NAME = "t_comment";
	
	private int id;
	private String content;
	private Date replayTime;
	
	private int articleId;
	private int userId;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String content, int articleId, int userId) {
		super();
		this.content = content;
		this.articleId = articleId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplayTime() {
		return replayTime;
	}

	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setAticleId(int aticleId) {
		this.articleId = aticleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
