package ufc.br.so.scheduler.model.processor;

import java.util.List;

import ufc.br.so.scheduler.model.queue.MultiLevelQueue;

public class Processor implements Runnable{

	private List<MultiLevelQueue> listMultiLevelQueues;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
	public float run(Process p){
		return p.getCpuBurst() > 0 ? p.getCpuBurst() : executionTime(p);
	}
	
	public float executionTime(Process p){
		//TODO
		return 0;
	}

	public List<MultiLevelQueue> getListMultiLevelQueues() {
		return listMultiLevelQueues;
	}

	public void setListMultiLevelQueues(List<MultiLevelQueue> listMultiLevelQueues) {
		this.listMultiLevelQueues = listMultiLevelQueues;
	}

}
