package ufc.br.so.scheduler.model;

import java.util.List;

import ufc.br.so.scheduler.inter.ThreadManagement;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.Queue;

public class Dispatcher implements ThreadManagement {

	private Processor processor;
	private Thread dispatcherThread;
	private List<Queue> listQueues;

	private boolean running = false;

	public Dispatcher() {
		dispatcherThread = new Thread(this);
	}

	public void addQueue(Queue queue) {
		this.listQueues.add(queue);
	}

	private Processor getIdleProcessor(final List<Processor> all,
			final Process selectedProcess) {
		Processor idle = null;
		float run1, run2;
		for (Processor processor : all) {
			if (idle == null) {
				idle = processor;
			} else {
				run1 = idle.run(selectedProcess);
				run2 = processor.run(selectedProcess);
				if (idle.getExecutingProcess() != null
						&& processor.getExecutingProcess() == null) {
					idle = processor;
				}
				if ((idle.getExecutingProcess() == null && processor
						.getExecutingProcess() == null)
						|| (idle.getExecutingProcess() != null && processor
								.getExecutingProcess() != null)) {
					if (run2 < run1) {
						idle = processor;
					}
				}
			}
		}
		return idle;
	}

	public void addQueueList(List<Queue> listQueues) {
		this.listQueues.addAll(listQueues);
	}

	/*
	 * Select a process from queue Starting iterating over the list of
	 * queues,get the first process, doing it, this process will be removed from
	 * this Queue.
	 */
	public Process dispatch(final Queue selectedQueue,
			final ProcessorCollection allProcessors) {
		Process selectedProcess = selectedQueue.selectProcess();

		if (selectedProcess != null) {
			getIdleProcessor(allProcessors.getListProcessors(), selectedProcess)
					.setExecutingProcess(selectedProcess);
		}
		return selectedProcess;
	}

	@Override
	public void run() {

	}

	@Override
	public void start() {
		if (!dispatcherThread.isAlive()) {
			dispatcherThread.start();
		} else if (!running) {
			dispatcherThread.notify();
		}
		running = true;

	}

	@Override
	public void stop() throws InterruptedException {
		if (running && !dispatcherThread.isInterrupted()) {
			dispatcherThread.interrupt();
			running = false;
		}

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
