package ufc.br.so.shell.commandline.functions;

import ufc.br.so.storage.HardDisk;
import ufc.br.so.storage.RAM;

public class Lshw implements Function {

	@Override
	public void execute() {
		
		System.out.println("Non Fenetre Operating System version 2.1.2");
		System.out.println("RAM Size: "+RAM.getRamSize());
		System.out.println("Hard Disk Size: "+HardDisk.getHardDiskSize());
		
	}

}
