package cn.maiba.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.maiba.model.Article;
import cn.maiba.model.ArticleUserComm;
import cn.maiba.model.PageBean;
import cn.maiba.model.User;

public class ArticleDao {

	/**
	 * 查询所有文章，page不为空时按分页查询
	 * @param page
	 * @return
	 */
	public List<ArticleUserComm> list(PageBean page) {
		String sql = "SELECT * FROM t_article LEFT JOIN t_user ON userId=t_user.id ORDER BY createTime DESC";
		if(page!=null) {
			sql += " limit " + page.getStart() +"," + page.getSize();
		}
		return this.commonList(sql, null);
	}
	
	/**
	 * 根据类型查询帖子
	 * @param type
	 * @param page
	 * @return
	 */
	public List<ArticleUserComm> listByType(String type, PageBean page) {
		String sql = "select * from t_article as a, t_user as u where a.userId=u.id and a.type=?"
				+ " order by createTime desc";
		if(page !=null) {
			sql += " limit " + page.getStart() + "," +page.getSize();
		}
		return this.commonList(sql, new Object[]{type});
	}
	
	/**
	 * 模糊查询
	 * @param titleOrContent
	 * @return
	 */
	public List<ArticleUserComm> articleLike(String titleOrContent) {
		String sql = "select * from t_article as a,t_user as u where "
				+ "a.userId=u.id and (a.title like '%"+titleOrContent+"%' "
						+ "or a.content like '%"+titleOrContent+"%')";
		List<ArticleUserComm> list = this.commonList(sql, null);
		return list;
	}
	
	/**
	 * 查询某一帖子
	 * @param id
	 * @return
	 */
	public ArticleUserComm load(String id) {
		String sql = "select * from t_article left join t_user on userId=t_user.id where t_article.id=?";
		return (ArticleUserComm) this.commonList(sql, new Object[]{id}).get(0);
	}
	
	public Article listById(String id) {
		Dao dao = new Dao();
		String sql = "select * from t_article where id=?";
		return (Article) dao.list(Article.class, sql, new Object[]{id}, null).get(0);
	}
	
	/*
	 * 查询userId的帖子
	 */
	public List<ArticleUserComm> listByUserId(String userId, PageBean page) {
		String sql = "SELECT * FROM t_article LEFT JOIN t_user ON userId=t_user.id";
		if(userId != null) {
			sql += " WHERE t_user.id=?";
		}
		sql += " order by createTime desc";
		if(page!=null) {
			sql += " limit " + page.getStart() +"," + page.getSize();
		}
		return this.commonList(sql, new Object[]{userId});
	}
	
	/**
	 * 查询userId评论过的帖子
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<ArticleUserComm> listByComm(String userId, PageBean page) {
		String sql = "SELECT * FROM t_article as a,t_comment as c,t_user as u where "
				+ "c.userId=u.id and c.articleId=a.id";
		if(userId != null) {
			sql += " and u.id=? group by a.id";
		}
		if(page!=null) {
			sql += " limit " + page.getStart() +"," + page.getSize();
		}
		
		return this.commonList(sql, new Object[]{userId});
	}
	
	
	public boolean save(Article article) {
		return new Dao().save(article);
	}
	
	public boolean update(Article article) {
		return new Dao().update(article);
	}
	
	public boolean delete(String id) {
		return new Dao().delete(Article.class, id);
	}
	
	/**
	 * 查询记录条数
	 * @param 查询条件
	 * @return
	 */
	public int count(Object[] param) {
		return new Dao().count(Article.class, param);
	}
	
	public int count(int userId){
		String sql = "select count(*) as count from "
				+ "t_article AS a,t_comment AS c,t_user AS u WHERE c.userId=u.id AND c.articleId=a.id"
				+ " and u.id=" + userId;
		return new Dao().count(sql);
	}
	
	public List<ArticleUserComm> commonList(String sql, Object[] param) {
		Dao dao = new Dao();
		List<ArticleUserComm> auc = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			dao.getConn().setAutoCommit(false);
			try {
				pst = dao.getConn().prepareStatement(sql);
				if(param != null) {
					for(int i=0; i<param.length; i++) {
						pst.setObject(i+1, param[i]);
					}
				}
				dao.getConn().commit();
				rs = pst.executeQuery();
				if(rs != null) {
					while(rs.next()) {
						Article article = new Article();
						article.setId(rs.getInt(1));
						article.setTitle(rs.getString(2));
						article.setContent(rs.getString(3));
						article.setCreateTime(rs.getDate(4));
						article.setHitNum(rs.getInt(5));
						article.setRemarkNum(rs.getInt(6));
						article.setUserId(rs.getInt(7));
						article.setIsComment(rs.getInt("isComment"));
						article.setIsVisiable(rs.getInt("isVisiable"));
						article.setType(rs.getString("type"));
						article.setIsTop(rs.getInt("isTop"));
						User user = new User();
						user.setId(rs.getInt(12));
						user.setUserName(rs.getString("userName"));
						user.setAge(rs.getInt("age"));
						user.setNickName(rs.getString("nickName"));
						user.setPassword(rs.getString("password"));
						user.setEmail(rs.getString("email"));
						user.setIsAdmin(rs.getBoolean("isAdmin"));
						user.setExpe(rs.getInt("expe"));
						user.setStarLevel(rs.getString("starLevel"));
						
						auc.add(new ArticleUserComm(article, user));
					}
				}
				return auc;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			try {
				dao.getConn().rollback();
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}finally {
			dao.close(pst, rs);
		}
	}

}
