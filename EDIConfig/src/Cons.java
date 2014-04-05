
public enum Cons {
	SP(" "),
	EQ("="),
	JOB_PARN_REGEX("[\" ]"),
	STANDARD_RULE("EV_ATTRI=SENDER"),
	EXC("EXCLUSION"),
	GRP("GROUPING"),
	SETTING("EXCLUSION|GROUPING");
	
	private final String s;
	
	Cons(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return s;
	}
}
