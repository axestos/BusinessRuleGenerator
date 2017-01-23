package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
	public static final String ORACLE_JDBC_CONN = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
	public static final String ORACLE_JDBC_USER = "stud1681260";// Database name zonder CAPS
	public static final String ORACLE_JDBC_PASSWORD = "stud1681260";// Database name zonder CAPS

	protected final Connection getConnection() {
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				System.out.println("-----Driver found-----");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JAR file not found.");
			e.printStackTrace();
		}
		Connection con = null;
		try {
			System.out.println("Trying to establish connection");
			con = DriverManager.getConnection(ORACLE_JDBC_CONN, ORACLE_JDBC_USER, ORACLE_JDBC_PASSWORD);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return con;

	}

}
