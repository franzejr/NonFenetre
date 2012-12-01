package ufc.br.so.scheduler.model.queue;

import java.util.List;

import ufc.br.so.scheduler.model.processor.Process;

public class Queue {
	
	private List<Process> listProcesses;
	private ScheduleAlgorithm scheduleAlgorithm;

	public List<Process> getListProcesses() {
		return listProcesses;
	}

	public void setListProcesses(List<Process> listProcesses) {
		this.listProcesses = listProcesses;
	}

	public ScheduleAlgorithm getScheduleAlgorithm() {
		return scheduleAlgorithm;
	}

	public void setScheduleAlgorithm(ScheduleAlgorithm scheduleAlgorithm) {
		this.scheduleAlgorithm = scheduleAlgorithm;
	}
}
