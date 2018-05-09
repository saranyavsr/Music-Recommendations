import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PreProcess_NN {

	public void preprocessFile(String ipFile, String opFile) {

		System.out.print("Start Processing... File [ " + ipFile.substring(ipFile.lastIndexOf("\\")+ 1) + " ] >>>>>>>> ");
		
		int numInstances = 100000;
		
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
			List<Map<String, String>> lstMapData = new ArrayList<Map<String,String>>();
			Map<String, Integer> mapMaxRating = new HashMap<>();
			Map<String, Integer> mapMinRating = new HashMap<>();
			while ((scanner.hasNext()) && (countInstances < numInstances)) {
				
				String line = scanner.nextLine();
				if(line == null || line.length() == 0) {

					continue;
				}

				if(line.indexOf("|") > -1) {

					String[] values = line.split("|");
					userID = values[0];
					mapMaxRating.put(userID, -1);
					mapMinRating.put(userID, 101);
					continue;
				}
				
				if(line.indexOf("-1") > -1) {

					continue;
				}
				
				String[] values = line.split("\t");
				
				if(("0").equalsIgnoreCase(values[0])) {
				
					continue;
				}
				
				Map<String, String> mapLine = new LinkedHashMap<>();
				mapLine.put("UserID", userID);
				mapLine.put("ItemID", values[0]);
				mapLine.put("Rating", values[1]);
				
				
				int intUserRating = Integer.parseInt(values[1]);
				int intOldUserMaxRating = (mapMaxRating.get(userID) == null ? -1 : mapMaxRating.get(userID));
				int intOldUserMinRating = (mapMinRating.get(userID) == null ? 101 : mapMinRating.get(userID));
				
				if(intUserRating > intOldUserMaxRating) {
					mapMaxRating.put(userID, intUserRating);
				}
				
				if(intUserRating < intOldUserMinRating) {
					mapMinRating.put(userID, intUserRating);
				}
				
				lstMapData.add(mapLine);
				++countInstances;
			}
			
			String header = "UserID" + "," + "ItemID" + "," + "Rating";
			bufferedWriter.write(header);
			bufferedWriter.newLine();
			
			for(Map<String, String> mapLineData : lstMapData) {
				
				String strUserID =  mapLineData.get("UserID");
				double dblActualRating = Double.parseDouble(mapLineData.get("Rating"));
				double x_min = dblActualRating - mapMinRating.get(strUserID);
				double max_min = mapMaxRating.get(strUserID) - mapMinRating.get(strUserID);
				double dblRating = x_min/(double) max_min;
				
				String line = mapLineData.get("UserID");
				line += "," + mapLineData.get("ItemID");
				line += "," + dblActualRating/100;
				
				bufferedWriter.write(line);
				bufferedWriter.newLine();
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

		String ipFile_Train1 = "../"
				+ "ydata-ymusic-kddcup-2011-track1/trainIdx1.txt";
		
		String ipFile_Test1 = "../"+"ydata-ymusic-kddcup-2011-track1/testIdx1.txt";
		
		String ipFile_Validation1 = "../"+"ydata-ymusic-kddcup-2011-track1/validationIdx1.txt";
				
		String opFile_Train1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/trainIdx1_nn.csv";
		
		String opFile_Test1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/testIdx1_nn.csv";
		
		String opFile_Validation1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/validationIdx1_nn.csv";
		
		
		String ipFile_Train2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/trainIdx2.txt";
		
		String ipFile_Test2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/testIdx2.txt";
		
		String opFile_Train2 = "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/trainIdx2_nn.csv";
		
		String opFile_Test2 = "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/testIdx2_nn.csv";
		
		
		PreProcess_NN obj = new PreProcess_NN();
		obj.preprocessFile(ipFile_Train1, opFile_Train1);
		obj.preprocessFile(ipFile_Test1, opFile_Test1);
		obj.preprocessFile(ipFile_Validation1, opFile_Validation1);
		
		obj.preprocessFile(ipFile_Train2, opFile_Train2);
		obj.preprocessFile(ipFile_Test2, opFile_Test2);
	}

}
