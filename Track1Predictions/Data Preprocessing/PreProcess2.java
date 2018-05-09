import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PreProcess_2 {

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
			Map<String, Map<String, String>> mapData = new LinkedHashMap<>();
			Set<String> setItemdID = new LinkedHashSet<>();
						
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
				
				Map<String, String> userData = mapData.get(userID);
				
				if(userData == null) {
					
					userData = new HashMap<>();
				}
				
				userData.put(values[0] , values[1]);
				setItemdID.add(values[0]);
				mapData.put(userID, userData);
					
				++countInstances;
			}
			
			
			String header = "UserID";
			for(String strItemID : setItemdID) {
				
				header += "," + strItemID;
			}
			
			bufferedWriter.write(header);
			bufferedWriter.newLine();
			
			for(String currUserID : mapData.keySet()) {
				
				String line = currUserID;
				Map<String, String> mapUserData = mapData.get(currUserID);
				
				for(String strItemID : setItemdID) {
					
					String frequency = (mapUserData.get(strItemID) == null ? "0" : "1");
					line += "," + frequency; 
				}
				
				bufferedWriter.write(String.valueOf(line));
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
				
		String opFile_Train1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/trainIdx1_ItemBased.csv";
		
		String opFile_Test1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/testIdx1_ItemBased.csv";
		
		String opFile_Validation1 = "../"+"ydata-ymusic-kddcup-2011-track1/Pre_Processed/validationIdx1_ItemBased.csv";
		
		
		String ipFile_Train2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/trainIdx2.txt";
		
		String ipFile_Test2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/testIdx2.txt";
		
		String opFile_Train2 = "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/trainIdx2_ItemBased.csv";
		
		String opFile_Test2 =  "../"
				+ "ydata-ymusic-kddcup-2011-track2/Pre_Processed/testIdx2_ItemBased.csv";
		
		PreProcess_2 obj = new PreProcess_2();
		obj.preprocessFile(ipFile_Train1, opFile_Train1);
		obj.preprocessFile(ipFile_Test1, opFile_Test1);
		obj.preprocessFile(ipFile_Validation1, opFile_Validation1);
		
		obj.preprocessFile(ipFile_Train2, opFile_Train2);
		obj.preprocessFile(ipFile_Test2, opFile_Test2);
	}

}
