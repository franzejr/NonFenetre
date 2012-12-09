package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import ufc.br.so.kernel.spi.Parameters;
import ufc.br.so.kernel.spi.Report;
import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;
/*
 * Round Robin Algorithm
 * 
 * Round-robin (RR) is one of the simplest scheduling algorithms for processes 
 * in an operating system. As the term is generally used, time slices are assigned 
 * to each process in equal portions and in circular order, handling all processes 
 * without priority (also known as cyclic executive). Round-robin scheduling is simple, 
 * easy to implement, and starvation-free. Round-robin scheduling can also be applied 
 * to other scheduling problems, such as data packet scheduling in computer networks.
 * 
 */
public class RR extends ScheduleAlgorithm{
	/*
	 * The quantum time is a part of time which each process will execute and
	 * in each of these executions the burst time of this process will be decreased.
	 * 
	 */
	private int quantumTime;
	
	public RR(){
		/*
		 * The result will be a list of process, so this process can be repeated, of course, mainly if the
		 * quantumTime is small.
		 */
		super(false);
		result = new ArrayList<Process>();
		identifier = "RR";
		report = new Report();
	}
	
	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from FCFS Algorithm");
		
	}

	public int getQuantumTime() {
		return quantumTime;
	}

	public void setQuantumTime(int quantumTime) {
		this.quantumTime = quantumTime;
	}

	@Override
	public Queue<Process> newQueueImpl() {
		// TODO Auto-generated method stub
		return null;
	}


}
