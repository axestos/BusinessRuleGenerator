package dao;

import java.util.List;

import businessrule.BusinessRule;

public class TestDAO extends BaseDAO {

	public static void main(String[] args) {
		SpelerDAO speler = new SpelerDAO();
		speler.selectFeedback("Select * from BUSINESSRULES");
	}

}
