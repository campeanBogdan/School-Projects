package PT2018.Project_2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	
	private LinkedBlockingQueue<Task> tasks;
	private AtomicInteger totalWaitingPeriodOnServer;
	private int queueIndex;
	private int serverNumber;
	private static int number = 0;
	private int average = 0;
	
	
	public Server() {
		this.tasks = new LinkedBlockingQueue<Task>();
		this.totalWaitingPeriodOnServer = new AtomicInteger(0);
		this.queueIndex = 0;
		this.serverNumber = number;
		number++;
	}
	
	
	public static int getNoOfServer() {
		return number;
	}
	
	
	public static void decrementServerQueue(LinkedList<Server> servers, int index) {
		int i = 0;
		
		Iterator<Server> it = servers.iterator();
		
		while (i < index && it.hasNext()) {
			Server sv = it.next();
			i++;
		}
		
		servers.get(i-1).queueIndex--;
	}
	
	
	public static int getAllWaitingPeriod(LinkedList<Server> servers) {
		int k = 0;
		
		Iterator<Server> it = servers.iterator();
		
		while (it.hasNext()) {
			Server sv = it.next();
			
			k += sv.getTotalWaitingPeriodOnServer();
		}
		
		return k;
	}
	
	
	public static int getPeakHour(LinkedList<Server> servers) {
		int k = 0;
		
		Iterator<Server> it = servers.iterator();
		
		while (it.hasNext()) {
			Server sv = it.next();
			
			k += sv.getQueueIndex();
		}
		
		return k;
	}
	
	
	public synchronized void decQueueIndex() {
		this.queueIndex--;
	}
	
	
	public synchronized void addTask(Task task) {
		
	//introduc un task nou => timpul total de asteptare pe server este ...
		task.setTaskFinsihTime(this.totalWaitingPeriodOnServer.get());
		this.tasks.add(task);
		this.totalWaitingPeriodOnServer.set(this.totalWaitingPeriodOnServer.get() + task.getProcessingTime());
		this.queueIndex++;
		
		System.out.println("serverNumber: " + serverNumber);
	}
	
	
	public void run() {
		
		try {
			
			while (!tasks.isEmpty()) {
				
				//iau urmatorul task din coada
				Task currentTask = tasks.take();
				
				while (currentTask.getProcessingTime() > 0) {
					
				// decrementez timpul total de asteptare pe server si timpul de procesare a unui task la fiecare secunda
					currentTask.decProcessingTime();
					if (this.totalWaitingPeriodOnServer.get() > 0)
						this.totalWaitingPeriodOnServer.decrementAndGet();
					
//					System.out.println("current task: " + currentTask.getTaskNumber() + " finish time: " + currentTask.getProcessingTime() + " waiting time/server: " + this.totalWaitingPeriodOnServer);
					Thread.sleep(1000);
				}
				
				decQueueIndex();
			}
			
		} catch (InterruptedException e) {
		}

	}
	
	
	public int getAverageWaitingTimePerServer() {
		int average = 0;
		
		Iterator<Task> it = tasks.iterator();
		
		while (it.hasNext()) {
			Task task = it.next();
			
			average += task.getAverageTask();
		}
		
		if (this.queueIndex > 0)
			return average / this.queueIndex;
		
		return 0;
	}

	
	public static void decNumberOfServers() {
		number--;
	}
	
	
	public synchronized int getQueueIndex() {
		return this.queueIndex;
	}
	
	public synchronized int getTotalWaitingPeriodOnServer() {
		return this.totalWaitingPeriodOnServer.intValue();
	}

}
