package task_reg;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Methods {
	
	//izprintē katra darbinieka saņemtos un citiem uzdotos darbus
	public static void printTaskCount() {
		for (Employee e : Main.employee) {
			for (String m : Main.months) {
				System.out.println(e.name + " in " + m + " got " + e.taskCountGotEachMonth.get(m) + " tasks.");
				System.out.println(
						e.name + " in " + m + " assigned " + e.taskCountAssignedToOthersEachMonth.get(m) + " tasks.");
			}
		}
	}
	
	//nosaka, kuram darbiniekam tikuši deleģēti visvairāk uzdevumi kopā
	public static void getEmployeeWithMostTasks() {
		int totalTaskCount=0;
		String employeeName = "";
		for (Employee e : Main.employee) {
				if (e.getTotalTaskCount()>totalTaskCount) {
					totalTaskCount=e.getTotalTaskCount();
					employeeName=e.name;
			}
		}
		System.out.println("Most tasks got: "+employeeName+"("+totalTaskCount+").");
	}
	
	//nosaka, kurš uzdevis visvairāk uzdevumu kopā
	public static void getEmployeeWhoAssignetMostTasks() {
		int totalTaskCount=0;
		String employeeName = "";
		for (Employee e : Main.employee) {
				if (e.getTotalAssignedTaskCount()>totalTaskCount) {
					totalTaskCount=e.getTotalAssignedTaskCount();
					employeeName=e.name;
			}
		}
		System.out.println("Most tasks assigned: "+employeeName+"("+totalTaskCount+").");
	}
	
	//nosaka, kurš uzdevis visvairāk uzdevumu, kuri ir FAILED
	public static void getEmployeeWithMostFailedTasks() {
		int totalFailedTaskCount = 0;
		String employeeName = "";
		for (Employee e : Main.employee) {
			int failedTaskCounter=getEmployeeFailedTaskCount(e);
			if (failedTaskCounter>totalFailedTaskCount) {
				totalFailedTaskCount=failedTaskCounter;
				employeeName=e.name;
			}
		}
		System.out.println(employeeName + " got the bigest failed tasks count: "+totalFailedTaskCount);
	}
	
	public static int getEmployeeFailedTaskCount(Employee e) {
		int failedTaskCounter=0;
		for(String m: Main.months) {
			failedTaskCounter += e.tasks.statussCountPerMonth.get("FAILED").get(m);
		}
		return failedTaskCounter;
	}
	
	//aprēķina konkrētajai dienai kopējo darbu daudzumu
	public static int getTotalTaskCount(Map<String, ArrayList<String>> allYearTaskList, String day) {
		int totalCount = 0;
		for(String d:Main.filenames) {
			if (createDateFormat(d).equals(day)) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
						totalCount++;
					}
				}
			}
		}
		return totalCount;
	}
	
	//aprēķina konkrētajai dienai DONE darbu daudzumu
	public static int getDoneTaskCount(Map<String, ArrayList<String>> allYearTaskList, String day) {
		int doneCount = 0;
		for(String d:Main.filenames) {
			if (createDateFormat(d).equals(day)) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0 && r.split(Main.delimiter)[2].equals("DONE")) {
						doneCount++;
					}
				}
			}
		}
		return doneCount;
	}
	
	//pārveido faila nosaukumu uz datuma formātu, lai to p;arveidotu uz dienu
	public static String createDateFormat(String date){
		date=date.substring(0,date.length()-4).replace("_", "/");
		String day = LocalDate.parse( date , DateTimeFormatter.ofPattern( "d/M/uuuu" ) ).getDayOfWeek().getDisplayName( TextStyle.FULL , Locale.UK );
		return day;
	}
	
	//aprēķina success rate
	public static double calcucalteSuccessRate(double done, double all) {
		double successRate = 0;
		if(all==0) return successRate;
		successRate=(done/all)*100;
		return successRate;
	}
	
	//izprintē dienu ar vislielāku succes rate
	//jāpārveido, lai rate ir tikai 2 skaitļi aiz komata
	public static void getDayWithBigestSuccessRate(Map<String, Double> successRateForDays) {
		String day = "";
		double rate = 0;
		
		for(String d:Main.days) {
			if(successRateForDays.get(d)>rate) {
				day=d;
				rate=successRateForDays.get(d);
			}
		}
		System.out.println("Day with bigest success rate is: "+day+". With rate "+getRoundedDouble(rate)+"%.");
	}
	
	//noapaļo double vērtību 2 ciparus aiz komata
	public static Double getRoundedDouble (Double val) {
	DecimalFormat df2 = new DecimalFormat("#.##");
	val = Double.parseDouble(df2.format(val));
	return val;
	}
	
	
		
}
