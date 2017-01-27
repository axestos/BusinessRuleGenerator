package businessrule;


public class InterEntityCompareRule extends BusinessRule {

	public InterEntityCompareRule(int ruleid, String authorid, String type, String operator, String first, String last, boolean interEntityModifiable, String errorCode, String beforeAfter) {
		super(ruleid, authorid, type, operator, first, last);
		setInterEntityModifiable(interEntityModifiable);
		generateInterEntityCompareRule(ruleid, authorid, type, operator, first, last, interEntityModifiable, errorCode, beforeAfter);
	}
	
	
	public void generateInterEntityCompareRule(int ruleid, String authorid, String type, String operator, String first, String last, boolean interEntityModifiable, String errorCode, String beforeAfter){
		String attrTable1 = first.split("\\.")[1];
		String attrTable2 = last.split("\\.")[1];
		String tablename_attr2 = last.split("\\.")[0];
		String remoteID_attr2 = "l_"+tablename_attr2;
		String cursorID_table1 = "cursor"+tablename_attr2+ruleid;
		String tablename_attr1 = first.split("\\.")[0];
		String cursorID_table2 = "cursor"+tablename_attr1+ruleid;
		String remoteID_attr1 = "l_"+tablename_attr1;
		String triggernameTable1 = tablename_attr1+type+ruleid;
		String triggernameTable2 = tablename_attr2+type+ruleid;
		System.out.println(toStringTableOne(triggernameTable1, attrTable1, attrTable2, cursorID_table1, tablename_attr2, remoteID_attr2 ,tablename_attr1, remoteID_attr1, errorCode, beforeAfter));
		setGeneratedCode(toStringTableOne(triggernameTable1, attrTable1, attrTable2, cursorID_table1, tablename_attr2, remoteID_attr2 ,tablename_attr1, remoteID_attr1, errorCode, beforeAfter));
		if(interEntityModifiable == true){
			System.out.println("\n");
			System.out.println(toStringTableTwo(triggernameTable2, attrTable1, attrTable2, cursorID_table2, tablename_attr2, remoteID_attr2 ,tablename_attr1, remoteID_attr1, errorCode, beforeAfter));
			setGeneratedCode(toStringTableOne(triggernameTable1, attrTable1, attrTable2, cursorID_table1, tablename_attr2, remoteID_attr2 ,tablename_attr1, remoteID_attr1, errorCode, beforeAfter) + 
					toStringTableTwo(triggernameTable2, attrTable1, attrTable2, cursorID_table2, tablename_attr2, remoteID_attr2 ,tablename_attr1, remoteID_attr1, errorCode, beforeAfter));
		}
		
	}

	public String toStringTableOne(String triggername, String attrTable1, String attrTable2, String cursorID_table1, String tablename_attr2, String remoteID_attr2, String tablename_attr1, String remoteID_attr1, String errorCode, String beforeAfter){
		String generatedDeclare = "Create or replace trigger "+triggername+
								  "\n"+beforeAfter+" insert or update on "+tablename_attr1+
								  "\nfor each row"+
								  "\nDECLARE \n"+
								  "l_passed boolean := true;\n"+
								  "cursor " + cursorID_table1+" is\n"+
								  "SELECT "+last+"\n"+
								  "from "+tablename_attr2+
								  "\nwhere "+tablename_attr2+".id = p_"+tablename_attr1+"_row.new_"+tablename_attr2+"_id;\n"+
								  remoteID_attr2+" "+first+"%type;\n";
		String generateBegin = "BEGIN\n"+
							   "open "+cursorID_table1+";\n"+
							   "fetch "+cursorID_table1+" into "+remoteID_attr2+";\n"+
							   "close "+cursorID_table1+";\n"+
							   "l_passed := p_"+tablename_attr1+"_row.new_"+attrTable1+" "+ getOperator(operator)+" "+remoteID_attr2+";\n"+
							   "if not l_passed then\n"+
							   "raise_application_error (-20800,'"+errorCode+"');\n"+
							   "end if;\n"+
							   "end;\n"+
							   "/";
		return generatedDeclare + generateBegin;
	}
	
	public String toStringTableTwo(String triggername, String attrTable1, String attrTable2, String cursorID_table2, String tablename_attr2, String remoteID_attr2, String tablename_attr1, String remoteID_attr1, String errorCode, String beforeAfter){
		String generatedDeclare = "Create or replace trigger "+triggername+
								  "\n"+beforeAfter+" insert or update on "+tablename_attr2+
								  "\nfor each row"+
								  "\nDECLARE \n"+
								  "l_passed boolean := true;\n"+
								  "cursor " + cursorID_table2+" is\n"+
								  "SELECT min("+first+")\n"+
								  "from "+tablename_attr1+
								  "\nwhere "+tablename_attr1+".id = p_"+tablename_attr2+"_row.new_"+tablename_attr1+"_id;\n"+
								  remoteID_attr1+" "+last+"%type;\n";
		String generateBegin = "BEGIN\n"+
							   "open "+cursorID_table2+";\n"+
							   "fetch "+cursorID_table2+" into "+remoteID_attr2+";\n"+
							   "close "+cursorID_table2+";\n"+
							   "l_passed := p_"+tablename_attr2+"_row.new_"+attrTable2+" "+ getOtherOperator(operator)+" "+remoteID_attr1+";\n"+
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
	
	public String getOtherOperator(String operator){
		if (operator.equals("NotEquals")){
			operator= "<>";
		}
		if (operator.equals("Equals")){
			operator= "=";
		}
		if (operator.equals("LessThan")){
			operator = ">";
		}
		if (operator.equals("GreaterThan")){
			operator = "<";
		}
		if (operator.equals("LessOrEqualTo")){
			operator = ">=";
		}
		if (operator.equals("GreaterOrEqualTo")){
			operator = "<=";
		}
		return operator;
	}
}

