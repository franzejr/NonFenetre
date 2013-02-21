package ufc.br.so.programs;

import ufc.br.so.storage.RAM;
import java.util.List;
import ufc.br.so.memory.Page;

public class MemInfoProgram extends Program {

	public MemInfoProgram() {
		this.setName("getmeminfo");
		this.setSize(5);
		this.setDescription("getmeminfo - shows the current memory usage");
	}

	@Override
	public void execute() {
		List<Page> busyPages = this.setBusyPages();
		System.out.println("Free Memory: "+RAM.getRamSize() +"mb");
		freePages(busyPages);
	}

}
