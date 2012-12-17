package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ufc.br.so.kernel.spi.Parameters;
import ufc.br.so.kernel.spi.Report;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;

public class Priority extends ScheduleAlgorithm{
	
	// The priority attribute is the priority of this Queue
	private PriorityQueue<Process> priorityQueue = new PriorityQueue<Process>(10, Process.COMPARE_PRIORITY);
	
	public Priority() {
		super(false);
		result = new ArrayList<Process>();
		identifier = "priority";
		report = new Report();
	}

	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from Priority Algorithm");
		priorityQueue.addAll(source);
		
		int tempo = 0;
		while (!priorityQueue.isEmpty()) {
			report.setReport("Getting the first element from the Priority Queue and putting it in a result list");
			Process p = priorityQueue.poll();
			tempo += p.getExecutionTime();
			p.setTurnAroundTime(tempo);
			p.setWaitingTime(p.getTurnAroundTime() - p.getExecutionTime());
			
			result.add(p);
		}
	}

	@Override
	public Queue<Process> newQueueImpl() {
		return new PriorityQueue<Process>(10, Process.COMPARE_PRIORITY);
	}


}
