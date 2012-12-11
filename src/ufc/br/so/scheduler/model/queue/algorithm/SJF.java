package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ufc.br.so.kernel.spi.Parameters;
import ufc.br.so.kernel.spi.Report;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;

/*
 * ShortestJobFirst is an algorithm of scheduling which get the process with the
 * minimum CPU BURST. This implementation is for a non preemptive algorithm.
 * 
 */
public class SJF extends ScheduleAlgorithm {
	
	//The CPU Burst is the priority of this queue
	private PriorityQueue<Process> priorityQueue = new PriorityQueue<Process>(10, Process.COMPARE_CPUBURST);
	
	public SJF(){
		//SJF result is a List of process
		super(false);
		result = new  ArrayList<Process>();
		identifier = "SJF";
		report = new Report();
	}
	
	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from SJF Algorithm");
		priorityQueue.addAll(source);
		
		while(!priorityQueue.isEmpty()){
			report.setReport("Getting the first element from the BurstTime Queue and putting it in a result list");
			result.add(priorityQueue.poll());
		}
	}

	@Override
	public Queue<Process> newQueueImpl() {
		return new PriorityQueue<Process>(10, Process.COMPARE_CPUBURST);
	}

}
