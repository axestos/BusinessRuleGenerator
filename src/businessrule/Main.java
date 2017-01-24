package businessrule;

public class Main {

	public static void main(String[] args) {
		new AttributeRangeRule(100, "Kevin", "Attribute", "NotBetween", "School.klaslokaal", "10", "150");
		new AttributeCompareRule(101, "Victor", "Attribute", "LessThan", "Voetbal.kleedkamer", "150");
	}
	
}
