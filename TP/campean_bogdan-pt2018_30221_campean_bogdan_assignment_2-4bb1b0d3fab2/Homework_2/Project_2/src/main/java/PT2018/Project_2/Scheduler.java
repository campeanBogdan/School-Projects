package PT2018.Project_2;

import java.util.*;

public class Scheduler {
	private LinkedList<Server> servers;
	private int noOfServers;
	private int maxTasksPerServer;
//	private Strategy strategy;
	
	
	public Scheduler(String noOfServers, String maxTasksPerServer, LinkedList<Server> servers) {
		
		this.noOfServers = Integer.parseInt(noOfServers);
		this.maxTasksPerServer = Integer.parseInt(maxTasksPerServer);
		
		this.servers = servers;
		
//		for (int i = 0; i < this.noOfServers; i++) {
//			servers.add(new Server());
//		}
	}
	
	
//	public void changeStrategy(SelectionPolicy policy) {
//		
//		
//		if (policy == SelectionPolicy.SHORTEST_QUEUE) {
//			strategy = new ConcreteStrategyQueue();
//		}
//		
//		if (policy == SelectionPolicy.SHORTEST_TIME) {
//			strategy = new ConcreteStrategyTime();
//		}
//	}
	
	
	public int getNoOfServers() {
		return noOfServers;
	}
	
	
	public void dispatchTask(Task cl) {
		
	}
	
	
	public synchronized LinkedList<Server> getServers() {
		return servers;
	}

}
