package listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;

//Nog een main van maken, moet natuurlijk runable zijn
public class DBChangeNotification {
	public static final String ORACLE_JDBC_CONN = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
	public static final String ORACLE_JDBC_USER = "stud1681260";
	public static final String ORACLE_JDBC_PASSWORD = "stud1681260";

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
			Statement stmt = con.createStatement();
			((OracleStatement) stmt).setDatabaseChangeRegistration(dcr);
			ResultSet rs = stmt.executeQuery("select * from speler");
			while (rs.next()){}
			String[] tableNames = dcr.getTables();
			for (int i=0; i<tableNames.length; i++)
				System.out.println(tableNames[i] + "is part of the registration.");
			rs.close();
			stmt.close();
		} 
		
		catch (SQLException ex) {
			if (con != null)
				con.unregisterDatabaseChangeNotification(dcr);
			throw ex;
		} 
		
		finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		synchronized ( this ){
			
		}

	}

	 public static void main(String[] args)
	  {
//	    if(args.length < 1)
//	    {
//	      System.out.println("Error: You need to provide the URL in the first argument.");
//	      System.out.println("  For example: > java -classpath .:ojdbc6.jar DBChangeNotification \"jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=yourhost.yourdomain.com)(PORT=5221))(CONNECT_DATA=(SERVICE_NAME=orcl)))\"");
//	 
//	      System.exit(1);
//	    }
//	    URL = argv[0];
	    DBChangeNotification demo = new DBChangeNotification();
	    try
	    {
	      demo.run();
	    }
	    catch(SQLException mainSQLException )
	    {
	      mainSQLException.printStackTrace();
	    }
	  }
	
}
