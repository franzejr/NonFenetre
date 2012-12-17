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
public class RR extends ScheduleAlgorithm {
	/*
	 * The quantum time is a part of time which each process will execute and in
	 * each of these executions the burst time of this process will be
	 * decreased.
	 */
	private int quantumTime;

	public RR() {
		/*
		 * The result will be a list of process, so this process can be
		 * repeated, of course, mainly if the quantumTime is small.
		 */
		super(false);
		result = new ArrayList<Process>();
		identifier = "RR";
		report = new Report();
	}

	@Override
	public void execute(List<Process> source, Parameters parameters) {
		report.setReport("Starting the execute method from RR Algorithm");
		List<Process> queueCopy = new ArrayList<Process>();

		for (Process p : source) {
			queueCopy.add(p);
		}

		int i = 0;
		int tempo = 0;
		while (source.size() > 0) {
			Process process = source.get(i);
			if (process.getExecutionTime() <= 0) {

				source.remove(process);

				// setting the turnaround time
				for (Process pCopy : queueCopy) {
					if (pCopy.getIdentifier() == process.getIdentifier()) {
						pCopy.setTurnAroundTime(tempo);
					}
				}

			}
			Process newProcess = process.clone();

			newProcess.setExecutionTime(quantumTime);
			process.setExecutionTime(process.getExecutionTime() - quantumTime);
			tempo += quantumTime;
			result.add(newProcess);

			i++;
			if (i == source.size()) {
				i = 0;
			}
		}

		// Setting the turn around time in each of the processes
		for (Process pCopy : queueCopy) {
			for (Process pFinal : result) {
				if (pCopy.getIdentifier() == pFinal.getIdentifier()) {
					pFinal.setTurnAroundTime(pCopy.getTurnAroundTime());
					pFinal.setWaitingTime(pCopy.getTurnAroundTime() - pCopy.getExecutionTime());
				}
			}
		}

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
