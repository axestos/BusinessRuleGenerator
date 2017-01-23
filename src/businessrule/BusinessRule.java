package businessrule;

public class BusinessRule implements BusinessRuleType {
	public String type;
	public String operator;

	public BusinessRule(String type, String operator) {
		super();
		this.type = type;
		this.operator = operator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
//Dit lijkt sterk op een Factory Pattern btw, not sure tho of dat het ook echt is.
	@Override
	public void generateBusinessRule(String resultset) {
		String type = getType();
		String operator = getOperator();
		
		//Attribute rules
		if (type.equals("atr")) {
			new AttributeRangeRule(type, operator, resultset);
		}
		if (type.equals("atc")) {
			new AttributeCompareRule(type, operator, resultset);
		}
		if (type.equals("atl")) {
			new AttributeListRule(type, operator, resultset);
		}
		if (type.equals("ato")) {
			new AttributeOtherRule(type, operator, resultset);
		}
		
		//Tuple Rules
		if (type.equals("tuc")) {
			new TupleCompareRule(type, operator, resultset);
		}
		if (type.equals("tuo")) {
			new TupleOtherRule(type, operator, resultset);
		}
		
		
		if (type.equals("ent")) {
			new EntityOtherRule(type, operator, resultset);
		}
		if (type.equals("int")) {
			new InterEntityCompareRule(type, operator, resultset);
		}
		if (type.equals("mod")) {
			new ModifyRule(type, operator, resultset);
		}

	}

}
