package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		String path = "C:\\Users\\mateu\\eclipse-workspace\\Exercicio Map\\src\\in.txt";
		Map<String, Integer> candidatesVotes = new HashMap<>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line != null) {
				String[] candidates = line.split(",");
				if(!candidatesVotes.containsKey(candidates[0])) {
					candidatesVotes.put(candidates[0], Integer.parseInt(candidates[1]));
				} else {
					candidatesVotes.put(candidates[0], candidatesVotes.get(candidates[0]) +  Integer.parseInt(candidates[1]));
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String key : candidatesVotes.keySet()) {
			System.out.println(key + " - " + candidatesVotes.get(key));
		}
	}

}
