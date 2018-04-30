import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PreProcess {

	public void preprocessFile(String ipFile, String opFile) {

		System.out.print("Start Processing... File [ " + ipFile.substring(ipFile.lastIndexOf("\\")+ 1) + " ] >>>>>>>> ");
		
		int numInstances = 1000000;
		
		FileInputStream fileInputStream = null;
		Scanner scanner = null;
		
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		

		try {

			fileInputStream = new FileInputStream(ipFile);
			scanner = new Scanner(fileInputStream);
			
			fileWriter = new FileWriter(opFile);
			bufferedWriter = new BufferedWriter(fileWriter);

			int countInstances = 0;
			
			String userID = null;
			while ((scanner.hasNext()) && (countInstances < numInstances)) {
				
				String line = scanner.nextLine();
				if(line == null || line.length() == 0) {

					continue;
				}

				if(line.indexOf("|") > -1) {

					String[] values = line.split("|");
					userID = values[0];
					continue;
				}
				
				if(line.indexOf("-1") > -1) {

					continue;
				}
				
				String[] values = line.split("\t");
				
				if(("0").equalsIgnoreCase(values[0])) {
				
					continue;
				}
								
				String preProcessedLine = userID + "," + values[0] + "," + values[1];
				//System.out.println(preProcessedLine);
				bufferedWriter.write(preProcessedLine);
				bufferedWriter.newLine();
				
				++countInstances;
			}

		} 
		catch (FileNotFoundException e) {

			e.printStackTrace();			
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally {

			if(scanner != null) {
				scanner.close();
			}
			
			if(bufferedWriter != null) {
				
				try {
					
					bufferedWriter.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		System.out.println("Done Processing.");
		
	}

	public static void main(String[] args) {

		String ipFile_Train1 = "../" +
				"ydata-ymusic-kddcup-2011-track1/trainIdx1.txt";
		
		String ipFile_Test1 = "../"+"ydata-ymusic-kddcup-2011-track1/testIdx1.txt";
		
		String ipFile_Validation1 = "../"+"ydata-ymusic-kddcup-2011-track1/validationIdx1.txt";
				
		String opFile_Train1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/trainIdx1.csv";
		
		String opFile_Test1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/testIdx1.csv";
		
		String opFile_Validation1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/validationIdx1.csv";
		
		
		String ipFile_Train2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/trainIdx2.txt";
		
		String ipFile_Test2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/testIdx2.txt";
		
		String opFile_Train2 = "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/trainIdx2.csv";
		
		String opFile_Test2 = "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/testIdx2.csv";
		
		
		PreProcess obj = new PreProcess();
		obj.preprocessFile(ipFile_Train1, opFile_Train1);
		obj.preprocessFile(ipFile_Test1, opFile_Test1);
		obj.preprocessFile(ipFile_Validation1, opFile_Validation1);
		
		obj.preprocessFile(ipFile_Train2, opFile_Train2);
		obj.preprocessFile(ipFile_Test2, opFile_Test2);
	}

}
