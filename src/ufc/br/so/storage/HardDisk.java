package ufc.br.so.storage;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.programs.Program;

public class HardDisk {
	
	private static int hardDiskSize;
	
	private List<Program> programs = new ArrayList<Program>();

	public static int getHardDiskSize() {
		return hardDiskSize;
	}

	public static void setHardDiskSize(int hardDiskSize) {
		HardDisk.hardDiskSize = hardDiskSize;
	}
	

}
