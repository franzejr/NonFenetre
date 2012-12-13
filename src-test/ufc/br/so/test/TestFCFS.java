package ufc.br.so.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.queue.algorithm.FCFS;
import ufc.br.so.scheduler.model.queue.algorithm.SJF;
import ufc.br.so.util.XMLHelper;

public class TestFCFS {

	MultiLevelQueue multiLevelQueue;
	List<Process> processes = new ArrayList<Process>();
	FCFS fcfs = new FCFS();

	@Before
	public void setUp() throws Exception {
		List<MultiLevelQueue> listMultilevelQueue = XMLHelper
				.readMultilevelqueueFile("multilevelQueueTest.xml");
		multiLevelQueue = listMultilevelQueue.get(0);

		for (Queue queue : multiLevelQueue.getListAllQueues()) {
			for (Process process : queue.getListProcesses()) {
				processes.add(process);
			}
		}
	}

	@Test
	public void test() {
		fcfs.execute(processes, null);
		String result = "";
		for (Process process : fcfs.getResult()) {
			result += process.toString() + ",";
			System.out.println("***** " + process.toString() + " *****");
			System.out.println("EXECUTION TIME:" + process.getExecutionTime());
			System.out.println("Waiting TIME:" + process.getWaitingTime());
			System.out
					.println("TurnAround TIME:" + process.getTurnAroundTime());
		}
		result = result.length() > 0 ? result.substring(0, result.length() - 1)
				+ "." : result;
		System.out.println("**************");
		System.out.println(" RESULT:" + result);
		assertEquals("RESULT", "P3,P2,P0,P1.", result);
	}

}
