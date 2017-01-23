package dao;

public class TestDAO extends BaseDAO {

	public static void main(String[] args) {
		SpelerDAO speler = new SpelerDAO();
		speler.insertFeedback("Quinten", "atr", "GreaterThan", "Literal Value", "Costumer.Age", "5");
	}

}
