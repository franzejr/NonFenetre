package ufc.br.so.programs;

import ufc.br.so.services.ServicesRunning;
import java.util.List;
import ufc.br.so.memory.Page;

public class ServicesRunningList extends Program {
	
	public ServicesRunningList() {
		this.setName("services_running");
		this.setSize(3);
		this.setDescription("services_running - Get a list of all services running");
	}

	@Override
	public void execute() {
		List<Page> busyPages = setBusyPages();
		System.out.println(ServicesRunning.getNameServicesRunning());
		freePages(busyPages);
	}


}
