package businessrule;

public class BusinessRule {
	public int ruleid;
	public String authorid;
	public String type;
	public String operator;
	public String first;
	public String last;
	public boolean interEntityModifiable = false;
	public String errorCode;
	public String generatedCode;
	public String beforeAfter;
	public String rangeAttribute;
	public String firstValue;
	public String range;
	
	public BusinessRule(int ruleid, String authorid, String type, String operator, String first, String last) {
		super();
		this.ruleid = ruleid;
		this.authorid = authorid;
		this.type = type;
		this.operator = operator;
		this.first = first;
		this.last = last;
	}

	//Dit lijkt sterk op een Factory Pattern btw, not sure tho of dat het ook echt is.
	public Object generateBusinessRule() {
		Object GeneratedRuleObject = new Object();

		//Attribute rules
		if (type.equals("AttributeRangeRule")) {
			GeneratedRuleObject = new AttributeRangeRule(ruleid, authorid, type, operator, rangeAttribute, first, last);
		}
		if (type.equals("AttributeCompareRule")) {
			GeneratedRuleObject = new AttributeCompareRule(ruleid, authorid, type, operator, first, last);
		}
		if (type.equals("AttributeListRule")) {
			GeneratedRuleObject = new AttributeListRule(ruleid, authorid, type, operator, first, last);
		}
		if (type.equals("AttributeOtherRule")) {
			GeneratedRuleObject = new AttributeOtherRule(ruleid, authorid, type, operator, rangeAttribute, first, last, range);
		}

		//Tuple Rules
		if (type.equals("TupleCompareRule")) {
			GeneratedRuleObject = new TupleCompareRule(ruleid, authorid, type, operator, first, last);
		}
		if (type.equals("TupleOtherRule")) {
			GeneratedRuleObject = new TupleOtherRule(ruleid, authorid, type, operator, first, last);
		}

		if (type.equals("EntityOtherRule")) {
			GeneratedRuleObject = new EntityOtherRule(ruleid, authorid, type, operator, first, last, errorCode, firstValue, beforeAfter);
		}
		if (type.equals("InterEntityCompareRule")) {
			GeneratedRuleObject = new InterEntityCompareRule(ruleid, authorid, type, operator, first, last, interEntityModifiable, errorCode, beforeAfter);
		}
		if (type.equals("ModifyRule")) {
			GeneratedRuleObject = new ModifyRule(ruleid, authorid, type, operator, first, last, errorCode, firstValue, beforeAfter);
		}
		return GeneratedRuleObject;
	}

	public String getBeforeAfter() {
		return beforeAfter;
	}

	public void setBeforeAfter(String beforeAfter) {
		this.beforeAfter = beforeAfter;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setInterEntityModifiable(boolean interEntityModifiable) {
		this.interEntityModifiable = interEntityModifiable;
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

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setRangeAttribute(String rangeAttribute) {
		this.rangeAttribute = rangeAttribute;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	public String getGeneratedCode() {
		return generatedCode;
	}

	void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}
}