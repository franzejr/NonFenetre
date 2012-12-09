package ufc.br.so.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ufc.br.so.scheduler.model.processor.Process;
import ufc.br.so.scheduler.model.queue.MultiLevelQueue;
import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.queue.QueueType;
import ufc.br.so.scheduler.model.queue.ScheduleAlgorithm;

public class XMLHelper {

	public static List<MultiLevelQueue> readMultilevelqueueFile(String filename) throws Exception{
		//Open the xml file and parse it
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File("xml" + File.separator + filename));
		
		List<MultiLevelQueue> listMultilevelQueues = new ArrayList<MultiLevelQueue>();
		
		//Gets the list of multilevelQueues
		NodeList multilevelQueues = doc.getElementsByTagName("multilevelQueue");
		for(int i=0;i<multilevelQueues.getLength();i++){
			Element multilevelQueueElement = (Element) multilevelQueues.item(i);
			MultiLevelQueue multilevelQueue;
			List<Queue> listQueues = new ArrayList<Queue>();
			//Gets the list of Queues inside the multilevelQueue
			NodeList queues = multilevelQueueElement.getChildNodes();
			
			for(int j=0;j<queues.getLength();j++){
				
				Element queueElement = (Element)queues.item(j);
				Queue queue = new Queue();
				
				String scheduleAlgorithm = queueElement.getAttribute("algorithmId");
				String queueType = queueElement.getAttribute("queueType");
				if(scheduleAlgorithm != null && !"".equals(scheduleAlgorithm.trim())){
					QueueType qType = setQueueType(queueType);
					queue.setQueueType(qType);
					queue.setScheduleAlgorithm((ScheduleAlgorithm)Class.forName("ufc.br.so.scheduler.model.queue.algorithm."+scheduleAlgorithm).newInstance());
				}
				 
				
				java.util.Queue<Process> listProcesses = queue.getScheduleAlgorithm().newQueueImpl();
				
				//Gets the list of processes inside the queue
				NodeList processes = queueElement.getChildNodes();
				for(int k=0;k<processes.getLength();k++){
					Element processElement = (Element)processes.item(k);
					Process process = new Process();
					
					//Gets the process cpuBurs if it exists
					String cpuBurst = processElement.getAttribute("executionTime");
					if(cpuBurst != null && !"".equals(cpuBurst.trim())){
						process.setExecutionTime(Integer.valueOf(cpuBurst));
					}
					//Gets the process priority if it exists
					String priority = processElement.getAttribute("priority");
					if(priority != null && !"".equals(priority.trim())){
						process.setPriority(Integer.valueOf(priority));
					}
					//Gets the process arrivalTime if it exists
					String arrivalTime = processElement.getAttribute("arrivalTime");
					if(arrivalTime != null && !"".equals(arrivalTime.trim())){
						process.setArrivalTime(Long.valueOf(arrivalTime));
					}
					//TODO read process' instructions
					listProcesses.add(process);
				}
				queue.setListProcesses(listProcesses);
				listQueues.add(queue);
			}
			
			String multilevelQueueId = multilevelQueueElement.getAttribute("id");
			multilevelQueue = new MultiLevelQueue(multilevelQueueId, listQueues);
			listMultilevelQueues.add(multilevelQueue);
		}
		return listMultilevelQueues;
	}
	
	public static QueueType setQueueType(String queueType){
		
		if(queueType.equals("system")){
			return QueueType.QUEUE_SYSTEM;
		}
		else if(queueType.equals("background")){
			return QueueType.QUEUE_BACKGROUND;
		}
		else if(queueType.equals("batch")){
			return QueueType.QUEUE_BATCH;
		}
		else{
			return QueueType.QUEUE_INTERACTIVE;
		}
	}
	
//	public static void main(String[] args) {
//		try{
//			XMLHelper.readMultilevelqueueFile("multilevelQueue.xml");
//		}
//		catch(Exception e){
//			
//		}
//	}
}
