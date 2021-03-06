package businessrule;

public class AttributeOtherRule extends BusinessRule {
	public String output;


	public AttributeOtherRule(int ruleid, String authorid, String type, String operator, String rangeAttribute, String first, String last, String range) {
		super(ruleid, authorid, type, operator, first, last);
		generateAttributeOtherRule(ruleid, operator, rangeAttribute, first, last, range);
		}

		private void generateAttributeOtherRule(int ruleid, String operator, String rangeAttribute, String first, String last, String range){
			String [] tableAttribute = rangeAttribute.split("\\.");
			String tablename = tableAttribute[0];
			String attribute = tableAttribute[1];
			String constrainname = "constraint"+ruleid;
			String constraintstatement = "substr("+attribute+", "+range+") "+getOperator(operator)+" ("+"'"+first+"'"+" and '"+last+"')";
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
			if (operator.equals("NotBetween")){
				operator = "not between";
			}
			else if (operator.equals("Between")){
				operator = "between";
			}
			return operator;
		}
	}
