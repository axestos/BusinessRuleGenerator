package businessrule;


public class InterEntityCompareRule extends BusinessRule {

	public InterEntityCompareRule(int ruleid, String authorid, String type, String operator, String first, String last, boolean interEntityModifiable, String errorCode) {
		super(ruleid, authorid, type, operator, first, last);
		generateInterEntityCompareRule(ruleid, authorid, type, operator, first, last, interEntityModifiable, errorCode);
	}
	
	
	public void generateInterEntityCompareRule(int ruleid, String authorid, String type, String operator, String first, String last, boolean interEntityModifiable, String errorCode){
		
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

