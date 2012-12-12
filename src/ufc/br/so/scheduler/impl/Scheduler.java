package ufc.br.so.scheduler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<Processor,Dispatcher> dispatchers;
	private ProcessorCollection processorCollection;
	private Statistics statistics;

	private Thread schedulerThread;

	// State of the Scheduler
	private boolean running = false;

	public Scheduler(MultiLevelQueue multiLevelQueue,
			List<Processor> listProcessors) {
		this.multilevelQueue = multiLevelQueue;
		this.processorCollection = new ProcessorCollection(listProcessors);
		this.statistics = Statistics.getStatistics();
		schedulerThread = new Thread(this);

		this.dispatchers = new HashMap<Processor,Dispatcher>();

		for (Processor processor : this.processorCollection.getListProcessors()) {
			Dispatcher dispatcher = new Dispatcher(processor);
			// There is only one multiLevelQueue
			dispatcher.addQueueList(multilevelQueue.getProcessorQueue().get(processor));
			dispatchers.put(processor, dispatcher);
		}
	}

	@Override
	public void setInitialParameters(Configuration c) {
		// TODO Auto-generated method stub
	}

	/*
	 * We're loading the process in the Scheduler constructor, there we can pass
	 * by parameter a List of Process.
	 * 
	 * @see
	 * ufc.br.so.scheduler.inter.IScheduler#loadProcess(ufc.br.so.scheduler.
	 * model.processor.Process)
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
		if (!schedulerThread.isAlive()) {
			schedulerThread.start();
		}
		multilevelQueue.start();
		for (Dispatcher dispatcher : dispatchers.values()) {
			dispatcher.start();
		}

	}

	@Override
	public void suspend() {
		this.running = false;
	}

	@Override
	public void resume() {
		if (!running) {
			running = true;
			schedulerThread.notify();
			multilevelQueue.start();
			for (Dispatcher dispatcher : dispatchers.values()) {
				dispatcher.start();
			}
		}
	}

	@Override
	public void stop() throws InterruptedException {
		if (!schedulerThread.isInterrupted()) {
			schedulerThread.interrupt();
		}
		multilevelQueue.stop();
		for (Dispatcher dispatcher : dispatchers.values()) {
			dispatcher.stop();
		}
	}

	@Override
	public void run() {

		while (true) {

			if (!running) {

				try {
					this.wait();
				} catch (InterruptedException e) {
					return;
				}
			}

			if (Thread.currentThread().isInterrupted()) {
				return;
			}
			
			for (Processor processor : processorCollection.getListProcessors()) {
				Queue queue = multilevelQueue.getSelectedQueue(processor);

				if (queue != null) {
					Process processToLoad = dispatchers.get(processor).dispatch(queue,processorCollection);
					if (processToLoad != null) {
						loadProcess(processToLoad);
					}
				}
			}
		}

	}

	@Override
	public Statistics getStatistics() {
		return this.statistics;
	}

	public ProcessorCollection getProcessorCollection() {
		return processorCollection;
	}

	public void setProcessorCollection(ProcessorCollection processorCollection) {
		this.processorCollection = processorCollection;
	}

}
