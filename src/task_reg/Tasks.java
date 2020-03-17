package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tasks {
	Employee employee;
	//uzdevumu skaits konkrētajam darbiniekam, salikts pa mēnešiem
	Map<String, Map <String, Integer>> statussCountPerMonth = new HashMap<String, Map <String, Integer>>();
	/*Map <String, Integer> done = new HashMap<String, Integer>();
	Map <String, Integer> failed = new HashMap<String, Integer>();
	Map <String, Integer> cancelled = new HashMap<String, Integer>();
	Map <String, Integer> bossdidit = new HashMap<String, Integer>();
	Map <String, Integer> redirected = new HashMap<String, Integer>();*/
	
	public Tasks(Employee employee) {
		this.employee=employee;
		sortSpecificTasks();
	}
	/*
	 * Izveidoju intereses pēc, lai ir līdzīgs princips kā bija iepriekš, ka ir katram mēnesim statusu skaits.
	 * 
	 */
	//katram uzdevumam saskaita tā katra statusa biežumu katrā mēnesī un saliek map (statuss, mēnesis, skaits)
	public void sortSpecificTasks() {
		int statussCounter = 0;
		Map<String, Integer> statussCountPerMonth = new HashMap<String, Integer>();
		for(String s:Main.statussList) {
			for(String m:Main.months) {
				ArrayList<String> monthDates=(ArrayList<String>)Main.monthDateFilenames.get(m);
				Month monthObj = (Month) Main.monthList.get(m);
				ArrayList<String> tasks=monthObj.getsWholeMonthsTasks(monthDates);
				//taskCounter(tasks, m);
				statussCounter = getStatussCount(tasks,s);
				statussCountPerMonth.put(m, statussCounter);
				this.statussCountPerMonth.put(s, statussCountPerMonth);
			}
		}
	}
	
	public int getStatussCount(ArrayList<String> tasks,String s) {
		int statussCounter = 0;
		for(String t:tasks) {
			if(t.length()>0) {
				if(t.split(Main.delimiter)[2].equals(s)&&t.split(Main.delimiter)[1].equals(employee.name)) statussCounter++;
			}
		}
		return statussCounter;
	}
	
	//sarakstā saskaita darbu profilu pa mēnešiem un atbilstošam mēnesim ievieto Map
	/*public void taskCounter(ArrayList<String> tasks, String month) {
		int doneCounter = 0;
		int failedCounter = 0;
		int cancelledCounter = 0;
		int bossdiditCounter = 0;
		int redirectedCounter = 0;
			for(String t:tasks) {
				if(t.length()>0) {
					if(t.split(Main.delimiter)[2].equals("DONE")&&t.split(Main.delimiter)[1].equals(employee.name)) doneCounter++;
					if(t.split(Main.delimiter)[2].equals("FAILED")&&t.split(Main.delimiter)[1].equals(employee.name)) failedCounter++;
					if(t.split(Main.delimiter)[2].equals("CANCELLED")&&t.split(Main.delimiter)[1].equals(employee.name)) cancelledCounter++;
					if(t.split(Main.delimiter)[2].equals("BOSSDIDIT")&&t.split(Main.delimiter)[1].equals(employee.name)) bossdiditCounter++;
					if(t.split(Main.delimiter)[2].equals("REDIRECTED")&&t.split(Main.delimiter)[1].equals(employee.name)) redirectedCounter++;
				}
			}
		this.done.put(month,doneCounter);
		this.failed.put(month,failedCounter);
		this.cancelled.put(month,cancelledCounter);
		this.bossdidit.put(month,bossdiditCounter);
		this.redirected.put(month,redirectedCounter);
	}*/

}
