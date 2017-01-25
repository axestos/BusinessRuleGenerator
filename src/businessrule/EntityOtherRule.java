package businessrule;


public class EntityOtherRule extends BusinessRule {

	public EntityOtherRule(int ruleid, String authorid, String type, String operator, String first, String last, String errorCode) {
		super(ruleid, authorid, type, operator, first, last);
		// TODO Auto-generated constructor stub
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
