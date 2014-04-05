
public enum Attr {
	JOBN_PAR		("JOBN_PAR"), 
	SENDER			("SENDER"),
	RECEIVER		("RECEIVER"),
	OBJ_CLASS		("OBJ_CLASS"),
	PROCESS_STEP	("PROCESS_STEP"),
	CONVTAB			("CONVTAB"),
	CONVTAB_ST		("CONVTAB_ST"),
	SAP_ID			("SAP_ID"),
	UNB_SENDER		("UNB_SENDER"),
	UNB_RECEIVER	("UNB_RECEIVER"),
	PARTNER			("PARTNER"),
	FTP_PARAM_FILE	("FTP_PARAM_FILE"),
	DEST_DIRECTORY	("DEST_DIRECTORY"),
	DEST_FILENAME	("DEST_FILENAME"),
	USE_TRIGGERFILE	("USE_TRIGGERFILE"),
	FILE_HEADER		("FILE_HEADER"),
	SHELLSCRIPT		("SHELLSCRIPT"),
	RASMAP			("RASMAP"),
	RASMSG			("RASMSG"),
	EMAIL_ADDR		("EMAIL_ADDR"),
	ATTACH_NAME		("ATTACH_NAME"),
	SUBJECT			("SUBJECT"),
	TEXT_BODY_FILE	("TEXT_BODY_FILE"),
	ATTACHMENTOPTION("ATTACHMENTOPTION"),
	OFTP_TYPE		("OFTP_TYPE"),
	OFTP_ORIGINATOR	("OFTP_ORIGINATOR"),
	OFTP_BUF_SIZE	("OFTP_BUF_SIZE"),
	OFTP_WINDOW_SIZE("OFTP_WINDOW_SIZE"),
	CONVTAB_SAP		("CONVTAB_SAP"),
	MANDT			("MANDT"),
	RCVPRT			("RCVPRT"),
	RCVPOR			("RCVPOR"),
	RCVPRN			("RCVPRN"),
	SNDPOR			("SNDPOR"),
	SNDPRT			("SNDPRT"),
	SNDPRN			("SNDPRN"),
	MESFCT			("MESFCT"),
	DOCNUM			("DOCNUM"),
	CHECK_TAB		("CHECK_TAB"),
	STANDARD		("STANDARD"),
	POOL_NAME		("POOL_NAME"),
	DSN				("DSN"),
	RAP_PROOF		("RAP_PROOF"),
	RAP_PRECONV		("RAP_PRECONV"),
	RAP_NAME		("RAP_NAME"),
	RAP_VARS		("RAP_VARS"),
	RAP_OPTIONS		("RAP_OPTIONS"),
	RAP_IDX			("RAP_IDX"),
	RAP_VERSION		("RAP_VERSION"),
	IFID			("IFID"),
	USE_LOCKFILE	("USE_LOCKFILE");
	
	private final String s;
	
	Attr(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return s;
	}
}