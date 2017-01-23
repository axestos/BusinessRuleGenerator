package listener;

import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
//Nog een main van maken, moet natuurlijk runable zijn
public class DBChangeNotification {
	public static final String ORACLE_JDBC_CONN = "jdbc:oracle:thin:@//ondora02.hu.nl:8080/cursus02.hu.nl";
	public static final String ORACLE_JDBC_USER = "";// Database name zonder CAPS
	public static final String ORACLE_JDBC_PASSWORD = "";// Database name zonder CAPS

	OracleConnection connect() throws SQLException {
		OracleDriver dr = new OracleDriver();
		Properties prop = new Properties();
		prop.setProperty("user", DBChangeNotification.ORACLE_JDBC_USER);
		prop.setProperty("password", DBChangeNotification.ORACLE_JDBC_PASSWORD);
		return (OracleConnection) dr.connect(DBChangeNotification.ORACLE_JDBC_CONN, prop);
	}

	public void run() throws SQLException {
		OracleConnection con = connect();
		Properties prop = new Properties();
		prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
		prop.setProperty(OracleConnection.DCN_QUERY_CHANGE_NOTIFICATION, "true");
		DatabaseChangeRegistration dcr = con.registerDatabaseChangeNotification(prop);
		// adds the listener
		try {
			DCNListener list = new DCNListener(this);
			dcr.addListener(list);

		} catch (SQLException ex) {
			if (con != null)
				con.unregisterDatabaseChangeNotification(dcr);
			throw ex;
		}

	}

}
