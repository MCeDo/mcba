package cn.maiba.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {

	private static Connection conn = null;

	
	public static Connection getConn() {
		if(conn == null) {
			try {
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	private DbUtil() {
	}

	private static void init() throws Exception {
		Properties p = new Properties();
		p.load(new FileInputStream("E:\\Java\\eclipse\\worksplace\\JavaWeb\\src\\jdbc.properties"));
		String driverName = p.getProperty("driverName");
		String url = p.getProperty("url");
		String userName = p.getProperty("userName");
		String password = p.getProperty("password");
		//加载驱动程序
		Class.forName(driverName);
		//连接数据库
		conn = DriverManager.getConnection(url, userName, password);
	}
}
