package businessrule;

public class BusinessRule implements BusinessRuleType {
	public int ruleid;
	public String authorid;
	public String type;
	public String operator;
	public String compare;
	public String first;
	public String last;


	public BusinessRule(int ruleid, String authorid, String type, String operator, String compare, String first, String last) {
		super();
		this.ruleid = ruleid;
		this.authorid = authorid;
		this.type = type;
		this.operator = operator;
		this.compare = compare;
		this.first = first;
		this.last = last;
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

	public int getRuleid() {
		return ruleid;
	}

	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
	
	public String getAuthorid() {
		return authorid;
	}

	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}

	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	//Dit lijkt sterk op een Factory Pattern btw, not sure tho of dat het ook echt is.
	@Override
	public void generateBusinessRule() {
		String type = getType();
		String operator = getOperator();
		
		//Attribute rules
		if (type.equals("atr")) {
			new AttributeRangeRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("atc")) {

			new AttributeCompareRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("atl")) {
			new AttributeListRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("ato")) {
			new AttributeOtherRule(ruleid, authorid, type, operator, compare, first, last);
		}
		
		//Tuple Rules
		if (type.equals("tuc")) {
			new TupleCompareRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("tuo")) {
			new TupleOtherRule(ruleid, authorid, type, operator, compare, first, last);
		}
		
		
		if (type.equals("ent")) {
			new EntityOtherRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("int")) {
			new InterEntityCompareRule(ruleid, authorid, type, operator, compare, first, last);
		}
		if (type.equals("mod")) {
			new ModifyRule(ruleid, authorid, type, operator, compare, first, last);
		}

	}

}