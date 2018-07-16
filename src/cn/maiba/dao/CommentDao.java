package cn.maiba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.maiba.model.Article;
import cn.maiba.model.ArticleUserComm;
import cn.maiba.model.Comment;
import cn.maiba.model.PageBean;
import cn.maiba.model.User;

public class CommentDao {

	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	public boolean save(Comment comment) {
		return new Dao().save(comment);
	}
	
	/**
	 * 根据id删除评论
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		return new Dao().delete(Comment.class, id);
	}
	
	/**
	 * 根据帖子id查询评论
	 * @param articleId
	 * @return
	 */
	public List<ArticleUserComm> list(String articleId) {
		String sql = "select * from t_comment,t_article as c,t_user as u where articleId=c.id and "
				+ "t_comment.userId=u.id and t_comment.articleId=?";
		List<ArticleUserComm> list = commList(sql, new Object[]{articleId});
		return list;
	}
	
	
	
	/**
	 * 根据sql语句查询
	 * @param sql
	 * @param 查询参数
	 * @return
	 */
	public List<ArticleUserComm> commList(String sql, Object[] param) {
		Dao dao = new Dao();
		List<ArticleUserComm> list = new ArrayList<>();
		Connection conn = dao.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			if(param != null) {
				for(int i=0; i<param.length; i++) {
					pst.setObject(i+1, param[i]);
				}
			}
			conn.commit();
			rs = pst.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					Comment comment = new Comment();
					comment.setId(rs.getInt(1));
					comment.setContent(rs.getString(2));
					comment.setReplayTime(rs.getDate(3));
					comment.setAticleId(rs.getInt(4));
					comment.setUserId(rs.getInt(5));
					
					Article article = new Article();
					article.setId(rs.getInt(6));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					article.setCreateTime(rs.getDate("createTime"));
					article.setHitNum(rs.getInt("hitNum"));
					article.setRemarkNum(rs.getInt("remarkNum"));
					article.setIsComment(rs.getInt("isComment"));
					article.setIsVisiable(rs.getInt("isVisiable"));
					article.setType(rs.getString("type"));
					article.setUserId(rs.getInt("userId"));
					User user = new User();
					user.setId(rs.getInt(15));
					user.setUserName(rs.getString("userName"));
					user.setAge(rs.getInt("age"));
					user.setNickName(rs.getString("nickName"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setIsAdmin(rs.getBoolean("isAdmin"));
					user.setExpe(rs.getInt("expe"));
					
					list.add(new ArticleUserComm(comment, article, user));
				}
			}
			dao.close(pst, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				dao.getConn().rollback();
				return null;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
	}
}
