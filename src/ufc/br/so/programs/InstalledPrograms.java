package ufc.br.so.programs;

import java.util.HashMap;

public final class InstalledPrograms {
	
	static HashMap<String,Program> installed = new HashMap<String,Program>();
	
	/*
	 * Installing all programs
	 * 
	 * To install a program, you need to put the bin at the functions package(similar to /usr/bin)
	 * and "install the program" setting this program at the programs package and after put
	 * the program as installed using the hashmap of this class.
	 * 
	 */
	private static void setListPrograms(){
		LshwProgram lshw = new LshwProgram();
		installed.put(lshw.getName(), lshw);
		
		ExitProgram exit = new ExitProgram();
		installed.put(exit.getName(), exit);
		
		StatisticsProgram statistics = new StatisticsProgram();
		installed.put(statistics.getName(), statistics);
		
		InstalledProgramsList installedPrograms = new InstalledProgramsList();
		installed.put(installedPrograms.getName(), installedPrograms);
		
		ServicesRunningList servicesRunning = new ServicesRunningList();
		installed.put(servicesRunning.getName(), servicesRunning);
		
		GetDateTime getDateTime = new GetDateTime();
		installed.put(getDateTime.getName(), getDateTime);
		
		MemInfoProgram memInfo = new MemInfoProgram();
		installed.put(memInfo.getName(), memInfo);
		
		
	}
	
	public static HashMap<String,Program> getInstalledPrograms(){
		System.out.println("Getting Installled Programs...");
		setListPrograms();
		return installed;
	}
	
	public static String getNameInstalledPrograms(){
		String installedPrograms = "";
		for (String program : installed.keySet()) {
			installedPrograms += " | ";
			installedPrograms += program;
		}
		
		return installedPrograms;
	}
	
	public static String getDescriptionInstalledProgram(String programName){
		return installed.get(programName).getDescription();
	}
	
	public static Program getInstalledProgram(String programName){
		return installed.get(programName);
	}
	
}
