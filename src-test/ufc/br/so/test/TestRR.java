/**
 * 
 */
package ufc.br.so.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.queue.algorithm.RR;
import ufc.br.so.scheduler.model.queue.algorithm.SJF;
import ufc.br.so.util.XMLHelper;

/**
 * @author franzejr
 *
 */
public class TestRR {
	
	MultiLevelQueue multiLevelQueue;
	List<Process> processes = new ArrayList<Process>();
	RR rr = new RR();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<MultiLevelQueue> listMultilevelQueue = XMLHelper.readMultilevelqueueFile("multilevelQueueTest.xml");
		multiLevelQueue = listMultilevelQueue.get(0);
		
		for(Queue queue : multiLevelQueue.getListAllQueues()){
			for(Process process : queue.getListProcesses()){
				processes.add(process);
			}
		}
	}

	/**
	 * Test method for {@link ufc.br.so.scheduler.model.queue.algorithm.RR#execute(java.util.List, ufc.br.so.kernel.spi.Parameters)}.
	 */
	@Test
	public void testExecuteListOfProcessParameters() {
		rr.setQuantumTime(1);
		rr.execute(processes, null);
		String result = "";
		for(Process process : rr.getResult()){
			result += process.toString() +",";
			System.out.println("***** "+process.toString()+" *****");
			System.out.println("EXECUTION TIME:"+process.getExecutionTime());
			System.out.println("Waiting TIME:"+process.getWaitingTime());
			System.out.println("TurnAround TIME:"+process.getTurnAroundTime());
		}
		result = result.length() > 0 ? result.substring(0,result.length() - 1) + "." : result;
		System.out.println("**************");
		System.out.println(" RESULT:"+result);
		assertEquals("RESULT", "P3,P2,P0,P1.", result);
	}

}
