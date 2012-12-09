package ufc.br.so.scheduler.model.processor;

import java.util.Comparator;

import ufc.br.so.scheduler.model.queue.ProcessType;

/*
 * A process is an instance of a computer program that is being executed. 
 * It contains the program code and its current activity.
 * 
 */
public class Process {

	private long arrivalTime;
	private int priority;
	private long turnAroundTime;
	private long waitingTime;
	private long responseTime;
	private Integer remainingTime;
	private Integer executionTime;
	private ProcessType typeOfProcess;

	private Integer timeQuantum;

	private float totalWaitingTime = 0f;
	private float waitingTimeForFirstResponse = 0f;
	private boolean isRunning;

	public Process() {
	}

	public Process(Integer executionTime, int priority, long arrivalTime) {
		this.executionTime = executionTime;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
	}

	public Process(Integer executionTime) {
		this.executionTime = executionTime;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void addWaitingTime(float waitingTime) {
		if (remainingTime == null || remainingTime == getExecutionTime()) {
			waitingTimeForFirstResponse += waitingTime;
		}
		this.totalWaitingTime += waitingTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
		this.remainingTime = executionTime;
		
		if (this.executionTime == null) {
			this.executionTime = 0;
		}
	}
	
	private int generateCPUBurstTime() {
		return (int) (10 * Math.random()); 
	}

	public int execute() {
		int burstTime = generateCPUBurstTime();
		int timeExecuting = 0;
		if (remainingTime == null) {
			timeExecuting = burstTime;
			executionTime += burstTime;

		} else {
			// If the process has a quantum
			if (getTimeQuantum() != null) {
				int min = Math.min(getTimeQuantum(), burstTime);
				timeExecuting = burstTime;
				if (remainingTime > burstTime) {
					remainingTime = remainingTime - burstTime;
				} else {
					remainingTime = 0;
				}
			} else {
				timeExecuting = remainingTime;
				remainingTime = 0;
			}
		}
		return timeExecuting;
	}

	/*
	 * Comparator to CPUBurst(executionTime)
	 */
	public static Comparator<Process> COMPARE_CPUBURST = new Comparator<Process>() {

		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getExecutionTime() > p2.getExecutionTime()) {
				return 1;
			} else if (p1.getExecutionTime() < p2.getExecutionTime()) {
				return -1;
			}
			return 0;
		}

	};
	/*
	 * Comparator to ArrivalTime
	 */
	public static Comparator<Process> COMPARE_ARRIVALTIME = new Comparator<Process>() {

		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getArrivalTime() > p2.getArrivalTime()) {
				return 1;
			} else if (p1.getArrivalTime() < p2.getArrivalTime()) {
				return -1;
			}
			return 0;
		}

	};

	/*
	 * Comparator to Priority
	 */
	public static Comparator<Process> COMPARE_PRIORITY = new Comparator<Process>() {

		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getPriority() > p2.getPriority()) {
				return 1;
			} else if (p1.getPriority() < p2.getPriority()) {
				return -1;
			}
			return 0;
		}

	};

	public float getWaitingTimeForFirstResponse() {
		return waitingTimeForFirstResponse;
	}

	public float getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getTurnAroundTime() {
		return turnAroundTime;
	}

	public void setTurnAroundTime(long turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Integer timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public boolean isFinished() {
		return remainingTime != null && remainingTime == 0;
	}

	public ProcessType getTypeOfProcess() {
		return typeOfProcess;
	}

	public void setTypeOfProcess(ProcessType typeOfProcess) {
		this.typeOfProcess = typeOfProcess;
	}

}
