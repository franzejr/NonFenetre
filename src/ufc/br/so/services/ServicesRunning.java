package ufc.br.so.services;

import java.util.HashMap;
import java.util.Iterator;

import ufc.br.so.programs.Program;
import ufc.br.so.storage.RAM;

/*
 * Services which are running at current time
 */
public class ServicesRunning {
	
	static HashMap<String,Program> servicesRunning = new HashMap<String,Program>();
	
	public static HashMap<String,Program> getServicesRunning(){
		System.out.println("Getting services running...");
		return servicesRunning;
	}
	
	public static void startService(Program program){
		servicesRunning.put(program.getName(), program);
		RAM.addProgram(program);
	}
	
	public static int getSumMemoryServices(){
		int sum = 0;
		for (String s : servicesRunning.keySet()) {
			sum += servicesRunning.get(s).getSize();
		}
		
		return sum;
	}
	
	public static void stopService(String nameService){
		servicesRunning.remove(nameService);
		RAM.stopProgram(nameService);
	}
	
	public static String getNameServicesRunning(){
		String installedPrograms = "";
		for (String program : servicesRunning.keySet()) {
			installedPrograms += " | ";
			installedPrograms += program;
		}
		
		return installedPrograms;
	}
	
	public static String getDescriptionServicesRunning(String programName){
		return servicesRunning.get(programName).getDescription();
	}
	
	public static Program getServiceRunning(String programName){
		return servicesRunning.get(programName);
	}
}
