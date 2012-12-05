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
	 * O dispatcher seleciona um processo dado um algoritmo de escalonamento
	 */
	public Process selectProcess(){
		//TODO ainda nao temos um escalador dos processos, entao vamos pegar o primeiro
		return queue.getListProcesses().get(0);
	}
	/*
	 * O dispatcher envia um processo para um processador
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
