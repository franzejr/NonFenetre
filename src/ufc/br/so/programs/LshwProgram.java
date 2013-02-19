package ufc.br.so.programs;

import java.util.List;

import ufc.br.so.memory.Page;
import ufc.br.so.storage.HardDisk;
import ufc.br.so.storage.RAM;

public class LshwProgram extends Program {
	
	public LshwProgram() {
		this.setName("lshw");
		this.setSize(16);
		this.setDescription("lshw - lshw is a small tool to extract detailed information on the hardware configuration of the machine. It can report exact memory configuration, firmware version, mainboard configuration");
	}

	@Override
	public void execute() {
		
		List<Page> busyPages = setBusyPages();
		
		System.out.println("Non Fenetre Operating System version 2.1.2");
		System.out.println("Hard Disk Size: "+HardDisk.getHardDiskSize() +"mb");
		System.out.println("Total Memory Size: "+RAM.totalRamSize +"mb");
		System.out.println("Free Memory: "+RAM.getRamSize() +"mb");
		freePages(busyPages);
	}


}
