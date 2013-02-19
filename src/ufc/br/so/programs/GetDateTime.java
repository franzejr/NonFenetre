package ufc.br.so.programs;

import java.util.List;

import ufc.br.so.memory.Page;
import ufc.br.so.services.ServicesRunning;

public class GetDateTime extends Program {
	
	public GetDateTime() {
		this.setName("getdatetime");
		this.setSize(1);
		this.setDescription("getdatetime - Return the current date and time");
	}

	@Override
	public void execute() {
		List<Page> busyPages = setBusyPages();
		ServicesRunning.getServiceRunning("getdatetime").execute();
		freePages(busyPages);
	}

}
