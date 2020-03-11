package task_reg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Month {
		String name;
		Map<String, ArrayList<String>> taskListPerDay = new HashMap<String, ArrayList<String>>();
		
		public Month(String month) {
			this.name=month;
		}
		
		public ArrayList<String> getsWholeMonthsTasks (ArrayList<String> monthDays){
			ArrayList<String> tasks = new ArrayList<String>();
			for (String d:monthDays) {
				tasks.addAll(this.taskListPerDay.get(d));
			}
			return tasks;
		}

}
