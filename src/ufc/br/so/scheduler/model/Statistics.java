package ufc.br.so.scheduler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.processor.Process;

/*
 * General Statistics
 * 
 */
public class Statistics {

	private static Statistics statistics = null;

	private List<StatisticTuples> statisticTuples;

	private ProcessorCollection processorCollection;

	private Map<Queue, List<Process>> queuesProcess = new HashMap<Queue, List<Process>>();

	private Set<Process> processes = new HashSet<Process>();

	public static Statistics getStatistics() {
		if (statistics == null) {
			statistics = new Statistics();
		}
		return statistics;
	}

	private Statistics() {

	}

	/*
	 * Main method to generate statistics
	 */
	private void generateStatistics() {

	}

	public List<StatisticTuples> getStatisticTuples() {
		generateStatistics();
		return statisticTuples;
	}

	public void setStatisticTuples(List<StatisticTuples> statisticTuples) {
		this.statisticTuples = statisticTuples;
	}
	
	public void addProcessQueue(Queue queue, Process process){
		List<Process> processes = queuesProcess.get(queue);
		if(processes == null){
			processes = new ArrayList<Process>();
			queuesProcess.put(queue, processes);
		}
		processes.add(process);
	}

	@Override
	public String toString() {
		List<StatisticTuples> tuples = getStatisticTuples();
		String string = "\n--- STATISTICS BEGIN ---";
		for (StatisticTuples tuple : tuples) {
			string += tuple.toString();
		}
		string += "\n--- STATISTICS END ---";
		return string;
	}

	public Set<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(Set<Process> processes) {
		this.processes = processes;
	}

	public void setProcessorCollection(ProcessorCollection processorCollection) {
		this.processorCollection = processorCollection;
	}

	public void addProcess(Process process) {
		processes.add(process);
		
	}

}
