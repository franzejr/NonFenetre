package ufc.br.so.scheduler.model.processor;

import java.util.List;

public class ProcessorCollection{
	
	private List<Processor> listProcessors;
	
	public ProcessorCollection(List<Processor> listProcessors) {
		this.listProcessors = listProcessors;
	}

	public List<Processor> getListProcessors() {
		return listProcessors;
	}

	public void setListProcessors(List<Processor> listProcessors) {
		this.listProcessors = listProcessors;
	}
}
