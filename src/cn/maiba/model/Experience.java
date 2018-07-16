package cn.maiba.model;

/**
 * @author cedo
 *
 */
public class Experience {
	
	public static final String TABLE_NAME = "t_experience";
	
	private int id;
	
	private int userId;
	
	private int logonCount;		//登录次数
								
	private int logonTimes;		//登录时长
	
	private int publishNums;	//发布帖子数量
	
	private int commentNums;	//评论帖子数量
	
	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLogonCount() {
		return logonCount;
	}

	public void setLogonCount(int logonCount) {
		this.logonCount = logonCount;
	}

	public int getLogonTimes() {
		return logonTimes;
	}

	public void setLogonTimes(int logonTimes) {
		this.logonTimes = logonTimes;
	}

	public int getPublishNums() {
		return publishNums;
	}

	public void setPublishNums(int publishNums) {
		this.publishNums = publishNums;
	}

	public int getCommentNums() {
		return commentNums;
	}

	public void setCommentNums(int commentNums) {
		this.commentNums = commentNums;
	}

}
