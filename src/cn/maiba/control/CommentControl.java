package cn.maiba.control;

import javax.servlet.http.HttpServletRequest;

import cn.maiba.dao.CommentDao;
import cn.maiba.dao.ExpeDao;
import cn.maiba.model.Comment;
import cn.maiba.model.User;

public class CommentControl {

	private HttpServletRequest request;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String add() {
		String articleId = request.getParameter("articleId");
		String content = request.getParameter("commContent");
		
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("failure_message", "请先登录！");
			return "result/failure.jsp";
		}
		Comment comment = new Comment(content, Integer.parseInt(articleId), user.getId());
		CommentDao dao = new CommentDao();
		//用户评论数量+1
		ExpeDao.addComment(user.getId()+"");
		
		String result = "";
		if(dao.save(comment)) {
			result = "/logon/Article_detail.do?articleId="+articleId;
		}
		return result; 
	}
	
}
