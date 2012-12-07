package ufc.br.so.boot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ufc.br.so.scheduler.impl.Scheduler;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.shell.commandline.Shell;
import ufc.br.so.util.XMLHelper;

public class Boot {
	
	private int cpuNum;
	private int ramSize;
	private int HDSize;

	public Boot(){
		getConfig();
		List<MultiLevelQueue> listMultilevelQueue = null;
		try{
			//Read the multilevelQueues from the xml file
			listMultilevelQueue = XMLHelper.readMultilevelqueueFile("multilevelQueue.xml");
		}
		catch(Exception e){
			//TODO fazer o devido tratamento de exce��es
			e.printStackTrace();
		}
		//Inicializes the processors
		List<Processor> listProcessors = new ArrayList<Processor>();
		for(int i=1;i<=cpuNum;i++){
			listProcessors.add(new Processor(i));
		}
		
		Scheduler scheduler = new Scheduler(listMultilevelQueue, listProcessors);
		new Thread(scheduler).start();
		new Shell();
	}

	private void getConfig(){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("resources/config.cfg"));
		} catch (IOException e) {
			System.out.println("No config.cfg found!");
		}
		cpuNum = Integer.parseInt(properties.getProperty("CPU_NUM"));
		ramSize = Integer.parseInt(properties.getProperty("RAM_SIZE"));
		HDSize = Integer.parseInt(properties.getProperty("HD_SIZE"));
		System.out.println(cpuNum+" "+ramSize+" "+HDSize);
	}
	

	public int getCpuNum() {
		return cpuNum;
	}
	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}
	public int getRamSize() {
		return ramSize;
	}
	public void setRamSize(int ramSize) {
		this.ramSize = ramSize;
	}
	public int getHDSize() {
		return HDSize;
	}
	public void setHDSize(int hDSize) {
		HDSize = hDSize;
	}
}