package ufc.br.so.scheduler.model.queue;

import java.util.List;

import ufc.br.so.scheduler.model.processor.Processor;


public class MultiLevelQueue implements Runnable {

	private List<Processor> listProcessors;
	private List<Queue> listQueues;
	private QueueManager queueManager;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public List<Processor> getListProcessors() {
		return listProcessors;
	}

	public void setListProcessors(List<Processor> listProcessors) {
		this.listProcessors = listProcessors;
	}

	public List<Queue> getListQueues() {
		return listQueues;
	}

	public void setListQueues(List<Queue> listQueues) {
		this.listQueues = listQueues;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

}
