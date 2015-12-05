import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ExtractorWrongRecord {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/home/chris88/Documenti/Tesi/componenti/triples_relations_between_types/mapping_types_relation.tsv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("/home/chris88/Documenti/Tesi/componenti/triples_relations_between_types/mapping_types_relation_CLEANED.tsv"));
			
			Date start = new Date();
			String line = "";
			while((line=reader.readLine())!=null){
				String[] fields = line.split("\t");
				if(!(fields[0].startsWith("m.") || fields[2].startsWith("m."))){
					writer.write(line+"\n");
				}
			}
		
			Date end = new Date();
			
			double totalTime = ((double)end.getTime() - (double)start.getTime()) / 1000;
			System.out.println("Start at: "+start);
			System.out.println("End at: "+end);
			System.out.println("Total time: "+totalTime+" secs");
			reader.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
