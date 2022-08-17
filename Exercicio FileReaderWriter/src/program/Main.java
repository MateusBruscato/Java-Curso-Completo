package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//Path to read file
		String path = "C:\\Users\\mateu\\eclipse-workspace\\Exercicio FileReaderWriter\\produtos.csv";

		//Path to write file
		String directoryPath = "C:\\Users\\mateu\\eclipse-workspace\\Exercicio FileReaderWriter\\out";
		new File(directoryPath).mkdir();
		String pathSummary = directoryPath + "\\produtosOut.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path));
				BufferedWriter bw = new BufferedWriter(new FileWriter(pathSummary))){
			String line = br.readLine();
			while(line != null)
			{
				String[] productValues = line.split(",");
				bw.write(productValues[0] + "," + Double.valueOf(productValues[1]) * Double.valueOf(productValues[2]));
				bw.newLine();
				line = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
