package ufc.br.so.scheduler.model.processor;

import java.util.Comparator;


/*
 * A process is an instance of a computer program that is being executed. 
 * It contains the program code and its current activity.
 * 
 */
public class Process {

	private float cpuBurst;
	private long arrivalTime;
	private int priority;
	private long turnAroundTime;
	private long waitingTime;
	private long responseTime;

	public Process() {
	}

	public Process(float cpuBurst) {
		this.cpuBurst = cpuBurst;
	}

	public float getCpuBurst() {
		return cpuBurst;
	}

	public void setCpuBurst(float cpuBurst) {
		this.cpuBurst = cpuBurst;
	}
	
	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public static Comparator<Process> COMPARE_CPUBURST = new Comparator<Process>() {

		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getCpuBurst() > p2.getCpuBurst()) {
				return 1;
			} else if (p1.getCpuBurst() < p2.getCpuBurst()) {
				return -1;
			}
			return 0;
		}
		
	};
	
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

}
