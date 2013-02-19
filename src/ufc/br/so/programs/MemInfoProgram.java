package ufc.br.so.programs;

import ufc.br.so.storage.RAM;

public class MemInfoProgram extends Program {

	public MemInfoProgram() {
		this.setName("getmeminfo");
		this.setSize(5);
		this.setDescription("getmeminfo - shows the current memory usage");
	}

	@Override
	public void execute() {
		System.out.println("Free Memory: "+RAM.getRamSize() +"mb");
	}

}
