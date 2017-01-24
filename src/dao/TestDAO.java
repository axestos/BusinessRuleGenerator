package dao;

import java.util.List;

import businessrule.BusinessRule;

public class TestDAO extends BaseDAO {

	public static void main(String[] args) {
		SpelerDAO speler = new SpelerDAO();
		List<BusinessRule> rules = speler.selectFeedback("Select * from BUSINESSRULES");
		System.out.println(rules);
	}

}
