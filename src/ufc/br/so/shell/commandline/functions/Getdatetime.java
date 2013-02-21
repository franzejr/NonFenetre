package ufc.br.so.shell.commandline.functions;

import ufc.br.so.services.ServicesRunning;

public class Getdatetime implements Function{

	@Override
	public void execute() {
		ServicesRunning.getServiceRunning("datetime").execute();
		
	}

}
