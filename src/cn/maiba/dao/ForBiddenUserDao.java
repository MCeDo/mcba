package cn.maiba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.maiba.model.ForbiddenUser;

public class ForBiddenUserDao {

	public static boolean addForbiddenUser(String userName) {
		return new Dao().save(new ForbiddenUser(userName));
	}
	
	public List<ForbiddenUser> list() {
		return new Dao().list(ForbiddenUser.class, null);
	}
	
	public boolean delete(String userName) {
		String sql = "delete from t_forbiddenuser where userName=?";
		Connection con = new Dao().getConn();
		PreparedStatement pst = null;
		try {
			con.setAutoCommit(false);
			pst  = con.prepareStatement(sql);
			pst.setString(1, userName);
			pst.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				pst.close();
//				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ForbiddenUser load(String userName) {
		ForbiddenUser fUser = null;
		String sql = "select * from t_forbiddenUser where userName=?";
		Connection con = new Dao().getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			con.commit();
			rs = pst.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					fUser = new ForbiddenUser();
					fUser.setForbiddenTime(rs.getDate("forbiddenTime"));
					fUser.setUserName(rs.getString("userName"));
				}
				return fUser;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
