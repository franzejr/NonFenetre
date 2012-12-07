package ufc.br.so.scheduler.impl;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.scheduler.inter.IScheduler;
import ufc.br.so.scheduler.model.Configuration;
import ufc.br.so.scheduler.model.Dispatcher;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
/*
 * Escalonador
 */
public class Scheduler implements IScheduler, Runnable {

	private List<MultiLevelQueue> listMultilevelQueue;
	private List<Dispatcher> listDispatcher;
	private ProcessorCollection processorCollection;
	private Statistics statistics;
	
	public Scheduler(List<MultiLevelQueue> listMultilevelQueue, List<Processor> listProcessors) {
		this.listMultilevelQueue = listMultilevelQueue;
		this.processorCollection = new ProcessorCollection(listProcessors);
		this.listDispatcher = new ArrayList<Dispatcher>();
	}
	
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
		//For each Processor
		for(Processor processor:this.processorCollection.getListProcessors()){
			//Instanciate a dispatcher
			Dispatcher dispatcher = new Dispatcher(processor);
			//Add its queues
			for(MultiLevelQueue multilevelQueue:listMultilevelQueue){
				dispatcher.addQueueList(multilevelQueue.getProcessorQueue().get(processor));
			}
			this.listDispatcher.add(dispatcher);
			new Thread(dispatcher).start();
		}
	}
	
	public ProcessorCollection getProcessorCollection() {
		return processorCollection;
	}

	public void setProcessorCollection(ProcessorCollection processorCollection) {
		this.processorCollection = processorCollection;
	}

	public List<MultiLevelQueue> getListMultilevelQueue() {
		return listMultilevelQueue;
	}

	public void setListMultilevelQueue(List<MultiLevelQueue> listMultilevelQueue) {
		this.listMultilevelQueue = listMultilevelQueue;
	}

	public List<Dispatcher> getListDispatcher() {
		return listDispatcher;
	}

	public void setListDispatcher(List<Dispatcher> listDispatcher) {
		this.listDispatcher = listDispatcher;
	}

}
