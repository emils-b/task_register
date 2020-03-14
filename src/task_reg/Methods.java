package task_reg;

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
		System.out.println("Visvairāk uzdevumi deleģēti: "+employeeName+"("+totalTaskCount+").");
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
		System.out.println("Visvairāk uzdevumus deleģēja: "+employeeName+"("+totalTaskCount+").");
	}
	
	//nosaka, kurš uzdevis visvairāk uzdevumu, kuri ir FAILED
	public static void getEmployeeWithMostFailedTasks() {
		int totalFailedTaskCount = 0;
		String employeeName = "";
		for (Employee e : Main.employee) {
			int failedTaskCounter=0;
			for(String m: Main.months) {
				failedTaskCounter += e.tasks.failed.get(m);
			}
			if (failedTaskCounter>totalFailedTaskCount) {
				totalFailedTaskCount=failedTaskCounter;
				employeeName=e.name;
			}
		}
		System.out.println(employeeName + " got the bigest failed tasks count: "+totalFailedTaskCount);
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
					if(r.length()>0 && r.split(";")[2].equals("DONE")) {
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
	public static void getDayWithBigestSuccessRate(Map<String, Double> successRateForDays) {
		String day = "";
		double rate = 0;
		for(String d:Main.days) {
			if(successRateForDays.get(d)>rate) {
				day=d;
				rate=successRateForDays.get(d);
			}
		}
		System.out.println("Day with bigest success rate is: "+day+". With rate "+rate+"%.");
	}
	
	//aprēķina success rate
	/*public static void getSuccessRateDay(Map<String, ArrayList<String>> allYearTaskList) {
		int mondayTotalTaskCount=0, tuesdayTotalTaskCount=0, wednesdayTotalTaskCount=0, thursdayTotalTaskCount=0, fridayTotalTaskCount=0, saturdayTotalTaskCount=0, sundayTotalTaskCount = 0;
		int mondayDoneTaskCount=0, tuesdayDoneTaskCount=0, wednesdayDoneTaskCount=0, thursdayDoneTaskCount=0, fridayDoneTaskCount=0, saturdayDoneTaskCount=0, sundayDoneTaskCount = 0;
		for(String d:Main.filenames) {
			if (createDateFormat(d).equals("Monday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					mondayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					mondayDoneTaskCount++;
					}
					}		
				}
			}
			if (createDateFormat(d).equals("Tuesday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					tuesdayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					tuesdayDoneTaskCount++;
					}
					}		
				}
			}
			if (createDateFormat(d).equals("Wednesday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					wednesdayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					wednesdayDoneTaskCount++;
					}
					}		
				}
			}
			if (createDateFormat(d).equals("Thursday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					thursdayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					thursdayDoneTaskCount++;
					}
					}		
				}
			}
			if (createDateFormat(d).equals("Friday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					fridayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					fridayDoneTaskCount++;
					}
					}		
				}
			}
			if (createDateFormat(d).equals("Saturday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					saturdayTotalTaskCount++;
					if(r.length()>0&&r.split(";")[2].equals("DONE")) {
					saturdayDoneTaskCount++;
					}
					}
				}
			}
			if (createDateFormat(d).equals("Sunday")) {
				for(String r:allYearTaskList.get(d)) {
					if(r.length()>0) {
					sundayTotalTaskCount++;
					if(r.split(";")[2].equals("DONE")) {
					sundayDoneTaskCount++;
					}
					}		
				}
			}
		}
		System.out.println("Monday "+mondayTotalTaskCount+" "+mondayDoneTaskCount+" "+calcucalteSuccessRate(mondayDoneTaskCount, mondayTotalTaskCount));
		System.out.println("Tuesday "+tuesdayTotalTaskCount+" "+tuesdayDoneTaskCount+" "+calcucalteSuccessRate(tuesdayDoneTaskCount, tuesdayTotalTaskCount));
		System.out.println("Wednesday "+wednesdayTotalTaskCount+" "+wednesdayDoneTaskCount+" "+calcucalteSuccessRate(wednesdayDoneTaskCount, wednesdayTotalTaskCount));
		System.out.println("Thursday "+thursdayTotalTaskCount+" "+thursdayDoneTaskCount+" "+calcucalteSuccessRate(thursdayDoneTaskCount, thursdayTotalTaskCount));
		System.out.println("Friday "+fridayTotalTaskCount+" "+fridayDoneTaskCount+" "+calcucalteSuccessRate(fridayDoneTaskCount, fridayTotalTaskCount));
		System.out.println("Saturday "+saturdayTotalTaskCount+" "+saturdayDoneTaskCount+" "+calcucalteSuccessRate(saturdayDoneTaskCount, saturdayTotalTaskCount));
		System.out.println("Sunday "+sundayTotalTaskCount+" "+sundayDoneTaskCount+" "+calcucalteSuccessRate(sundayDoneTaskCount, sundayTotalTaskCount));
	}*/
		
}
