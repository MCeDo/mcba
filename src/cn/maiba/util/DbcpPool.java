package cn.maiba.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbcpPool {

	private static BasicDataSource dataSource = null;
	
	public static void init() {
		try {
			if(dataSource != null) {
				dataSource.close();
			}
			dataSource = null;
			
//			Properties p = new Properties();
//			p.load(new FileReader("E:\\Java\\eclipse\\worksplace\\JavaWeb\\src\\jdbc.properties"));
			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/db_maiba");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized Connection getConn() {
		if(dataSource == null) {
			init();
		}
		Connection con = null;
		if(dataSource !=null) {
			try {
				con = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
