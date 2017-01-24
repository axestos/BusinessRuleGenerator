package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import businessrule.BusinessRule;


public class SpelerDAO extends BaseDAO {

	public List<BusinessRule> selectFeedback(String query) {
		List<BusinessRule> BusinessRules = new ArrayList<BusinessRule>();

		try (Connection con = super.getConnection()) {

			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int authorid = dbResultSet.getInt("AUTHORID");
				String type = dbResultSet.getString("TYPE");
				String operator = dbResultSet.getString("OPERATOR");
				String compare = dbResultSet.getString("COMPAREWITH");
				String first = dbResultSet.getString("FIRSTVALUE");
				String last = dbResultSet.getString("LASTVALUE");

				BusinessRule BR = new BusinessRule(authorid, type, operator, compare, first, last);

				BusinessRules.add(BR);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BusinessRules;
	}

}
