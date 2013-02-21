package ufc.br.so.programs;

import java.util.List;

import ufc.br.so.memory.Page;

public class OutMemoryProgram extends Program {
	
	public OutMemoryProgram() {
		this.setName("outmemory");
		this.setSize(40);
		this.setDescription("outmemory - a program executing with all memory(RAM + HD)");
	}

	@Override
	public void execute() {
		List<Page> busyPages = this.setBusyPages();
		//Execution
		freePages(busyPages);
		
	}

}
