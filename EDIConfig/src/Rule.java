
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.TreeMap;

public class Rule implements Comparable<Rule> {

	private TreeMap<Attr,Flag> 		flags = new TreeMap<Attr,Flag>();
	private TreeMap<Attr,String> 	vals = new TreeMap<Attr,String>();
	
	public Rule() {
		for (Attr a : Attr.values()) {
			flags.put(a, Flag.OFF);
			vals.put(a, "");
		}
	}
	
	@Override
	public String toString() {
		String str="";
		for (Attr a : Attr.values()) {
			str+=a.toString()+Cons.EQ+vals.get(a)+";";
		}
		return str+"\n";
	}
	
	@Override
	public int compareTo(Rule r) {
		int jobComp=vals.get(Attr.JOBN_PAR).compareTo(r.vals.get(Attr.JOBN_PAR));
		if (jobComp!=0)
			return jobComp;
		else
			return (vals.get(Attr.SENDER)+vals.get(Attr.RECEIVER)+vals.get(Attr.OBJ_CLASS)).compareTo(r.vals.get(Attr.SENDER)+r.vals.get(Attr.RECEIVER)+r.vals.get(Attr.OBJ_CLASS));
	}
	
	static class ComparatorJob implements Comparator<Rule> {
		@Override
		public int compare(Rule r1, Rule r2) {
			int jobComp=r1.vals.get(Attr.JOBN_PAR).compareTo(r2.vals.get(Attr.JOBN_PAR));
			if (jobComp!=0)
				return jobComp;
			else
				return (r1.vals.get(Attr.SENDER)+r1.vals.get(Attr.RECEIVER)+r1.vals.get(Attr.OBJ_CLASS)).compareTo(r2.vals.get(Attr.SENDER)+r2.vals.get(Attr.RECEIVER)+r2.vals.get(Attr.OBJ_CLASS));
		}
	}
	
	static class ComparatorRule implements Comparator<Rule> {
		@Override
		public int compare(Rule r1, Rule r2) {
			return (r1.vals.get(Attr.SENDER)+r1.vals.get(Attr.RECEIVER)+r1.vals.get(Attr.OBJ_CLASS)).compareTo(r2.vals.get(Attr.SENDER)+r2.vals.get(Attr.RECEIVER)+r2.vals.get(Attr.OBJ_CLASS));
		}
	}
	
	public void setAttr(Attr attribute, String s) {
		try {
			Field f = this.getClass().getDeclaredField("vals");
			f.setAccessible(true);
			TreeMap<Attr,String> vls = (TreeMap<Attr,String>)f.get(this);
			vls.put(attribute, s);
			f.set(this,vls);
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
	}
	
	public String getAttr(Attr attribute) {
		try {
			Field f = this.getClass().getDeclaredField("vals");
			f.setAccessible(true);
			TreeMap<Attr,String> vls = (TreeMap<Attr,String>)f.get(this);
			return vls.get(attribute);
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return "";
	}

	public void setAttrFlag(Attr attribute, Flag flg) {
		try {
			Field f = this.getClass().getDeclaredField("flags");
			f.setAccessible(true);
			TreeMap<Attr,Flag> flgs = (TreeMap<Attr,Flag>)f.get(this);
			flgs.put(attribute, flg);
			f.set(this,flgs);
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
	}
	
	public Flag getAttrFlag(Attr attribute) {
		try {
			Field f = this.getClass().getDeclaredField("flags");
			f.setAccessible(true);
			TreeMap<Attr,Flag> flgs = (TreeMap<Attr,Flag>)f.get(this);
			return flgs.get(attribute);
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return Flag.OFF;
	}
}