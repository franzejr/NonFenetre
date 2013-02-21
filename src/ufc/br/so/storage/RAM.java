package ufc.br.so.storage;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.programs.Program;
import ufc.br.so.programs.ServicesRunningList;
import ufc.br.so.services.ServicesRunning;

public class RAM {
	
	public static int totalRamSize;
	
	private static int ramSize;

	static List<Program> programs = new ArrayList<Program>();

	public static int getRamSize() {
		return ramSize;
	}

	public static void setRamSize(int ramSize) {
		RAM.ramSize = ramSize;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public static void addProgram(Program program) {
		programs.add(program);
		if(ServicesRunning.getSumMemoryServices() > ramSize){
			System.err.println("It's impossible to start all services!!!");
			System.exit(0);
		}
		ramSize -= program.getSize();
	}

	public static void stopProgram(Program program) {
		programs.remove(program);
		ramSize += program.getSize();
	}

	public static void stopProgram(String program) {
		for (Program p : programs) {
			if (p.getName().equals(program)) {
				programs.remove(p);
				ramSize += p.getSize();
			}
		}
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

}
