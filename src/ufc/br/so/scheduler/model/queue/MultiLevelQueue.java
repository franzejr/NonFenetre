package ufc.br.so.scheduler.model.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ufc.br.so.scheduler.model.processor.Processor;

/*
 * A MultiLevelQueue can be a Queue for a specific system from the OS.
 * 
 */
public class MultiLevelQueue implements Runnable {
	
	private String id;
	private List<Queue> listAllQueues;
	private Map<Processor,List<Queue>> processorQueue;
	
	private QueueManager queueManager;
	
	public MultiLevelQueue(String id, List<Queue> listAllQueues){
		this.id = id;
		this.listAllQueues = listAllQueues;
	}
	
	public void setProcessorQueue(List<Processor> processors){
		List<Queue> listAllQueuesTmp = new ArrayList<Queue>(listAllQueues);
		int i = 0, max = processors.size();
		//It's a circle list 
		while(!listAllQueuesTmp.isEmpty()){
			addPair(processors.get(i),listAllQueuesTmp.get(0));
			listAllQueuesTmp.remove(0);
			i++;
			
			if(i >= max){
				i = 0; 
			}
		}
	}
	
	/*
	 * Create a pair(processor and a Queue)
	 */
	public void addPair(Processor processor,Queue queue){
		if(processorQueue.containsKey(processor)){
			processorQueue.get(processor).add(queue);
		}else{
			List<Queue> queueList = new ArrayList<Queue>();
			queueList.add(queue);
			processorQueue.put(processor, queueList);			
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
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

	public Map<Processor,List<Queue>> getProcessorQueue() {
		return processorQueue;
	}

	public void setProcessorQueue(Map<Processor,List<Queue>> processorQueue) {
		this.processorQueue = processorQueue;
	}

	public List<Queue> getListAllQueues() {
		return listAllQueues;
	}

	public void setListAllQueues(List<Queue> listAllQueues) {
		this.listAllQueues = listAllQueues;
	}

}
