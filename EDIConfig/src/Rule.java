
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.TreeMap;

public class Rule implements Comparable<Rule> {

//	private Object[][] flags;
	private TreeMap<Attr,Flag> fgs = new TreeMap<Attr,Flag>();
	
	private String sender			="";
	private String receiver			="";
	private String obj_class		="";
	private String jobn_par			="";
	private String process_step		="";
	private String convtab	    	="";
	private String convtab_st	    ="";
	private String sap_id	    	="";
	private String unb_sender		="";
	private String unb_receiver		="";
	private String partner			="";
	private String ftp_param_file	="";
	private String dest_directory	="";
	private String dest_filename	="";
	private String use_triggerfile	="";
	private String file_header		="";
	private String shellscript		="";
	private String rasmap			="";
	private String rasmsg			="";
	private String email_addr		="";
	private String attach_name		="";
	private String subject			="";
	private String text_body_file	="";
	private String attachmentoption	="";
	private String oftp_type		="";
	private String oftp_originator	="";
	private String oftp_buf_size	="";
	private String oftp_window_size	="";
	private String convtab_sap		="";
	private String mandt			="";
	private String rcvprt			="";
	private String rcvpor			="";
	private String rcvprn			="";
	private String sndpor			="";
	private String sndprt			="";
	private String sndprn			="";
	private String mesfct			="";
	private String docnum			="";
	private String check_tab		="";
	private String standard			="";
	private String pool_name		="";
	private String dsn				="";
	private String rap_proof		="";
	private String rap_preconv		="";
	private String rap_name			="";
	private String rap_vars			="";
	private String rap_options		="";
	private String rap_idx			="";
	private String rap_version		="";
	private String ifid				="";
	private String use_lockfile		="";
	
	public Rule() {
		/*
		int index=0;
		flags = new Object[51][2];
		
		for (Attr a : Attr.values()) {
			flags[index][0]=a;
			flags[index][1]=Flag.OFF;
			index++;
		}
		*/
		for (Attr a : Attr.values())
			fgs.put(a, Flag.OFF);
	}
	
	@Override
	public String toString() {
		return 	""+Attr.JOBN_PAR+Cons.EQ+jobn_par+
				Cons.SP+Attr.SENDER+Cons.EQ+sender+
				Cons.SP+Attr.RECEIVER+Cons.EQ+receiver+
				Cons.SP+Attr.OBJ_CLASS+Cons.EQ+obj_class+
				Cons.SP+Attr.PROCESS_STEP+Cons.EQ+process_step+
				Cons.SP+Attr.CONVTAB+Cons.EQ+convtab+
				Cons.SP+Attr.CONVTAB_ST+Cons.EQ+convtab_st+
				Cons.SP+Attr.SAP_ID+Cons.EQ+sap_id+
				Cons.SP+Attr.UNB_SENDER+Cons.EQ+unb_sender+
				Cons.SP+Attr.UNB_RECEIVER+Cons.EQ+unb_receiver+
				Cons.SP+Attr.PARTNER+Cons.EQ+partner+
				Cons.SP+Attr.FTP_PARAM_FILE+Cons.EQ+ftp_param_file+
				Cons.SP+Attr.DEST_DIRECTORY+Cons.EQ+dest_directory+
				Cons.SP+Attr.DEST_FILENAME+Cons.EQ+dest_filename+
				Cons.SP+Attr.USE_TRIGGERFILE+Cons.EQ+use_triggerfile+
				Cons.SP+Attr.FILE_HEADER+Cons.EQ+file_header+
				Cons.SP+Attr.SHELLSCRIPT+Cons.EQ+shellscript+
				Cons.SP+Attr.RASMAP+Cons.EQ+rasmap+
				Cons.SP+Attr.RASMSG+Cons.EQ+rasmsg+
				Cons.SP+Attr.EMAIL_ADDR+Cons.EQ+email_addr+
				Cons.SP+Attr.ATTACH_NAME+Cons.EQ+attach_name+
				Cons.SP+Attr.SUBJECT+Cons.EQ+subject+
				Cons.SP+Attr.TEXT_BODY_FILE+Cons.EQ+text_body_file+
				Cons.SP+Attr.ATTACHMENTOPTION+Cons.EQ+attachmentoption+
				Cons.SP+Attr.OFTP_TYPE+Cons.EQ+oftp_type+
				Cons.SP+Attr.OFTP_ORIGINATOR+Cons.EQ+oftp_originator+
				Cons.SP+Attr.OFTP_BUF_SIZE+Cons.EQ+oftp_buf_size+
				Cons.SP+Attr.OFTP_WINDOW_SIZE+Cons.EQ+oftp_window_size+
				Cons.SP+Attr.CONVTAB_SAP+Cons.EQ+convtab_sap+
				Cons.SP+Attr.MANDT+Cons.EQ+mandt+
				Cons.SP+Attr.RCVPRT+Cons.EQ+rcvprt+
				Cons.SP+Attr.RCVPOR+Cons.EQ+rcvpor+
				Cons.SP+Attr.RCVPRN+Cons.EQ+rcvprn+
				Cons.SP+Attr.SNDPOR+Cons.EQ+sndpor+
				Cons.SP+Attr.SNDPRT+Cons.EQ+sndprt+
				Cons.SP+Attr.SNDPRN+Cons.EQ+sndprn+
				Cons.SP+Attr.MESFCT+Cons.EQ+mesfct+
				Cons.SP+Attr.DOCNUM+Cons.EQ+docnum+
				Cons.SP+Attr.CHECK_TAB+Cons.EQ+check_tab+
				Cons.SP+Attr.STANDARD+Cons.EQ+standard+
				Cons.SP+Attr.POOL_NAME+Cons.EQ+pool_name+
				Cons.SP+Attr.DSN+Cons.EQ+dsn+
				Cons.SP+Attr.RAP_PROOF+Cons.EQ+rap_proof+
				Cons.SP+Attr.RAP_PRECONV+Cons.EQ+rap_preconv+
				Cons.SP+Attr.RAP_NAME+Cons.EQ+rap_name+
				Cons.SP+Attr.RAP_VARS+Cons.EQ+rap_vars+
				Cons.SP+Attr.RAP_OPTIONS+Cons.EQ+rap_options+
				Cons.SP+Attr.RAP_IDX+Cons.EQ+rap_idx+
				Cons.SP+Attr.RAP_VERSION+Cons.EQ+rap_version+
				Cons.SP+Attr.IFID+Cons.EQ+ifid+
				Cons.SP+Attr.USE_LOCKFILE+Cons.EQ+use_lockfile+
				"\n";
	}
	
	@Override
	public int compareTo(Rule r) {
		int jobComp=jobn_par.compareTo(r.jobn_par);
		if (jobComp!=0)
			return jobComp;
		else
			return (sender+receiver+obj_class).compareTo(r.sender+r.receiver+r.obj_class);
	}
	
	static class ComparatorJob implements Comparator<Rule> {
		@Override
		public int compare(Rule r1, Rule r2) {
			int jobComp=r1.jobn_par.compareTo(r2.jobn_par);
			if (jobComp!=0)
				return jobComp;
			else
				return (r1.sender+r1.receiver+r1.obj_class).compareTo(r2.sender+r2.receiver+r2.obj_class);
		}
	}
	
	static class ComparatorRule implements Comparator<Rule> {
		@Override
		public int compare(Rule r1, Rule r2) {
			return (r1.sender+r1.receiver+r1.obj_class).compareTo(r2.sender+r2.receiver+r2.obj_class);
		}
	}
	
	public void setAttr(Attr attribute, String s) {
		try {
			Field f = this.getClass().getDeclaredField(attribute.toString().toLowerCase());
			f.setAccessible(true);
			f.set(this,s);
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
			Field f = this.getClass().getDeclaredField(attribute.toString().toLowerCase());
			f.setAccessible(true);
			return (String)f.get(this);
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return "";
	}
	/*
	public void setAttrFlag(Attr attribute, Flag flg) {
		try {
			Field f = this.getClass().getDeclaredField("flags");
			f.setAccessible(true);
			Object[][] flgs = (Object[][])f.get(this);
			for (int i = 0; i < flgs.length; i++) {
				if (flgs[i][0].toString().equals(attribute.toString())) {
					flgs[i][1]=flg;
					break;
				}
			}
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
			Object[][] flgs = (Object[][])f.get(this);
			for (int i = 0; i < flgs.length; i++) {
				if (flgs[i][0].toString().equals(attribute.toString()))
					return (Flag)flgs[i][1];
			}
		} catch (NoSuchFieldException e) {
			System.out.println("NoSuchFieldException "+e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException "+e);
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		return Flag.OFF;
	}
	*/
	public void setAttrFlag1(Attr attribute, Flag flg) {
		try {
			Field f = this.getClass().getDeclaredField("fgs");
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
	
	public Flag getAttrFlag1(Attr attribute) {
		try {
			Field f = this.getClass().getDeclaredField("fgs");
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
