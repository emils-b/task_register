package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Month {
		String name; //mēneša nosaukums
		Map<String, ArrayList<String>> taskListPerDay = new HashMap<String, ArrayList<String>>(); //satur katrai dienai atbilstošu darbu sarakstu
		
		public Month(String month) {
			this.name=month;
		}
		
		//apvieno viena katras individuālās dienas darbu sarakstu vienā mēneša
		public ArrayList<String> getsWholeMonthsTasks (ArrayList<String> monthDays){
			ArrayList<String> tasks = new ArrayList<String>();
			for (String d:monthDays) {
				tasks.addAll(this.taskListPerDay.get(d));
			}
			return tasks;
		}

}
