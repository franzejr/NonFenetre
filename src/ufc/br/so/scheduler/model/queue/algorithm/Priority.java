package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import ufc.br.so.kernel.spi.Parameters;
import ufc.br.so.kernel.spi.Report;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;

public class Priority extends ScheduleAlgorithm{
	
	// TODO must be the priority attribute the priority of this Queue
	private PriorityQueue<Process> priorityQueue = new PriorityQueue<Process>();
	
	public Priority() {
		result = new ArrayList<Process>();
		identifier = "priority";
		report = new Report();
	}

	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from Priority Algorithm");
		priorityQueue.addAll(source);
		
		while (!priorityQueue.isEmpty()) {
			report.setReport("Getting the first element from the Priority Queue and putting it in a result list");
			result.add(priorityQueue.poll());
		}
	}


}
