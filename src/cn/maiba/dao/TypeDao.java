package cn.maiba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.maiba.model.Type;
import cn.maiba.model.TypeUser;
import cn.maiba.model.User;

public class TypeDao {

	public List<Type> list() {
		Dao dao = new Dao();
		List<Type> list = dao.list(Type.class, null);
		return list;
	}
	
	/**
	 * 获取type和user的集合
	 * @return
	 */
	public List<TypeUser> typeUserList() {
		String sql = "select t.type,t.userId,u.userName from t_type as t left join t_user as u"
				+ " on t.userId=u.id";
		Dao dao = new Dao();
		List<TypeUser> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = dao.getConn().createStatement();
			rs = st.executeQuery(sql);
			if(rs != null) {
				while(rs.next()) {
					Type type = new Type();
					type.setType(rs.getString("type"));
					type.setUserId(rs.getInt("userId"));
					User u = new User();
					u.setUserName(rs.getString("userName"));
					
					list.add(new TypeUser(type, u));
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dao.close(st, rs);
		}
	}
	
}
