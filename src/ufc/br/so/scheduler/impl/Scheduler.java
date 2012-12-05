package ufc.br.so.scheduler.impl;

import ufc.br.so.scheduler.inter.IScheduler;
import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Dispatcher;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
/*
 * Escalonador
 */
public class Scheduler implements IScheduler, Runnable {

	private MultiLevelQueue multilevelQueue;
	private Dispatcher dispatcher;
	private ProcessorCollection processorCollection;
	private Statistics statistics;
	
	
	@Override
	public void setInicialParameters(Configuration c) {
		// TODO Auto-generated method stub
	}

	@Override
	public void loadProcess(Process p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
	}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}
	
	public MultiLevelQueue getMultilevelQueue() {
		return multilevelQueue;
	}

	public void setMultilevelQueue(MultiLevelQueue multilevelQueue) {
		this.multilevelQueue = multilevelQueue;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public ProcessorCollection getProcessorCollection() {
		return processorCollection;
	}

	public void setProcessorCollection(ProcessorCollection processorCollection) {
		this.processorCollection = processorCollection;
	}

}
