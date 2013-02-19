package ufc.br.so.services;

import ufc.br.so.programs.Program;
import ufc.br.so.storage.RAM;

public class MemInfo extends Program {

	@Override
	public void execute() {
		System.out.println(RAM.getRamSize());
	}

}
