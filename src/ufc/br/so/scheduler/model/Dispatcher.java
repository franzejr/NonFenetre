package ufc.br.so.scheduler.model;

import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.Queue;


public class Dispatcher implements Runnable {

	private Processor processor;
	private Queue queue;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	
}
