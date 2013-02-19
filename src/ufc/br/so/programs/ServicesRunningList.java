package ufc.br.so.programs;

import ufc.br.so.services.ServicesRunning;

public class ServicesRunningList extends Program {
	
	public ServicesRunningList() {
		this.setName("services_running");
		this.setSize(3);
		this.setDescription("services_running - Get a list of all services running");
	}

	@Override
	public void execute() {
		System.out.println(ServicesRunning.getNameServicesRunning());
	}


}
