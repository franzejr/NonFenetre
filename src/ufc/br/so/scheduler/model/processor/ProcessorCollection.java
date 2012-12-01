package ufc.br.so.scheduler.model.processor;

import java.util.List;

import ufc.br.so.scheduler.impl.Scheduler;

public class ProcessorCollection extends Scheduler {
	
	List<Processor> listProcessors;

	public List<Processor> getListProcessors() {
		return listProcessors;
	}

	public void setListProcessors(List<Processor> listProcessors) {
		this.listProcessors = listProcessors;
	}
}
