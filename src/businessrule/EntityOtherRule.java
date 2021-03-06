package businessrule;


public class EntityOtherRule extends BusinessRule {
	public String output;

	public EntityOtherRule(int ruleid, String authorid, String type, String operator, String first, String last, String errorCode, String firstValue, String beforeAfter) {
		super(ruleid, authorid, type, operator, first, last);
		setFirstValue(firstValue);
		generateOtherRule(ruleid, first, errorCode, beforeAfter);
	}

	private void generateOtherRule(int ruleid, String first, String errorCode, String beforeAfter){
		String attrTable1 = first.split("\\.")[1];
		String tablename_attr1 = first.split("\\.")[0];
		String triggernameTable1 = type+ruleid;
		this.output = toString(triggernameTable1, attrTable1, tablename_attr1, errorCode, beforeAfter);
	}

	private String toString(String triggername, String attrTable1, String tablename_attr1, String errorCode, String beforeAfter) {
		String generatedDeclare = "Create or replace trigger tosad_2016_2d_team5_target."+triggername+
				"\n"+beforeAfter+" insert or update on "+tablename_attr1+
				"\nfor each row"+
				"\nDECLARE \n"+
				"l_passed boolean := true;\n"+
				"l_aantal pls_integer;\n";
		String generateBegin = "BEGIN\n"+
				"SELECT count(*) into l_aantal\n"+
				"from "+tablename_attr1+
				"\nwhere "+tablename_attr1+"_"+attrTable1+" = p_"+tablename_attr1+"_row.new_"+tablename_attr1+"_id;\n"+
				"l_passed := "+attrTable1+" "+getOperator(operator)+" "+firstValue+";\n"+
				"if not l_passed then\n"+
				"raise_application_error (-20800,'"+errorCode+"');\n"+
				"end if;\n"+
				"end;\n"+
				"/";
		return generatedDeclare + generateBegin;
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
