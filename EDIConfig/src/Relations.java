import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Relations {
	TreeSet<Rule> rel;
	
	@Override
	public String toString() {
		String s=null;
		for (Rule r : rel)
			s+=r;
		return s;
	}
	
	public int readConfig(String fileName, Sort sort) {
		int numRules=0;
		File file = new File(fileName);
		if (file!=null) {
			try {
				String data;
				FileReader fr =	new FileReader(file);
				BufferedReader br =	new BufferedReader(fr);
				
				switch (sort) {
					case JOB:
						Rule.ComparatorJob  compJob  = new Rule.ComparatorJob();
						rel = new TreeSet<Rule>(compJob);
						break;
					case RULE:
						Rule.ComparatorRule compRule = new Rule.ComparatorRule();
						rel = new TreeSet<Rule>(compRule);
						break;
					default:
						break;
				}
				
				Rule rule=null;
				while ((data=br.readLine())!=null) {
					if (data.startsWith(Cons.STANDARD_RULE.toString())) {					
					// standard rule
						if (rule!=null) {
							rel.add(rule);
							numRules++;
						}
						rule = new Rule();
						String[] line = data.split(Cons.SP.toString());
						for (String s : line) {
							for (Attr a : Attr.values()) {
								if (s.startsWith(a.toString()+Cons.EQ.toString())) {
									String[] attributes = s.split(Cons.EQ.toString());
									if (attributes.length>1) rule.setAttr(a,attributes[1]);
									rule.setAttrFlag(a, Flag.ON);
									break;
								}
							}
							if (s.startsWith(Cons.STANDARD_RULE.toString()+Cons.EQ.toString())) {
								String[] attributes = s.split(Cons.EQ.toString());
								if (attributes.length>2) rule.setAttr(Attr.SENDER,attributes[2]);
								rule.setAttrFlag(Attr.SENDER, Flag.ON);
							}
						}
					}
					if (rule!=null && data.startsWith(Attr.JOBN_PAR.toString(), 2)) {
						String[] line = data.split(Cons.JOB_PARN_REGEX.toString());						
						for (String s : line) {
							for (Attr a : Attr.values()) {
								if (s.startsWith(a.toString()+Cons.EQ.toString())) {
									String[] attributes = s.split(Cons.EQ.toString());
									if (attributes.length>1) rule.setAttr(a,attributes[1]);
									rule.setAttrFlag(a, Flag.ON);
									break;
								}
							}
						}
					}
					
					if (data.startsWith("# ================= extended workflow rules and time events =================")) {
						break;
					}
				}
				
				if (rule!=null) {
					rel.add(rule);
					numRules++;
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException during reading file "+fileName);
			} catch (IOException e) {
				System.out.println("IOException during reading file "+fileName);
			}
		}
		return numRules;
	}
	
	public void writeConfig(String fileName, String delimeter) {
		File file = new File(fileName);
		if (file!=null) {
			try {
				FileWriter fw =	new FileWriter(file);
				BufferedWriter bw =	new BufferedWriter(fw);
				for (Attr a : Attr.values()) {
					bw.write(a.toString()+delimeter);
				}
				for (Rule r : rel) {
					bw.write("\n");
					for (Attr a : Attr.values()) {
						try {
							Field f = r.getClass().getDeclaredField("vals");
							f.setAccessible(true);
							TreeMap<Attr,String> vls = (TreeMap<Attr,String>)f.get(r);
							bw.write(vls.get(a)+delimeter);
						} catch (NoSuchFieldException e) {
							System.out.println("NoSuchFieldException "+e);							
						} catch (IllegalAccessException e) {
							System.out.println("IllegalAccessException "+e);
						}
					}
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("IOException during reading file "+fileName);
			}
		}
	}
	
	public void writeConfigXLS(String fileName) {
		int rowNum=0, cellNum=0;
		String job="";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet=null;
		Row row=null;
		for (Rule r : rel) {
			if (!job.equals(r.getAttr(Attr.JOBN_PAR))) {
				rowNum=0;
				cellNum=0;
				sheet = workbook.createSheet(r.getAttr(Attr.JOBN_PAR));
				row = sheet.createRow(rowNum++);
				for (Attr a : Attr.values()) {
					if (r.getAttrFlag(a).equals(Flag.ON)) {
						Cell cell = row.createCell(cellNum++);
						cell.setCellValue(a.toString());
					}
				}
			}
			cellNum=0;
			row = sheet.createRow(rowNum++);
			for (Attr a : Attr.values()) {
				if (r.getAttrFlag(a).equals(Flag.ON)) {
					try {
						Field f = r.getClass().getDeclaredField("vals");
						f.setAccessible(true);
						TreeMap<Attr,String> vls = (TreeMap<Attr,String>)f.get(r);
						Cell cell = row.createCell(cellNum++);
						cell.setCellValue(vls.get(a));
					} catch (NoSuchFieldException e) {
						System.out.println("NoSuchFieldException "+e);
					} catch (IllegalAccessException e) {
						System.out.println("IllegalAccessException "+e);
					} catch (Exception e) {
						System.out.println("Exception "+e);
					}
				}
			}			
			job=r.getAttr(Attr.JOBN_PAR);
		}
		
	    // Write the output to a file
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(fileName);
	    	workbook.write(fileOut);
	    	fileOut.close();
	    } catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException "+e);
	    } catch (IOException e) {
			System.out.println("IOException "+e);			
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
	}
}