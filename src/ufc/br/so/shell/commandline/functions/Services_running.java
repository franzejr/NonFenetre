package ufc.br.so.shell.commandline.functions;

import ufc.br.so.services.ServicesRunning;

public class Services_running implements Function {

	@Override
	public void execute() {
		System.out.println(ServicesRunning.getNameServicesRunning());
		
	}

}
