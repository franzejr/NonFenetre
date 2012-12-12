package ufc.br.so.scheduler.model.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ufc.br.so.scheduler.inter.ThreadManagement;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.algorithm.Aging;

/*
 * A MultiLevelQueue can be a Queue for a specific system from the OS.
 * 
 */
public class MultiLevelQueue implements ThreadManagement {

	private String id;
	private List<Queue> listAllQueues;
	private Map<Processor, List<Queue>> processorQueue;
	private QueueManager queueManager;

	private Thread multiQueueThread;
	private boolean running = false;
	
	public MultiLevelQueue(
			Map<QueueType, ScheduleAlgorithm> processQueuesAlgorithms) {
		queueManager = new Aging();
		listAllQueues = new ArrayList<Queue>(processQueuesAlgorithms.size());
		for (Entry<QueueType, ScheduleAlgorithm> entry : processQueuesAlgorithms
				.entrySet()) {
			listAllQueues.add(new Queue(entry.getKey(), entry.getValue()));
		}
		this.multiQueueThread = new Thread(this);
		processorQueue = new HashMap<Processor, List<Queue>>();

	}

	public MultiLevelQueue(String id, List<Queue> listAllQueues) {
		this.id = id;
		this.listAllQueues = listAllQueues;
		this.multiQueueThread = new Thread(this);
		processorQueue = new HashMap<Processor, List<Queue>>();
	}

	public void setProcessorQueue(List<Processor> processors) {
		List<Queue> listAllQueuesTmp = new ArrayList<Queue>(listAllQueues);
		for(Processor processor : processors){
			processorQueue.put(processor, new ArrayList<Queue>());
		}
		int i = 0, max = processors.size();
		// It's a circle list
		while (!listAllQueuesTmp.isEmpty()) {
			addPair(processors.get(i), listAllQueuesTmp.get(0));
			listAllQueuesTmp.remove(0);
			i++;

			if (i >= max) {
				i = 0;
			}
		}
	}

	/*
	 * Create a pair(processor and a Queue)
	 */
	public void addPair(Processor processor, Queue queue) {
		if (processorQueue.containsKey(processor)) {
			processorQueue.get(processor).add(queue);
		} else {
			List<Queue> queueList = new ArrayList<Queue>();
			queueList.add(queue);
			processorQueue.put(processor, queueList);
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	@Override
	public void start() {
		if (!multiQueueThread.isAlive()) {
			multiQueueThread.start();
		} else if (!running) {
			multiQueueThread.notify();
		}
		running = true;

	}

	@Override
	public void stop() throws InterruptedException {
		if (running && !multiQueueThread.isInterrupted()) {
			multiQueueThread.interrupt();
			running = false;
		}

	}

	private synchronized Queue selectQueue(Processor processor) {
		List<Queue> queuesThatHaveProcess = new ArrayList<Queue>();
		// Verify if the list has process
		for (Queue queue : processorQueue.get(processor)) {
			
			if (queue.getListProcesses().size() > 0) {
				queuesThatHaveProcess.add(queue);
			}
		}
		// pega a fila da rodada, utilizando o Aging
		queuesThatHaveProcess = orderQueueByPriority(queuesThatHaveProcess);
		
		return Aging.verifyQueueToRun(queuesThatHaveProcess);
	}

	public synchronized Queue getSelectedQueue(Processor processor) {
		return selectQueue(processor);
	}

	private List<Queue> orderQueueByPriority(final List<Queue> allQueues) {
		Queue aux = null;
		Object[] auxVector = allQueues.toArray();
		for (int i = 0; i < auxVector.length; i++) {
			for (int j = i; j < auxVector.length; j++) {
				if (((Queue) auxVector[i]).getQueuePriority().getPriority() > ((Queue) auxVector[j])
						.getQueuePriority().getPriority()) {
					aux = (Queue) auxVector[i];
					auxVector[i] = auxVector[j];
					auxVector[j] = aux;
				}
			}
		}
		allQueues.clear();
		for (int i = 0; i < auxVector.length; i++) {
			allQueues.add((Queue) auxVector[i]);
		}

		return allQueues;

	}

	public Queue getQueueByPriority(final QueueType priority) {
		Queue aux = null;
		for (Queue queue : listAllQueues) {
			if (queue.getQueuePriority() == priority) {
				aux = queue;
			}
		}
		return aux;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Processor, List<Queue>> getProcessorQueue() {
		return processorQueue;
	}

	public void setProcessorQueue(Map<Processor, List<Queue>> processorQueue) {
		this.processorQueue = processorQueue;
	}

	public List<Queue> getListAllQueues() {
		return listAllQueues;
	}

	public void setListAllQueues(List<Queue> listAllQueues) {
		this.listAllQueues = listAllQueues;
	}

}