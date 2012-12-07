package ufc.br.so.scheduler.model.queue.algorithm;

import java.util.List;

import ufc.br.so.scheduler.model.queue.Queue;
import ufc.br.so.scheduler.model.queue.QueueManager;
import ufc.br.so.scheduler.model.queue.QueueType;

public class Aging implements QueueManager {

	public static Queue verifyQueueToRun(List<Queue> allQueues) {
		
		for (Queue queue : allQueues) {
			
			switch (queue.getQueuePriority()) {
				case QUEUE_INTERACTIVE:
					if (queue.getTimesRunned() < 10) {
						queue.setTimesRunned(queue.getTimesRunned() + 1);
						return queue;
					}
					break;
				case QUEUE_SYSTEM:
					if (queue.getTimesRunned() < 5) {
						queue.setTimesRunned(queue.getTimesRunned() + 1);
						return queue;
					}
					break;
				case QUEUE_BACKGROUND:
					if (queue.getTimesRunned() < 2) {
						queue.setTimesRunned(queue.getTimesRunned() + 1);
						return queue;
					}
	
					break;
				case QUEUE_BATCH:
					if (queue.getTimesRunned() < 1) {
						queue.setTimesRunned(queue.getTimesRunned() + 1);
						return queue;
					}
					break;
				}
		}
		
		Queue highPriority = null;
		for (Queue q : allQueues) {
				q.setTimesRunned(0);
			if (q.getQueuePriority() == QueueType.QUEUE_INTERACTIVE) {
				highPriority = q;
			}

		}
		return highPriority;
	}
}
