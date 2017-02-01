package businessrule;

public class ModifyRule extends BusinessRule {

	public ModifyRule(int ruleid, String authorid, String type, String operator, String first, String last, String errorCode, String firstValue, String beforeAfter) {
		super(ruleid, authorid, type, operator, first, last);
		setFirstValue(firstValue);
		generateModifyRule(ruleid, type, first, last, errorCode, beforeAfter, firstValue);
	}

	public void generateModifyRule(int ruleid, String type, String first, String last, String errorCode, String beforeAfter, String firstValue){
		String attrTable1 = first.split("\\.")[1];
		String attrTable1_value;
		if(isInteger(firstValue)){
			attrTable1_value = firstValue;
		}
		else {
			attrTable1_value = "'"+firstValue+"'";
		}
		String tablename_attr1 = first.split("\\.")[0];
		String tablename_attr2 = last.split("\\.")[0];
		String cursorID_table1 = "cursor"+tablename_attr2+ruleid;
		String triggernameTable1 = tablename_attr1+type+ruleid;
		setGeneratedCode(toString(triggernameTable1, attrTable1, cursorID_table1, tablename_attr2, tablename_attr1, errorCode, beforeAfter, attrTable1_value));
	}

	private String toString(String triggername, String attrTable1, String cursorID_table1, String tablename_attr2, String tablename_attr1, String errorCode, String beforeAfter, String attrTable1_value) {
		String generatedDeclare = "Create or replace trigger "+triggername+
				"\n"+beforeAfter+" insert or update on "+tablename_attr1+
				"\nfor each row"+
				"\nDECLARE \n"+
				"l_passed boolean := true;\n"+
				"cursor " + cursorID_table1+" is\n"+
				"SELECT "+tablename_attr1+"."+attrTable1+"\n"+
				"from "+tablename_attr1+
				"\nwhere "+tablename_attr1+"_id = p_"+tablename_attr2+"_row.new_"+tablename_attr1+"_id;\n"+
				attrTable1+" "+first+"%type;\n";
		String generateBegin = "BEGIN\n"+
				"if p_"+tablename_attr2+"_row.brg_oper = 'INS' then\n"+
				"open "+cursorID_table1+";\n"+
				"fetch "+cursorID_table1+" into l_"+attrTable1+";\n"+
				"close "+cursorID_table1+";\n"+
				"l_passed := "+attrTable1+" "+getOperator(operator)+" "+attrTable1_value+"\n"+
				"if not l_passed then\n"+
				"raise_application_error (-20800,'"+errorCode+"');\n"+
				"end if;\n"+
				"end;\n"+
				"/";
        return generatedDeclare + generateBegin;
	}


	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
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
