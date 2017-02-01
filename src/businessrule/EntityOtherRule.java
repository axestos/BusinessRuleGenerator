package businessrule;


public class EntityOtherRule extends BusinessRule {

	public EntityOtherRule(int ruleid, String authorid, String type, String operator, String first, String last, String errorCode, String firstValue, String beforeAfter) {
		super(ruleid, authorid, type, operator, first, last);
		setFirstValue(firstValue);
		generateOtherRule(ruleid, first, last, errorCode, beforeAfter);
	}

	private void generateOtherRule(int ruleid, String first, String last, String errorCode, String beforeAfter){
		String attrTable1 = first.split("\\.")[1];
		String tablename_attr1 = first.split("\\.")[0];
		String tablename_attr2 = last.split("\\.")[0];
		String triggernameTable1 = tablename_attr1+type+ruleid;
		setGeneratedCode(toString(triggernameTable1, attrTable1, tablename_attr2, tablename_attr1, errorCode, beforeAfter));
	}

	private String toString(String triggername, String attrTable1, String tablename_attr2, String tablename_attr1, String errorCode, String beforeAfter) {
		String generatedDeclare = "Create or replace trigger "+triggername+
				"\n"+beforeAfter+" insert or update on "+tablename_attr1+
				"\nfor each row"+
				"\nDECLARE \n"+
				"l_passed boolean := true;\n"+
				""+tablename_attr1+"\n";
		String generateBegin = "BEGIN\n"+
				"SELECT "+tablename_attr1+"."+attrTable1+"\n"+
				"from "+tablename_attr1+
				"\nwhere "+tablename_attr1+"_id = p_"+tablename_attr1+"_row.new_"+tablename_attr2+"_id;\n"+
				"l_passed := "+attrTable1+" "+getOperator(operator)+" "+firstValue+"\n"+
				"if not l_passed then\n"+
				"raise_application_error (-20800,'"+errorCode+"');\n"+
				"end if;\n"+
				"end;\n"+
				"/";
		return generatedDeclare + generateBegin;
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
