package ufc.br.so.scheduler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.processor.ProcessorCollection;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.processor.Process;

/*
 * General Statistics
 * 
 */
public class StatisticsModule {

	private static StatisticsModule statistics = null;

	private List<StatisticTuples> statisticTuples;

	private ProcessorCollection processorCollection;

	private Map<Queue, List<Process>> queuesProcess = new HashMap<Queue, List<Process>>();

	private Set<Process> processes = new HashSet<Process>();

	public static StatisticsModule getStatistics() {
		if (statistics == null) {
			statistics = new StatisticsModule();
		}
		return statistics;
	}

	private StatisticsModule() {

	}

	/*
	 * Main method to generate statistics
	 */
	private void generateStatistics() {
		statisticTuples = new ArrayList<StatisticTuples>();
		List<Processor> processors = processorCollection.getListProcessors();
		
		//Number of processors
		statisticTuples.add(new StatisticTuples("number_processors", Integer.toString(processors.size())));
		
		//Number of processors executed
		for(Processor processor : processors){
			statisticTuples.add(new StatisticTuples("number_processes_executed P"+processor.getId(), Integer.toString(processor.getNumberProcessesExecuted())));
		}
		
		
		//Total time of processing
		Float totalTimeProcessing = 0f;
		for(Processor processor : processors){
			totalTimeProcessing += processor.getEffectiveExecutingTime();
		}
		statisticTuples.add(new StatisticTuples("total_time_processing", totalTimeProcessing.toString()));

		if(processes != null && !processes.isEmpty()){
			Map<Queue,Integer> queuesNumberProcesses = new HashMap<Queue, Integer>();
			for(Entry<Queue,List<Process>> entry : queuesProcess.entrySet()){
				queuesNumberProcesses.put(entry.getKey(), entry.getValue().size());
			}
			statisticTuples.add(new StatisticTuples("quantity_process_by_queue", Integer.toString(queuesNumberProcesses.size())));
		
			//Average waiting Time
			Float avgWaitingTime = 0f;
			for(Process process : processes){
				avgWaitingTime += process.getWaitingTime();
			}
			avgWaitingTime = avgWaitingTime / processes.size();
			statisticTuples.add(new StatisticTuples("average_waitingTime", avgWaitingTime.toString() ));
		
			//Average TurnAround
			Float avgTurnAround = 0f;
			for(Process process : processes){
				avgTurnAround += process.getWaitingTime() + process.getExecutionTime();
			}
			avgTurnAround = avgTurnAround / processes.size();
			statisticTuples.add(new StatisticTuples("average_turnAround", avgTurnAround.toString() ));
		
			//Average Response Time
			Float avgResponseTime = 0f;
			for (Process process : processes) {
				avgResponseTime += process.getWaitingTimeForFirstResponse();
			}
			avgResponseTime = avgResponseTime / processes.size();
			statisticTuples.add(new StatisticTuples("avg_response_time", avgResponseTime.toString()));
		}
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
		String string = "\n--- STATISTICS BEGIN ---\n";
		for (StatisticTuples tuple : tuples) {
			string += tuple.toString() + "\n";
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
