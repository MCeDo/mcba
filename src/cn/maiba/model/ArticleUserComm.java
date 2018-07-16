package cn.maiba.model;

public class ArticleUserComm {

	private Article article;
	private User user;
	private Comment comment;
	
	public ArticleUserComm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleUserComm(Article article, User user) {
		super();
		this.article = article;
		this.user = user;
	}

	public ArticleUserComm(Comment comment, Article article, User user) {
		// TODO Auto-generated constructor stub
		this.comment = comment;
		this.article = article;
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
}
