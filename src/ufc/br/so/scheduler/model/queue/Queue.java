package ufc.br.so.scheduler.model.queue;

import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;

public class Queue {

	private String name;
	private java.util.Queue<Process> processes;
	private ScheduleAlgorithm scheduleAlgorithm;
	private QueueType queueType;

	/*
	 * This variable will be used for the Aging to verify if a queue runs a lot
	 * of times, so the Aging Algorithms run other queue
	 */
	private int timesRunned;

	public Queue(QueueType key, ScheduleAlgorithm value) {
		this.scheduleAlgorithm = scheduleAlgorithm;
		this.scheduleAlgorithm.setCurrentQueue(this);
		this.queueType = queueType;
		this.timesRunned = 0;
		processes = this.scheduleAlgorithm.newQueueImpl();
	}
	
	//When to load to XML
	public Queue(){
		this.timesRunned = 0;
	}

	public String getName() {
		return name;
	}

	public java.util.Queue<Process> getListProcesses() {
		return processes;
	}
	
	public void setQueueType(QueueType queueType){
		this.queueType = queueType;
	}
	
	public QueueType getQueuePriority() {
		return queueType;
	}

	public void setListProcesses(java.util.Queue<Process> listProcesses) {
		this.processes = listProcesses;
	}

	public ScheduleAlgorithm getScheduleAlgorithm() {
		return scheduleAlgorithm;
	}

	public void setScheduleAlgorithm(ScheduleAlgorithm scheduleAlgorithm) {
		this.scheduleAlgorithm = scheduleAlgorithm;
		this.scheduleAlgorithm.setCurrentQueue(this);
		processes = this.scheduleAlgorithm.newQueueImpl();
	}

	public Process selectProcess() {
		Process aux = scheduleAlgorithm.selectProcess();
		if(aux != null && aux.isRunning() == true){
			return null;
		}
		if(aux != null){
			aux.setRunning(true);
		}
		return aux;
	}

	public int getTimesRunned() {
		return timesRunned;
	}
	
	public void setTimesRunned(final int timesRunned) {
		this.timesRunned = timesRunned;
	}

	public void addProcess(final Process process) {
		Statistics.getStatistics().addProcess(process);
		Statistics.getStatistics().addProcessQueue(this, process);
		processes.add(process);
	}

}
