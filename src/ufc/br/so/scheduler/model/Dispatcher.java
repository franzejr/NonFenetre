package ufc.br.so.scheduler.model;

import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.Queue;

/*
 * 
 */
public class Dispatcher implements Runnable {
	
	private Processor processor;
	private Queue queue;
	
	public Dispatcher(Processor processor, Queue queue) {
		this.processor = processor;
		this.queue = queue;
	}
	/*
	 * Select a process
	 */
	public Process selectProcess(){
		//The process has been used in a specific
		return queue.getListProcesses().get(0);
	}
	/*
	 *Dispatcher send a process to a processor
	 */
	public void sendToProcessor(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}


	public Queue getQueue() {
		return queue;
	}


	public void setQueue(Queue queue) {
		this.queue = queue;
	}


	public Processor getProcessor() {
		return processor;
	}


	public void setProcessor(Processor processor) {
		this.processor = processor;
	}	
}
