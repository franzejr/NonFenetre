package ufc.br.so.scheduler.model.processor;


/*
 * A process is an instance of a computer program that is being executed. 
 * It contains the program code and its current activity.
 * 
 */
public class Process implements Comparable {

	private float cpuBurst;
	private long arrivalTime;
	private int priority;

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


	@Override
	public int compareTo(Object o) {
		Process process = (Process) o;

		if (this.cpuBurst > process.cpuBurst) {
			return 1;
		} else if (this.cpuBurst < process.cpuBurst) {
			return -1;
		}

		return 0;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
