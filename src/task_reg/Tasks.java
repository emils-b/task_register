package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tasks {
	Employee employee;
	//uzdevumu skaits konkrētajam darbiniekam, salikts pa mēnešiem
	Map <String, Integer> done = new HashMap<String, Integer>();
	Map <String, Integer> failed = new HashMap<String, Integer>();
	Map <String, Integer> cancelled = new HashMap<String, Integer>();
	Map <String, Integer> bossdidit = new HashMap<String, Integer>();
	Map <String, Integer> redirected = new HashMap<String, Integer>();
	
	public Tasks(Employee employee) {
		this.employee=employee;
		sortSpecificTasks();
	}
	
	//padod taskCounter metodei apvienotu konkrēta mēneša sarakstu
	public void sortSpecificTasks() {
		for(String m:Main.months) {
			ArrayList<String> monthDates=(ArrayList<String>)Main.monthDateFilenames.get(m);
			Month monthObj = (Month) Main.monthList.get(m);
			ArrayList<String> tasks=monthObj.getsWholeMonthsTasks(monthDates);
			taskCounter(tasks, m);
		}
	}
	
	//sarakstā saskaita darbu profilu pa mēnešiem un atbilstošam mēnesim ievieto Map
	public void taskCounter(ArrayList<String> tasks, String month) {
		int doneCounter = 0;
		int failedCounter = 0;
		int cancelledCounter = 0;
		int bossdiditCounter = 0;
		//int redirectedCounter = 0;
		for(String t:tasks) {
			if(t.length()>0) {
			if(t.split(";")[2].equals("DONE")&&t.split(";")[1].equals(employee.name)) doneCounter++;
			if(t.split(";")[2].equals("FAILED")&&t.split(";")[1].equals(employee.name)) failedCounter++;
			if(t.split(";")[2].equals("CANCELLED")&&t.split(";")[1].equals(employee.name)) cancelledCounter++;
			if(t.split(";")[2].equals("BOSSDIDIT")&&t.split(";")[1].equals(employee.name)) bossdiditCounter++;
			//if(t.split(";")[2].equals("REDIRECTED")&&t.split(";")[1].equals(employee.name)) redirectedCounter++;
			}
		}
		
		this.done.put(month,doneCounter);
		this.failed.put(month,failedCounter);
		this.cancelled.put(month,cancelledCounter);
		this.bossdidit.put(month,bossdiditCounter);
		//this.redirected.put(month,redirectedCounter);
		
		//System.out.println(employee.name+" "+" "+month+" "+doneCounter+ "done");
		//System.out.println(employee.name+" "+" "+month+" "+failedCounter + "failed");
		//System.out.println(employee.name+" "+" "+month+" "+cancelledCounter + "cancelled");
		//System.out.println(employee.name+" "+" "+month+" "+bossdiditCounter + "bossdidit");
		//System.out.println(employee.name+" "+" "+month+" "+redirectedCounter + "redirected");
	}

}
