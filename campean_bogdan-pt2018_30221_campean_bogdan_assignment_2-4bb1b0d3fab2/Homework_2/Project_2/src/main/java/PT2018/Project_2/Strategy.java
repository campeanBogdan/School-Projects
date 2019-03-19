package PT2018.Project_2;

import java.util.Iterator;
import java.util.LinkedList;



public class Strategy {
	
	
	public int getQueue(LinkedList<Server> servers, SelectionPolicy sp) {
		int queue = 0;
		
		
		if (servers.isEmpty()) {
			System.out.println("A");
			return 0;
		}
		
		
		if (sp == SelectionPolicy.SHORTEST_QUEUE) {
			int min = 20, i = 0;
			
			Iterator<Server> it = (Iterator<Server>) servers.iterator();
			
			while (it.hasNext()) {
				Server sv = it.next();
				
				if (sv.getQueueIndex() < min) {
					min = sv.getQueueIndex();
					queue = i;
				}
				
				i++;
			}
		}
		
		
		if (sp == SelectionPolicy.SHORTEST_TIME) {
			int min = 1000, i = 0;
			
			Iterator<Server> it = (Iterator<Server>) servers.iterator();
			
			while (it.hasNext()) {
				Server sv = it.next();
				
				if (sv.equals(null)) min = 0;
				
				if (sv.getTotalWaitingPeriodOnServer() < min) {
					min = sv.getTotalWaitingPeriodOnServer();
					queue = i;
				}
				
				i++;
			}
		}
		
		
		return queue;
	}

}
