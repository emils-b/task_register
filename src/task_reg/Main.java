package task_reg;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
	static ArrayList<String> filenames = new ArrayList<String>();//visu csv failu nosaukumi
	static ArrayList<Employee> employee = new ArrayList<Employee>();//Employee objektu saraksts
	static ArrayList<String> employeeNameList = new ArrayList<String>();//darbinieku vārdu saraksts (vai vajadzīgs vispār??)
	static Map<String, Month> monthList = new HashMap<String, Month>();//Month objektu saraksts, sasaistīts ar attiecīgo mēneša nosaukumu (vai nevar veidot kā parasti ArrayList???)
	static String[] months= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; //mēnešu saraksts
	static String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};//nedēļas dienu saraksts
	static Map<String, ArrayList<String>> monthDateFilenames = new LinkedHashMap<String, ArrayList<String>>();//katram mēnesim attiecīgie failu nosaukumi
	static Map<String, ArrayList<String>> dayTasks = new HashMap<String, ArrayList<String>>();//salikts katrai dienai darbu saraksts
	static Map<String, Double> successRateForDays = new HashMap<String, Double>();
	
	public static void main(String[] args) throws IOException, ParseException {
		
		final File folder = new File("D:\\Eclipse\\workspace\\task_reg\\Task history");		
		
		//saliek katram mēnesim atbilstošos csv failu nosaukumus
		listFilesForFolder(folder);
		for(int i=0; i<months.length; i++) {
			monthDateFilenames.put(months[i], getCorrectMonth(i+1, filenames));
		}
		
		//izveido month objektu
		for (String m:months) {
			Month month=new Month(m);
			monthList.put(m, month);
		}
		
		//padod katra csv faila url, lai nolasītu metodē katru failu
		for(String m:months) {
			ArrayList<String> monthDates=(ArrayList<String>)monthDateFilenames.get(m);
				for(String d:monthDates) {
					readDates(folder+"\\"+m+"\\"+d, m, d);
				}
		}
		
		//izveido employee objektus
		for(String n : employeeNameList) {
			Employee e = new Employee(n);
			employee.add(e);
		}
		
		//izveido katra darbinieka objektam citiem iedoto un saņemto darbu daudzumu katram mēnesim
		for(Employee e:employee) {
			for(String m:months) {
				ArrayList<String> monthDates=(ArrayList<String>)monthDateFilenames.get(m);
				Month monthObj = (Month) monthList.get(m);
				ArrayList<String> tasks=monthObj.getsWholeMonthsTasks(monthDates);
				e.getTaskCountPerMonth(tasks, m);
				e.getAssignedTaskCountPerMonth(tasks, m);
			}
		}
		
		
		//izprintē katra darbinieka saņemtos un citiem uzdotos darbus
		//Methods.printTaskCount();
		
		//izprintē, kuram darbiniekam tikuši deleģēti visvairāk uzdevumi kopā
		//Methods.getEmployeeWithMostTasks();
		
		//izprintē, kurš uzdevis visvairāk uzdevumu kopā
		//Methods.getEmployeeWhoAssignetMostTasks();
		
		//izprintē, kurš uzdevis visvairāk uzdevumu kuri ir FAILED
		//Methods.getEmployeeWithMostFailedTasks();
		
		
		

		Map<String, ArrayList<String>> allYearTaskList = new HashMap<String, ArrayList<String>>();	
		for(String m:months) {
				ArrayList<String> monthDates=(ArrayList<String>)monthDateFilenames.get(m);
				Month monthObj = (Month) monthList.get(m);
				allYearTaskList.putAll(monthObj.getDayTaskList(monthDates));
				
			}
		
		for(String d:days) {
			int totalTaskCount = Methods.getTotalTaskCount(allYearTaskList, d);
			int totalDoneTaskCount = Methods.getDoneTaskCount(allYearTaskList, d);
			double successRate = Methods.calcucalteSuccessRate(totalDoneTaskCount, totalTaskCount);
			successRateForDays.put(d, successRate);
			//System.out.println(successRate);
		}
		
		//izprintē dienu ar vislielāku succes rate
		Methods.getDayWithBigestSuccessRate(successRateForDays);
			
		
	}
	
	/*nolasa individuālus csv failus pa dienām, 
	 * un apkopo katras dienas darbu sarakstu vienā arrayList
	 * tālāk katrai dienai HashMap piesaista tās dienas darbus
	 */
	static void readDates(String url, String month, String date) {
		try {
			File file = new File(url);
			Scanner read = new Scanner(file);
			ArrayList<String> dateTasks = new ArrayList<String>();
			while (read.hasNextLine()) {
				String row = read.nextLine();
				addNamesToEmployeeNameList(row);
				dateTasks.add(row);
			}
			monthList.get(month).taskListPerDate.put(date,dateTasks);
			read.close();
		} catch (Exception e) {
			System.err.println("Sumtin wen rong");
			e.printStackTrace();
		}
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
		
}
