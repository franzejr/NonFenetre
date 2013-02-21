package ufc.br.so.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ufc.br.so.memory.Page;
import ufc.br.so.programs.Program;

public class DateTimeService extends Program {
	
	public DateTimeService() {
		this.setName("datetime");
		this.setSize(2);
		this.setDescription("datetime - get the current date and time");
	}
	
	@Override
	public void execute() {
		List<Page> busyPages = this.setBusyPages();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		freePages(busyPages);
	}

}
