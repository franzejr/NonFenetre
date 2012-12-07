package ufc.br.so.scheduler.model;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.Queue;

public class Dispatcher implements Runnable {
	
	private Processor processor;
	private List<Queue> listQueues;
	
	public Dispatcher(Processor processor) {
		this.processor = processor;
		this.listQueues = new ArrayList<Queue>();
	}
	
	public void addQueue(Queue queue){
		this.listQueues.add(queue);
	}
	
	public void addQueueList(List<Queue> listQueues){
		this.listQueues.addAll(listQueues);
	}
	
	/*
	 * Select a process
	 */
	public Process selectProcess(){
		//The process has been used in a specific
//		return queue.getListProcesses().get(0);
		return null;
	}
	
	/*
	 *Dispatcher send a process to a processor
	 */
	public void sendToProcessor(){
		
	}

	@Override
	public void run() {
		processor.run(selectProcess());
	}
	
	public Processor getProcessor() {
		return processor;
	}
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public List<Queue> getListQueues() {
		return listQueues;
	}

	public void setListQueues(List<Queue> listQueues) {
		this.listQueues = listQueues;
	}
}
