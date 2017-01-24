package businessrule;


public class TupleCompareRule extends BusinessRule {

	public TupleCompareRule(int ruleid, String authorid, String type, String operator, String first, String last) {
		super(ruleid, authorid, type, operator, first, last);
		generateTupleCompareRule(ruleid, authorid, type, operator, first, last);
	}

	private void generateTupleCompareRule(int ruleid, String authorid, String type, String operator, String first, String last){
		String [] tableAttribute = first.split("\\.");
		String [] tableAttribute2 = last.split("\\.");
		String tablename = tableAttribute[0];
		String attribute = tableAttribute[1];
		String attribute2 = tableAttribute2[1];
		String constrainname = "constraint"+ruleid;
		String constraintstatement = attribute+" "+getOperator(operator)+" "+attribute2;
		System.out.println(toString(tablename, constrainname, constraintstatement));
	}
	public String toString(String tablename, String constrainname, String constraintstatement){
		String string = "Alter table " + tablename +" add constraint " + constrainname + " check("+constraintstatement+");";
		return string;
	}
	
	public String getOperator(String operator){
		if (operator.equals("NotEquals")){
			operator= "<>";
		}
		if (operator.equals("Equals")){
			operator= "=";
		}
		if (operator.equals("LessThan")){
			operator = "<";
		}
		if (operator.equals("GreaterThan")){
			operator = ">";
		}
		if (operator.equals("LessOrEqualTo")){
			operator = "<=";
		}
		if (operator.equals("GreaterOrEqualTo")){
			operator = ">=";
		}
		return operator;
	}
}
