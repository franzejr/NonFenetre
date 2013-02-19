package ufc.br.so.boot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ufc.br.so.VM.storage.ProcessorQtd;
import ufc.br.so.memory.PageList;
import ufc.br.so.programs.InstalledPrograms;
import ufc.br.so.services.DateTimeService;
import ufc.br.so.services.ServicesRunning;
import ufc.br.so.shell.commandline.Shell;
import ufc.br.so.storage.HardDisk;
import ufc.br.so.storage.RAM;

public class Boot {
	
	private int cpuNum;
	private int ramSize;
	private int HDSize;
	private int pageSize;

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
		RAM.totalRamSize = ramSize;
		
		HDSize = Integer.parseInt(properties.getProperty("HD_SIZE"));
		HardDisk.setHardDiskSize(HDSize);
		
		pageSize = Integer.parseInt(properties.getProperty("PAGE_SIZE"));
		
		System.out.println("CPU NUM: "+cpuNum+" "+"RAM Size: "+ramSize+"mb "+"HD Size: "+HDSize+"mb");
		startMemoryManager(pageSize);
		startServices();
		InstalledPrograms.getInstalledPrograms();
	}
	
	private void startServices(){
		System.out.println("Starting services...");
		
		/*
		 * DateTimeService
		 */
		
		DateTimeService dateTimeService = new DateTimeService();
		ServicesRunning.startService(dateTimeService);
		
		
		/*
		 * MemInfo
		 */
		
		//DateTimeService dateTimeService = new DateTimeService();
		//ServicesRunning.startService(dateTimeService);
		
		
		
	}
	
	public void startMemoryManager(int pageSize){
		System.out.println("Starting the Memory Manager...");
		PageList.startPageList(pageSize);
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