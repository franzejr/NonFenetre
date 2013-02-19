package ufc.br.so.programs;

import ufc.br.so.services.ServicesRunning;

public class GetDateTime extends Program {
	
	public GetDateTime() {
		this.setName("getdatetime");
		this.setSize(1);
		this.setDescription("getdatetime - Return the current date and time");
	}

	@Override
	public void execute() {
		ServicesRunning.getServiceRunning("getdatetime").execute();
	}

}
