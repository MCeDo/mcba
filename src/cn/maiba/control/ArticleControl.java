package cn.maiba.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.maiba.dao.ArticleDao;
import cn.maiba.dao.CommentDao;
import cn.maiba.dao.ExpeDao;
import cn.maiba.dao.TypeDao;
import cn.maiba.model.Article;
import cn.maiba.model.ArticleUserComm;
import cn.maiba.model.PageBean;
import cn.maiba.model.Type;
import cn.maiba.model.TypeUser;
import cn.maiba.model.User;

public class ArticleControl {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ArticleDao articleDao = new ArticleDao();
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String list() {
		PageBean page = (PageBean) request.getAttribute("page");
		String currentPage = request.getParameter("currentPage");
		if(page==null) {
			page = new PageBean();
			page.setCount(articleDao.count(null));
			page.setSize(5);
			page.setCurrentPage(0);
		}
		if(currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		//获取文章集
		List<ArticleUserComm> list = null;
		//如果有搜索信息
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue != null) {
			list = articleDao.articleLike(searchValue);
		}else {
			list = articleDao.list(page);
		}
		List<ArticleUserComm> topList = new ArrayList<>();
		for (ArticleUserComm auc : articleDao.list(null)) {
			if(auc.getArticle().getIsTop()==1){
				topList.add(auc);
			}
		}
		list.removeAll(topList);
		//获取版块集
		List<Type> typeList = new TypeDao().list();
		
		request.setAttribute("typeList", typeList);
		request.setAttribute("articleList", list);
		request.setAttribute("topList", topList);
		request.setAttribute("page", page);
		return "articleList.jsp";
	}
	/*
	 * 搜索
	 */
	public String search() {
		PageBean page = (PageBean) request.getAttribute("page");
		String currentPage = request.getParameter("currentPage");
		if(page==null) {
			page = new PageBean();
			page.setCount(articleDao.count(null));
			page.setSize(5);
			page.setCurrentPage(0);
		}
		if(currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		//获取文章集
		List<ArticleUserComm> list = null;
		//如果有搜索信息
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue != null) {
			list = articleDao.articleLike(searchValue);
		}
		
		request.setAttribute("searchValue", searchValue);
		request.setAttribute("articleList", list);
		request.setAttribute("page", page);
		return "searchArticleList.jsp";
		
	}
	
	/**
	 * 根据类型查询文章集
	 * @return
	 */
	public String listByType() {
		//如果有版块类型信息
		String type = request.getParameter("type");
		PageBean page = (PageBean) request.getAttribute("page");
		String currentPage = request.getParameter("currentPage");
		if(page==null) {
			page = new PageBean();
			page.setCount(articleDao.count(new Object[]{"type", "'"+type+"'"}));
			page.setSize(5);
			page.setCurrentPage(0);
		}
		if(currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		List<ArticleUserComm> list = null;
		if(type != null) {
			list = articleDao.listByType(type, page);
		}
		//获取版块集
		List<TypeUser> typeUserList = new TypeDao().typeUserList();
		
		List<ArticleUserComm> topList = new ArrayList<>();
		for (ArticleUserComm auc : list) {
			if(auc.getArticle().getIsTop()==1){
				topList.add(auc);
			}
		}
		list.removeAll(topList);
		
		request.setAttribute("page", page);
		request.setAttribute("currentType", type);
		request.setAttribute("typeUserList", typeUserList);
		request.setAttribute("articleList", list);
		request.setAttribute("topList", topList);
		return "articleListByType.jsp";
	}
	
	/*
	 * 我发表的帖子
	 */
	public String myList() {
		User user = (User) request.getSession().getAttribute("user");
		String userId = user.getId()+"";
		PageBean page = (PageBean) request.getAttribute("page");
		String currentPage = request.getParameter("currentPage");
		if(page==null) {
			page = new PageBean();
			page.setCount(articleDao.count(new Object[]{"userId",userId}));
			page.setSize(5);
			page.setCurrentPage(0);
		}
		if(currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		
		List<ArticleUserComm> list = articleDao.listByUserId(userId, page);
		request.setAttribute("articleList", list);
		request.setAttribute("page", page);
		return "logon/article/myArticleList.jsp";
	}
	/*
	 * 我评论的帖子
	 */
	public String myComment() {
		User user = (User) request.getSession().getAttribute("user");
		String userId = user.getId()+"";
		PageBean page = (PageBean) request.getAttribute("page");
		String currentPage = request.getParameter("currentPage");
		if(page==null) {
			page = new PageBean();
			page.setCount(articleDao.count(user.getId()));
			page.setSize(5);
			page.setCurrentPage(0);
		}
		if(currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		List<ArticleUserComm> list = articleDao.listByComm(userId, page);
		request.setAttribute("articleList", list);
		request.setAttribute("page", page);
		return "logon/article/myCommentArticle.jsp";
	}
	
	public String add() {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		int isVisiable = Integer.parseInt(request.getParameter("isVisiable"));
		int isComment = Integer.parseInt(request.getParameter("isComment"));

		User user = (User) request.getSession().getAttribute("user");
		Article article = new Article(title, content, user.getId(), isVisiable, isComment);
		article.setType(type);
		//用户发帖数量+1
		ExpeDao.addPublish(user.getId()+"");
		
		if(articleDao.save(article)) {
			request.setAttribute("result_message", "恭喜你，发表成功");
			request.setAttribute("back", "Article_myList.do");
		}else {
			request.setAttribute("result_message", "对不起，发表失败");
		}
		return "logon/article/articleResult.jsp";
	}
	
	public String preAdd() {
		request.setAttribute("typeList", new TypeDao().list());
		return "logon/article/articleNew.jsp";
	}
	
	public String detail() {
		//获取帖子详情
		String articleId = request.getParameter("articleId");
		//增加点击数
		Article a = articleDao.listById(articleId);
		a.setHitNum(a.getHitNum()+1);
		articleDao.update(a);
		ArticleUserComm articleUserComm = articleDao.load(articleId);
		
		//获取评论列表
		CommentDao commentDao = new CommentDao();
		List<ArticleUserComm> commentList = commentDao.list(articleId);

		request.setAttribute("commentList", commentList);
		request.setAttribute("articleUserComm", articleUserComm);
		return "articleDetail.jsp";
	}
	
	public String delete() {
		String articleId = request.getParameter("articleId");
		if(articleDao.delete(articleId)) {
			request.setAttribute("result_message", "删除成功");
			request.setAttribute("back", "Article_myList.do");
		}else {
			request.setAttribute("result_message", "删除失败");
		}
		return "logon/article/articleResult.jsp";
	}
	
	public String preModify() {
		String id = request.getParameter("articleId");
		ArticleUserComm article = articleDao.load(id);
		request.setAttribute("articleUser", article);
		return "logon/article/articleModify.jsp";
	}
	
	public String modify() {
		String articleId = request.getParameter("articleId");
		String title = request.getParameter("m_title");
		String content = request.getParameter("m_content");
		Article article = articleDao.listById(articleId);
		article.setTitle(title);
		article.setContent(content);
		if(articleDao.update(article)) {
			request.setAttribute("result_message", "恭喜你，修改成功");
		}else {
			request.setAttribute("result_message", "对不起，修改失败");
		}
		request.setAttribute("back", "Article_detail.do?articleId="+articleId);
		return "logon/article/articleResult.jsp";
	}
	
	/**
	 * 将帖子置顶/取消置顶
	 * @return
	 */
	public void toTop() {
		String articleId = request.getParameter("articleId");
		ArticleDao articleDao = new ArticleDao();
		Article article = articleDao.listById(articleId);
		if(article.getIsTop() == 0) {
			article.setIsTop(1);
		}else {
			article.setIsTop(0);
		}
		articleDao.update(article);

		try {
			response.sendRedirect("Article_list.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 公告发布
	 */
	
}
