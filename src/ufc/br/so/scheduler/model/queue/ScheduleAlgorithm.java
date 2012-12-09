package ufc.br.so.scheduler.model.queue;

import java.util.List;

import ufc.br.so.kernel.spi.Algorithm;
import ufc.br.so.scheduler.model.processor.Process;

/*
 * Schedule Algorithms
 * 
 */
public abstract class ScheduleAlgorithm extends Algorithm<List<Process>> {
	
	private boolean preemptive;
	protected Queue queue;
	
	public ScheduleAlgorithm(boolean preemptive){
		this.setPreemptive(preemptive);
	}
	
	public boolean isPreemptive(){
		return preemptive;
	}
	
	public Queue getCurrentQueue(){
		return queue;
	}
	
	public void setCurrentQueue(Queue queue){
		this.queue = queue;
	}
	
	public void setPreemptive(boolean preemptive) {
		this.preemptive = preemptive;
	}
	
	public abstract java.util.Queue<Process> newQueueImpl();
	
	public Process selectProcess(){
		do{
			Process process = getCurrentQueue().getListProcesses().peek();
			
			if(process.isFinished()){
				getCurrentQueue().getListProcesses().remove(process);
			}else{
				return process;
			}
			
		}while(!getCurrentQueue().getListProcesses().isEmpty());
		return null;
	}
	
	


}
