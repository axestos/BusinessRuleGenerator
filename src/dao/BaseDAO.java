package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
	public static final String ORACLE_JDBC_CONN = "jdbc:oracle:thin:@//ondora02.hu.nl:8080/cursus02.hu.nl";
	public static final String ORACLE_JDBC_USER = "";// Database name zonder CAPS
	public static final String ORACLE_JDBC_PASSWORD = "";// Database name zonder CAPS

	protected final Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JAR file not found.");
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(ORACLE_JDBC_CONN, ORACLE_JDBC_USER, ORACLE_JDBC_PASSWORD);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return con;

	}

}
