package businessrule;

public class Main {

	public static void main(String[] args) {
//		new AttributeRangeRule(100, "Kevin", "Attribute", "NotBetween", "School.klaslokaal", "10", "150");
//		new AttributeCompareRule(101, "Victor", "Attribute", "LessThan", "Voetbal.kleedkamer", "150");
//		new AttributeListRule(102, "Quinten",  "Attribute", "In", "Order.status", "'shipped','in warehouse','deliverd','waiting on invoice'");
//		new AttributeOtherRule(103, "Berend", "Attribute", "Between", "Voetbal.veld", "2", "4", "1,1");
//		new TupleCompareRule(104, "Roelant", "Tuple", "GreaterThan", "Voetbal.team", "Voetbal.elftal");
		new InterEntityCompareRule(105,"Dennis", "InterEntity", "GreaterThan", "School.klaslokaal", "Klas.leerlingen", true, "Mag niet");
	}
}