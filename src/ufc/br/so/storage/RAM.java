package ufc.br.so.storage;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.programs.Program;

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
