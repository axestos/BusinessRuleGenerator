package businessrule;

public class AttributeCompareRule extends BusinessRule {
	public String output;

	public AttributeCompareRule(int ruleid, String authorid, String type, String operator, String first, String last){
		super(ruleid, authorid, type, operator, first, last);
		generateAttributeCompareRule(ruleid, operator, first, last);
	}

	private void generateAttributeCompareRule(int ruleid, String operator, String first, String last){
		String [] tableAttribute = first.split("\\.");
		String tablename = tableAttribute[0];
		String attribute = tableAttribute[1];
		String constrainname = "constraint"+ruleid;
		String constraintstatement = attribute+" "+getOperator(operator)+" "+last;
		this.output = toString(tablename, constrainname, constraintstatement);
	}
	public String toString(String tablename, String constrainname, String constraintstatement){
		String string = "Alter table tosad_2016_2d_team5_target." + tablename +" add constraint " + constrainname + " check("+constraintstatement+");";
		return string;
	}

	public String getOutput(){
		return output;
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
