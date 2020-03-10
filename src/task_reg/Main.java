package task_reg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
	static ArrayList<String> combinedList = new ArrayList<String>();
	static String monthInMethod = "";
	static ArrayList<String> filenames = new ArrayList<String>();
	static ArrayList<Employee> employee = new ArrayList<Employee>();
	static ArrayList<String> employeeNameList = new ArrayList<String>();
	static Map<String, ArrayList<String>> taskListPerMonth = new HashMap<String, ArrayList<String>>(); //katram mēnesim apvienoti pilnīgi visi dienu faili

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
					readDays(fileUrl+m+"\\"+d, m);
				}
		}
		
		for(String n : employeeNameList) {
			Employee e = new Employee(n);
			employee.add(e);
		}
		
		for(String m:monthList) { 
			ArrayList<String> monthTasks=(ArrayList<String>)taskListPerMonth.get("January");
			System.out.println(monthTasks);
				
		}
		
		
		/*for (Employee e:employee) {
			e.getAssignetTaskCountPerMonthForGivenEmployee(monthTaskList, month);
		};*/
		/*for(Employee e:employee) {
		System.out.println(e.taskCountGotEachMonth);
		}*/
			
		
	}
	/*
	 * readDays metode lasa katra faila rindas pēc kārtas, 
	 * sadale pa mēnešiem nesanāk
	 * var vienīgi pa dienām
	 * 
	 */
	
	//nolasa individuālus csv failus, atgriež rindas
	static void readDays(String url, String month) {
		try {
			File file = new File(url);
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String row = read.nextLine();
				addNamesToEmployeeNameList(row);
				combinesTasksPerMonth(row, month);
			}
			read.close();
		} catch (Exception e) {
			System.err.println("Sumtin wen rong");
			e.printStackTrace();
		}
	}
	
	//pievieno katram mēnesim atbilstošu uzdevumu sarakstu
	public static void combinesTasksPerMonth(String row, String month) {
		if (monthInMethod.length()==0) monthInMethod=month;
		if (month.equals(monthInMethod)) { //pirmo iterāciju nenostrādās, jāizdomā kā apiet
			combinesOneMonthTaskInOneList(row);
		}
		else if (!month.equals(monthInMethod)) {
			taskListPerMonth.put(monthInMethod,combinedList);
			monthInMethod=month;
		}
	}
	
	public static void combinesOneMonthTaskInOneList(String row){
		combinedList.add(row);
	}
	
	//savieto vārdus sarakstā
	public static void addNamesToEmployeeNameList(String row) {
		String name = row.split(";")[0];
		if (!isInNameList(employeeNameList, name)&&name.length()!=0) {
		employeeNameList.add(name);
		}
	}
	
	//atlasa vārdus kuri atkārtojās
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
	
	
	//atlasa saņemto darbu skaitu katrā mēnesī konkrētajam darbiniekam
	/*public static Map<String, Integer> getAssignetTaskCountPerMonthForGivenEmployee(String row, String month, String name){
		Map<String, Integer> taskCountGotEachMonth = new HashMap<String, Integer>();
		
		taskPerMonthForGivenEmployee(row, name);
		
		return taskCountGotEachMonth;
		
	}
	
	public static int taskPerMonthForGivenEmployee(String row, String name) {
		int taskCounter = 0;
		if(name.equals(row.split(";")[0])) taskCounter++;
		return taskCounter;
	}*/
	
	
}
