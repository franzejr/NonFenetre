package ufc.br.so.shell.commandline.functions;

import java.util.ArrayList;
import java.util.List;

import ufc.br.so.VM.storage.ProcessorQtd;
import ufc.br.so.scheduler.impl.Scheduler;
import ufc.br.so.scheduler.model.StatisticsModule;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.util.XMLHelper;

public class Statistics implements Function {

	@Override
	public void execute() {
		int cpuNum = ProcessorQtd.getProcessorQtd();
		
		List<MultiLevelQueue> listMultilevelQueue = null;
		try{
			//Read the multilevelQueues from the xml file
			listMultilevelQueue = XMLHelper.readMultilevelqueueFile("multilevelQueue.xml");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//Initializes the processors
		List<Processor> listProcessors = new ArrayList<Processor>();
		for(int i=1;i<=cpuNum;i++){
			Processor processor = new Processor(i);
			new Thread(processor).start();
			listProcessors.add(processor);
		}
		listMultilevelQueue.get(0).setProcessorQueue(listProcessors);
		Scheduler scheduler = new Scheduler(listMultilevelQueue.get(0), listProcessors);
		
//		java.util.Queue<Process> processesQueue = listMultilevelQueue.get(0).getListAllQueues().get(0).getListProcesses();
//		
//		while(!processesQueue.isEmpty()){
//			Process process = processesQueue.poll();
//			scheduler.loadProcess(process);
//		}
		
		scheduler.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Printing the Statistics
		System.out.println(StatisticsModule.getStatistics().toString());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(StatisticsModule.getStatistics().toString());

		
	}

}
