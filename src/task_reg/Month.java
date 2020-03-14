package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Month {
		String name; //mēneša nosaukums
		Map<String, ArrayList<String>> taskListPerDate = new HashMap<String, ArrayList<String>>(); //satur katram datumam atbilstošu darbu sarakstu
		
		public Month(String month) {
			this.name=month;
		}
		
		//apvieno viena katras individuālā datums darbu sarakstu vienā mēneša
		public ArrayList<String> getsWholeMonthsTasks (ArrayList<String> monthDates){
			ArrayList<String> tasks = new ArrayList<String>();
			for (String d:monthDates) {
				tasks.addAll(this.taskListPerDate.get(d));
			}
			return tasks;
		}
		
		public Map<String, ArrayList<String>> getDayTaskList (ArrayList<String> monthDates){
			Map<String, ArrayList<String>> tasks = new HashMap<String, ArrayList<String>>();
			for (String d:monthDates) {
				tasks.put(d,this.taskListPerDate.get(d));
			}
			return tasks;
		}

}
