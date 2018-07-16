package cn.maiba.model;

import java.util.Date;

import org.eclipse.jdt.internal.compiler.env.ISourceMethod;

public class Article {

	public  static final String TABLE_NAME = "t_article";
	
	private int id;
	
	private int userId;
	
	private String title;
	
	private String content;
	
	private Date createTime;
	
	private int hitNum;
	
	private int remarkNum;
	
	private int isVisiable;	//游客是否可见,0表示不可见
	
	private int isComment;	//是否可以评论
	
	private int isTop;		//是否置顶
	
	private String type;	//版块分类
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Article(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	public Article(String title, String content, int userId, int isVisiable, int isComment) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.isVisiable = isVisiable;
		this.isComment = isComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getHitNum() {
		return hitNum;
	}

	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}

	public int getRemarkNum() {
		return remarkNum;
	}

	public void setRemarkNum(int remarkNum) {
		this.remarkNum = remarkNum;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsVisiable() {
		return isVisiable;
	}

	public void setIsVisiable(int isVisiable) {
		this.isVisiable = isVisiable;
	}

	public int getIsComment() {
		return isComment;
	}

	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
