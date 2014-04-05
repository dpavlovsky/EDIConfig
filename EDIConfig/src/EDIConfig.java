
public class EDIConfig {

	public static void main(String[] args) {
		Relations relations = new Relations();
		int i = relations.readConfig("src/input/config_j_select.cfg",Sort.JOB);
		relations.writeConfig("src/output/roadmap.csv",";");
		relations.writeConfig("src/output/roadmap.xls","\t");
		relations.writeConfigXLS("src/output/roadmapJOB.xls");
//		System.out.print(relations);
		System.out.println(i);
	}

}