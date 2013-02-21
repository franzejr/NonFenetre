package ufc.br.so.services;

import java.util.List;

import ufc.br.so.memory.Page;
import ufc.br.so.programs.Program;
import ufc.br.so.storage.RAM;

public class MemInfoService extends Program {
	
	public MemInfoService() {
		this.setName("meminfo");
		this.setSize(4);
		this.setDescription("meminfo - get the current memory usage");
	}

	@Override
	public void execute() {
		List<Page> busyPages = this.setBusyPages();
		System.out.println(RAM.getRamSize());
		freePages(busyPages);
	}

}
