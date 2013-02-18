package ufc.br.so.boot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ufc.br.so.VM.storage.ProcessorQtd;
import ufc.br.so.scheduler.impl.Scheduler;
import ufc.br.so.scheduler.model.StatisticsModule;
import ufc.br.so.scheduler.model.processor.Processor;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.shell.commandline.Shell;
import ufc.br.so.storage.HardDisk;
import ufc.br.so.storage.RAM;
import ufc.br.so.util.XMLHelper;

public class Boot {
	
	private int cpuNum;
	private int ramSize;
	private int HDSize;

	public Boot(){
		System.out.println("Starting the Operating System...");
		getConfig();
		new Shell();
	}

	private void getConfig(){
		System.out.println("Getting configs...");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("resources/config.cfg"));
		} catch (IOException e) {
			System.out.println("No config.cfg found!");
		}
		
		cpuNum = Integer.parseInt(properties.getProperty("CPU_NUM"));
		ProcessorQtd.setProcessorQtd(cpuNum);
		ramSize = Integer.parseInt(properties.getProperty("RAM_SIZE"));
		RAM.setRamSize(ramSize);
		HDSize = Integer.parseInt(properties.getProperty("HD_SIZE"));
		HardDisk.setHardDiskSize(HDSize);
		System.out.println("CPU NUM: "+cpuNum+" "+"RAM Size: "+ramSize+" "+"HD Size: "+HDSize);
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