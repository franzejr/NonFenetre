package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ufc.br.so.kernel.spi.Parameters;
import ufc.br.so.kernel.spi.Report;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;

/*
 * This algorithms is called First-Come-First-Served.
 * The first process to arrive is the first process to be served.
 * Basically, it's a priority queue in the Arrival Time.
 * This is a implementation of a non-preemptive algorithm.
 * 
 */
public class FCFS extends ScheduleAlgorithm {

	// The arrival time is the priority of this Queue
	private PriorityQueue<Process> priorityQueue = new PriorityQueue<Process>(10, Process.COMPARE_ARRIVALTIME);

	public FCFS() {
		super(false);
		result = new ArrayList<Process>();
		identifier = "FCFS";
		report = new Report();
	}

	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from FCFS Algorithm");
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
		return new PriorityQueue<Process>(10, Process.COMPARE_ARRIVALTIME);
	}
}
