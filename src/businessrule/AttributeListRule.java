package businessrule;

public class AttributeListRule extends BusinessRule {
	public String output;

	public AttributeListRule(int ruleid, String authorid, String type, String operator, String first, String last) {
		super(ruleid, authorid, type, operator, first, last);
		generateAttributeListRule(ruleid, operator, first, last);
	}

	private void generateAttributeListRule(int ruleid, String operator, String first, String last){
		String [] tableAttribute = first.split("\\.");
		String tablename = tableAttribute[0];
		String attribute = tableAttribute[1];
		String constrainname = "constraint"+ruleid;
		String constraintstatement = attribute+" "+getOperator(operator)+" ("+last+")";
		setGeneratedCode(toString(tablename, constrainname, constraintstatement));
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
		if (operator.equals("In")){
			operator= "in";
		}
		if (operator.equals("NotIn")){
			operator = "not in";
		}
		return operator;
	}
}
