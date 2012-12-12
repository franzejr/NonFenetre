package ufc.br.so.scheduler.model.processor;

import ufc.br.so.scheduler.inter.ThreadManagement;
import ufc.br.so.scheduler.model.Statistics;
import ufc.br.so.scheduler.model.processor.Process;

public class Processor implements ThreadManagement {

	private long id;
	private String description;
	private Statistics statistics;
	private Process executingProcess;
	private Thread processorThread;
	private float effectiveExecutingTime;
	
	private int  numberProcessesExecuted=0;

	public Processor(long id) {
		this.id = id;
	}

	public Processor(final String description) {
		this.description = description;
		processorThread = new Thread(this);
		processorThread.start();
		statistics = Statistics.getStatistics();
	}
	
	public float getEffectiveExecutingTime() {
		return effectiveExecutingTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Process getExecutingProcess() {
		return executingProcess;
	}

	public void setExecutingProcess(Process executingProcess) {
		this.executingProcess = executingProcess;
	}

	@Override
	public void stop() throws InterruptedException {
		// Never stop
	}

	@Override
	public void start() {
		
	}
	
	public float run(final Process p){
		if(p.getExecutionTime() > 0){
			return p.getExecutionTime();
		}else{
			try{
				return (float) (Math.random() * 1000);
			}catch (Exception e) {
				return 100F;
			}
		}
	}

	@Override
	public void run() {
		while(true){
			if(executingProcess == null){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				int timeExecuted = 0;
				synchronized (executingProcess) {
					numberProcessesExecuted++;
					timeExecuted = executingProcess.execute();
					effectiveExecutingTime += timeExecuted;
					executingProcess.setExecutionTime(timeExecuted);
					
					executingProcess.setRunning(false);
					executingProcess = null;
					
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return description;
	}

	public int getNumberProcessesExecuted() {
		return numberProcessesExecuted;
	}

	public void setNumberProcessesExecuted(int numberProcessesExecuted) {
		this.numberProcessesExecuted = numberProcessesExecuted;
	}

}
