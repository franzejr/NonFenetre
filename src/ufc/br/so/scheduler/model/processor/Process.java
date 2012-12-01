package ufc.br.so.scheduler.model.processor;

public class Process {

	private float cpuBurst;
	
	public Process(){}
	
	public Process(float cpuBurst){
		this.cpuBurst = cpuBurst;
	}
	
	public float getCpuBurst() {
		return cpuBurst;
	}

	public void setCpuBurst(float cpuBurst) {
		this.cpuBurst = cpuBurst;
	}
}
