package ufc.br.so.programs;

import java.util.List;

import ufc.br.so.memory.Page;
import java.util.List;
import ufc.br.so.memory.Page;

public class InstalledProgramsList extends Program {

	public InstalledProgramsList() {
		this.setName("installed_programs");
		this.setSize(4);
		this.setDescription("installed-programs - get a List of all Installed Programs");
	}
	
	
	@Override
	public void execute() {
		List<Page> busyPages = setBusyPages();
		System.out.println("List of Programs...");
		System.out.println( InstalledPrograms.getNameInstalledPrograms() );
		freePages(busyPages);
	}

}
