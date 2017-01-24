package dao;

import businessrule.BusinessRule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

	public List<BusinessRule> selectFeedback(String query) {
		List<BusinessRule> BusinessRules = new ArrayList<BusinessRule>();

		try (Connection con = super.getConnection()) {

			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int ruleid = dbResultSet.getInt("RULEID");
				String authorid = dbResultSet.getString("AUTHORID");
				String type = dbResultSet.getString("TYPE");
				String operator = dbResultSet.getString("OPERATOR");
				String compare = dbResultSet.getString("COMPAREWITH");
				String first = dbResultSet.getString("FIRSTVALUE");
				String last = dbResultSet.getString("LASTVALUE");

				BusinessRule BR = new BusinessRule(ruleid, authorid, type, operator, compare, first, last);

				BusinessRules.add(BR);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BusinessRules;
	}
}