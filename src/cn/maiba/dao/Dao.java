package cn.maiba.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.maiba.model.PageBean;
import cn.maiba.util.DbUtil;
import cn.maiba.util.DbcpPool;


@SuppressWarnings("all")
public class Dao {

	private ThreadLocal<Connection> conn = new ThreadLocal<>();

	public Dao() {
		// TODO Auto-generated constructor stub
		try {
			if(conn.get() != null) {
				conn.get().close();
			}
			conn.set(DbcpPool.getConn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public Connection getConn() {
		return conn.get();
	}
	
	public void close(PreparedStatement pst, ResultSet rs) {
		
		try {
			if(rs != null) {
				rs.close();
			}
			if(pst != null) {
				pst.close();
			}
			if(conn.get()!=null) {
				conn.get().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(Statement st, ResultSet rs) {
			
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				if(conn.get()!=null) {
					conn.get().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	/**
	 * 查询item对应表中的所有数据
	 * @param 实体类
	 * @return 对应ITem的数据集
	 */
	public <T> List<T> list(Class<?> item, PageBean page) {
		List<T> list = new ArrayList<T>();
		try {
			Statement st = conn.get().createStatement();
			String sql = "select * from " + item.getField("TABLE_NAME").get(item);
			if(page!=null) {
				sql += " limit " + page.getStart() +"," + page.getSize();
			}
			ResultSet rs = st.executeQuery(sql);
			if(rs != null) {
				while(rs.next()) {
					list.add((T) createObj(rs, item));
				}
			}
			close(st, rs);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.get().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} 
	}
	/**
	 * 根据给定的条件查询item类对应的表数据
	 * @param 实体类
	 * @param  查询语句，包括占位符？
	 * @param 与占位符对应的参数
	 * @param 分页类
	 * @return
	 */
	public <T> List<T> list(Class<?> item, String sql, Object[] param, PageBean page) {
		List<T> list = new ArrayList<T>();
		try {
			if(page != null) {
				sql += " limit " + page.getStart() +"," + page.getSize();
			}
			PreparedStatement pst = conn.get().prepareStatement(sql);
			if(param != null) {
				for(int i=0; i<param.length; i++) {
					pst.setObject(i+1, param[i]);
				}
			}
			ResultSet rs = pst.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					list.add((T) createObj(rs, item));
				}
			}
			close(pst, rs);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			try {
				conn.get().rollback();
			} catch (SQLException e1) {
				try {
					conn.get().rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	/**
	 * 保存一个实体类
	 * @param obj
	 * @return
	 */
	public <T> boolean save(T obj) {
		Class clazz = obj.getClass();
		try {
			StringBuilder sql = new StringBuilder("insert into "
						+clazz.getField("TABLE_NAME").get(obj.getClass())+"(");
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			//存放占位符?,如：(?,?)
			StringBuilder strValue = new StringBuilder("value (");
			List col = new ArrayList<>();
			
			for(int i=0; i<fields.length; i++) {
				String fieldName = fields[i].getName();
				if("TABLE_NAME".equals(fieldName)||"id".equals(fieldName)) {
					continue;
				}
				//构造getter方法
				String methodName = "get" 
								+ fieldName.replaceFirst(fieldName.substring(0, 1),
										fieldName.substring(0, 1).toUpperCase());
				//存放getter获取的值
				Object value = null;
				for(int j=0; j<methods.length; j++) {
					if(methodName.equals(methods[j].getName())) {
						value = methods[j].invoke(obj);
						break;
					}
				}
				//值不为空，sql插入相应的字段和占位符,并将值放入list中
				if(value != null) {
					col.add(value);
					sql.append(fieldName+",");
					strValue.append("?,");
				}
			}
			//整合为完整插入语句
			sql = sql.replace(sql.length()-1, sql.length(),")");
			sql.append(strValue.replace(strValue.length()-1, strValue.length(), ")").toString());
			PreparedStatement pst = conn.get().prepareStatement(sql.toString());
			for(int i=0; i<col.size(); i++) {
				pst.setObject(i+1, col.get(i));
			}
			pst.executeUpdate();
			close(pst, null);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.get().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public <T> boolean update(T obj) {
		Class clazz = obj.getClass();
		try {
			//sql语句：update TABLE_NAME set col=value where id=?
			StringBuilder sql = new StringBuilder("update "
							+ clazz.getField("TABLE_NAME").get(clazz) + " set ");
			Object id = null;
			List col = new ArrayList<>();
			
			Field[] fields = clazz.getDeclaredFields();
			Method[] methods = clazz.getDeclaredMethods();
			
			for(int i=0; i<fields.length; i++) {
				String fieldName = fields[i].getName();
				if("TABLE_NAME".equals(fieldName)) {
					continue;
				}
				//构造getter方法
				String methodName = "get" 
								+ fieldName.replaceFirst(fieldName.substring(0,1), 
										fieldName.substring(0,1).toUpperCase());
				//存放getter获取的值
				Object value = null;
				for(int j=0; j<methods.length; j++) {
					if(methodName.equals(methods[j].getName())){
						value = methods[j].invoke(obj);
						break;
					}
				}
				
				if("id".equals(fieldName)) {
					id = value;
					if(id == null) return false;
				}else {
					if(i == fields.length-1) {
						sql.append(fieldName+"=?");
					}else {
						sql.append(fieldName+"=?,");
					}
					col.add(value);
				}
			}
			sql.append(" where id=?");
			col.add(id);
			PreparedStatement pst = conn.get().prepareStatement(sql.toString());
			for(int i=0; i<col.size(); i++) {
				pst.setObject(i+1, col.get(i));
			}
			pst.executeUpdate();
			close(pst,null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.get().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
	public boolean delete(Class item, Object id) {
		if(id == null) return false;
		try {
			//delete语句:delete from TABLE_NAME where id=?
			String sql = "delete from "
						+ item.getField("TABLE_NAME").get(item) + " where id=?";
			PreparedStatement pst = conn.get().prepareStatement(sql);
			pst.setObject(1, id);
			pst.executeUpdate();
			pst.close();
			close(pst, null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.get().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return true;
		}
	}

	public int count(String sql) {
		int count = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn.get().setAutoCommit(false);
			pst = conn.get().prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs != null) {
				rs.next();
				count = rs.getInt("count");
			}
			close(pst, rs);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.get().rollback();
				return 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return 0;
			}
		}
		
	}
	
	/**
	 * 查询记录数
	 * @param item
	 * @param param
	 * @return
	 */
	public int count(Class item, Object[] param) {
		try {
			String sql = "select count(*)as count from " 
						+ item.getDeclaredField("TABLE_NAME").get(item);
			if(param!=null) {
				for(int i=0; i<param.length-1; i++) {
					sql += " and " + param[i] + "=" + param[i+1];
				}
				sql = sql.replaceFirst("and", "where");
			}
			Statement st = conn.get().createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			if(rs != null) {
				rs.next();
				count = rs.getInt("count");
			}
			close(st, rs);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public <T> T query(String sql, Class item) {
		T obj = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.get().createStatement();
			rs = st.executeQuery(sql);
			if(rs != null) {
				while(rs.next()) {
					obj = (T) createObj(rs, item);
				}
			}
			close(st, rs);
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private Object createObj(ResultSet rs, Class item) throws Exception {
		// TODO Auto-generated method stub
		//实例化
		Object obj = item.newInstance();
		//获取自定义的所有属性
		Field[] fileds = item.getDeclaredFields();
		//获取自定义的方法
		Method[] methods = item.getDeclaredMethods();
		
		for(int i=0; i<fileds.length; i++) {
			String filedName = fileds[i].getName();
			if("TABLE_NAME".equals(filedName)) {
				continue;
			}
			//构造setter方法
			String methodName = "set" 
						+ filedName.replaceFirst(filedName.substring(0, 1), filedName.substring(0, 1).toUpperCase());
			for(int j=0; j<methods.length; j++) {
				if(methodName.equals(methods[j].getName())) {
					//找到对应的setter方法，注入值
					methods[j].invoke(obj, rs.getObject(filedName));
					break;
				}
			}
		}
		return obj;
	}
}
