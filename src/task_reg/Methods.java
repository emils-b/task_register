package task_reg;

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
	
}
