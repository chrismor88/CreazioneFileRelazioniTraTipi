import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		String fileInput = "/home/ubuntu/input/annotated_nested_core_datagraph.tsv";
		String fileOutput = "/home/ubuntu/output/mapping_types_relation.tsv";
		String timestamp = "/home/ubuntu/output/timestamp_triples.txt";
//		String fileInput = "/home/chris88/Documenti/Tesi/componenti/10000_annotated_nested_core_datagraph.tsv";
//		String fileOutput = "/home/chris88/Documenti/Tesi/componenti/10000_mapping_types_relation.tsv";
//		String timestamp = "/home/chris88/Documenti/Tesi/componenti/10000_timestamp_triples.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileInput)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileOutput)));
			PrintWriter writerTimestamp = new PrintWriter(new File(timestamp));
			Set<String> triples = new LinkedHashSet<String>();
			Date start = new Date();
			
			String line = "";
			while((line=br.readLine())!=null){
				String[] fields = line.split("\t");
				String predicate = fields[2];
				String types1 = fields[5];
				String types2 = fields[6];
				
				String[] types_first_entity = types1.split(",");
				String[] types_second_entity = types2.split(",");
				String[] relations = predicate.split(",");
				
				for(String s1: types_first_entity){
					for(String s2: types_second_entity){
						for(String r1: relations){
							triples.add(s1+"\t"+r1+"\t"+s2+"\n");
							System.out.println(s1+"\t"+r1+"\t"+s2);
						}
					}
				}
			}
			
			for(String s : triples)
				bw.write(s);
			
			Date end = new Date();
			double totalTime = ((double)end.getTime() - (double)start.getTime()) /1000;
			
			br.close();
			bw.close();
			
			writerTimestamp.append("Start at: "+start.toString()+"\n");
			writerTimestamp.append("End at: "+end.toString()+"\n");
			writerTimestamp.append("Total time: "+totalTime+" sec+\n");
			writerTimestamp.flush();
			writerTimestamp.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
