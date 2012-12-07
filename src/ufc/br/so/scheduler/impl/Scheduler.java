package ufc.br.so.scheduler.impl;

import java.util.List;

import ufc.br.so.scheduler.inter.IScheduler;
import ufc.br.so.scheduler.inter.ThreadManagement;
import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Dispatcher;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.queue.QueueType;

/*
 * Scheduler
 */
public class Scheduler implements IScheduler, ThreadManagement {

	private MultiLevelQueue multilevelQueue;
	private Dispatcher dispatcher;
	private ProcessorCollection processorCollection;
	private Statistics statistics;
	
	private Thread schedulerThread;
	
	//State of the Scheduler
	private boolean running = false;
	
	public Scheduler(MultiLevelQueue multiLevelQueue, List<Processor> listProcessors) {
		this.multilevelQueue = multiLevelQueue;
		this.processorCollection = new ProcessorCollection(listProcessors);
		this.dispatcher = new Dispatcher();
		
		schedulerThread = new Thread(this);
	}
	
	@Override
	public void setInicialParameters(Configuration c) {
		// TODO Auto-generated method stub
	}
	/*
	 * We're loading the process in the Scheduler constructor, there we can pass by parameter
	 * a List of Process.
	 * @see ufc.br.so.scheduler.inter.IScheduler#loadProcess(ufc.br.so.scheduler.model.processor.Process)
	 */
	@Override
	public synchronized void loadProcess(final Process p) {
		multilevelQueue.getQueueByPriority(
				QueueType.getByProcessType(p.getTypeOfProcess())).addProcess(p);
	}

	@Override
	public void start() {
		statistics.setProcessorCollection(processorCollection);
		running = true;
		if(!schedulerThread.isAlive()){
			schedulerThread.start();
		}
		multilevelQueue.start();
		
	}

	@Override
	public void suspend() {
		this.running = false;
	}

	@Override
	public void resume() {
		if(!running){
			running = true;
			schedulerThread.notify();
			multilevelQueue.start();
			
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public Statistics getStatistics() {
		return this.statistics;
	}

	@Override
	public void run() {
		// tosco mas deixa por enquanto
		while (true) {
			//System.out.println("RODANDO - SCHEDULER");
			if (!running) {
				/*
				 * Caso a execṳ̣o tenha sido suspendida, o monitor fica em
				 * espera at̩ ser resumido ou a thread ser interrompida:
				 */
				try {
					this.wait();
				} catch (InterruptedException e) {
					// Ocorre quando o scheduler ̩ suspendido e em seguida
					// parado antes de ser resumido.
					// Neste deixa que a thread do escalonador morra:
					return;
				}
			}

			if (Thread.currentThread().isInterrupted()) {
				return;
			}

			// Envia para o dispatcher a fila escolhida pelo multilevel queue e
			// o processador escolhido:
			Queue queue = multilevelQueue.getSelectedQueue();
			if (queue != null) {
				//System.out.println("ENTROU");
				dispatcher.dispatch(queue, processorCollection);

			} else {
			//	System.out.println("ESPERANDO - SCHEDULER");
			}
		}
	}
	
	public ProcessorCollection getProcessorCollection() {
		return processorCollection;
	}

	public void setProcessorCollection(ProcessorCollection processorCollection) {
		this.processorCollection = processorCollection;
	}

}
