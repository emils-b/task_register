package task_reg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
	static ArrayList<String> filenames = new ArrayList<String>();
	static ArrayList<Employee> employee = new ArrayList<Employee>();
	static ArrayList<String> employeeNameList = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		
		final File folder = new File("D:\\Eclipse\\workspace\\task_reg\\Task history");
		String fileUrl = "D:\\Eclipse\\workspace\\task_reg\\Task history\\";
		Map<String, ArrayList<String>> monthDayFilenames = new LinkedHashMap<String, ArrayList<String>>();
		String[] monthList= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		
		listFilesForFolder(folder);
		for(int i=0; i<monthList.length; i++) {
			monthDayFilenames.put(monthList[i], getCorrectMonth(i+1, filenames));
		}
		
		for(String m:monthList) { 
			ArrayList<String> monthDays=(ArrayList<String>)monthDayFilenames.get(m);
				for(String d:monthDays) {
					//System.out.println(m + " " + d);
					readDays(fileUrl+m+"\\"+d);
				}
		}
				
		/*for (String n:employeeNameList) {
			System.out.println(n);
		}*/

		
		
	}
	
	//nolasa individuālus csv failus, atgriež rindas
	static void readDays(String url) {
		try {
			File file = new File(url);
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String row = read.nextLine();
				addNamesToEmployeeNameList(row);
			}
			read.close();
		} catch (Exception e) {
			System.err.println("Sumtin wen rong");
			e.printStackTrace();
		}
	}
	
	//savieto vārdus sarakstā
	public static void addNamesToEmployeeNameList(String row) {
		String name = row.split(";")[0];
		if (!isInNameList(employeeNameList, name)) {
		employeeNameList.add(name);
		}
	}
	
	//atlasa vārdus kuri atkārtojās, jāatlasa arī, lai whitespace netiek ievietots no tukšajiem csv failiem
	public static boolean isInNameList(ArrayList<String> list, String name) {
		boolean containsName = false;
		for(String n: list) {
			if (n.equals(name)) containsName=true;
		}
		return containsName;
	}
	
	//atgriež csv failus pa pieprasīto mēnesi
	public static ArrayList<String> getCorrectMonth(int m, List<String> files){
	ArrayList<String> newFiles = new ArrayList<String>();
		for(int i=0; i<files.size(); i++) {
			if (Integer.parseInt(files.get(i).split("_")[1])==m) {
				newFiles.add(files.get(i));
			}
		}
		return newFiles;
	}
	
	//atlasa csv failus no visām mapēm
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            if(fileEntry.getName().contains(".csv"))
	                filenames.add(fileEntry.getName());
	        }
	    }
	}
	
	
}
