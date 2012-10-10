package ufc.br.so.boot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ufc.br.so.commandline.Shell;

public class Boot {
	
	private int cpuNum;
	private int ramSize;
	private int HDSize;

	public Boot(){
		getConfig();
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