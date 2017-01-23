package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SpelerDAO extends BaseDAO {

	public void insertFeedback(String authorid, String type, String operator, String compare, String first, String last) {
		try (Connection con = super.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO BUSINESSRULES(AUTHORID,TYPE,OPERATOR,COMPAREWITH,FIRSTVALUE,LASTVALUE) VALUES(?,?,?,?,?,?)");
			ps.setString(1, authorid);
			ps.setString(2, type);
			ps.setString(3, operator);
			ps.setString(4, compare);
			ps.setString(5, first);
			ps.setString(6, last);
			ps.executeUpdate();
			ps.close();
			System.out.println("Inserted into db");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
