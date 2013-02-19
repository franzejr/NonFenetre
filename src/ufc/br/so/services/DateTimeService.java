package ufc.br.so.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ufc.br.so.programs.Program;

public class DateTimeService extends Program {
	
	public DateTimeService() {
		this.setName("datetime");
		this.setSize(2);
		this.setDescription("datetime - get the current date and time");
	}
	
	@Override
	public void execute() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
	}

}
