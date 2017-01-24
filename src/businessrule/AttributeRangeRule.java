package businessrule;

public class AttributeRangeRule extends BusinessRule {

	public AttributeRangeRule(int ruleid, String authorid, String type, String operator, String compare,String rangeAttribute, String first, String last) {
		super(ruleid, authorid, type, operator, compare, first, last);
		generateAttributeRangeRule(ruleid, authorid, type, operator, rangeAttribute, first, last);
	}

	private void generateAttributeRangeRule(int ruleid, String authorid, String type, String operator, String rangeAttribute, String first, String last){
		String [] tableAttribute = rangeAttribute.split("\\.");
		String tablename = tableAttribute[0];
		String attribute = tableAttribute[1];
		String constrainname = "constraint"+ruleid;
		String constraintstatement = "("+attribute+" "+getOperator(operator)+" "+first+" and "+last+");";
		System.out.println(toString(tablename, constrainname, constraintstatement));
	}
	public String toString(String tablename, String constrainname, String constraintstatement){
		String string = "Alter table " + tablename +" add constraint " + constrainname + " check"+constraintstatement;
		return string;
	}
	
	public String getOperator(String operator){
		if (operator.equals("NotBetween")){
			operator = "not between";
		}
		else if (operator.equals("Between")){
			operator = "between";
		}
		return operator;
	}
}
