package PT2018.Project_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;

public class SimulationManager implements Runnable {

	public int timeLimit;
	public int maxProcessingTine;
	public int minProcessingTime;
	public int numberOfServers;
	private static int timpScurs;
	public static SelectionPolicy sp = SelectionPolicy.SHORTEST_TIME;
	private String averageWaitingTime = new String();
	private int maxAverageWaitingTime = 0;
	private int averageWaitingTimeI = 0;
	private String peakHour = new String();
	private int maxPeakHour = 0;
	private static int ok = 0;
	
	private static Scheduler scheduler;
	private SimulationFrame frame;
	private static Strategy strategy;
	private static LinkedList<Server> servers;
	private static SimulationFrame simFrame = new SimulationFrame();
	
	
	
	public SimulationManager() {
		this.strategy = new Strategy();
		 servers = new LinkedList<Server>();
		timeLimit = 0;
		maxProcessingTine = 0;
		minProcessingTime = 0;
		numberOfServers = 0;
		timpScurs = 0;
	}
	
	public void setSimulationManager(String[] args) {
		this.timeLimit = Integer.parseInt(args[0]);
		this.minProcessingTime = Integer.parseInt(args[1]);
		this.maxProcessingTine = Integer.parseInt(args[2]);
		this.numberOfServers = Integer.parseInt(args[3]);
		
		scheduler = new Scheduler(args[3], "15", servers);
		
//		System.out.println("SC: " + scheduler.getNoOfServers());
		
	}
	
	
//generez un task random pe care il adaug intr-un server dupa strategia aleasa
	public Task generateRandomTask() {
		
		Random rand = new Random();
		Integer randomProcessingTime = new Integer(0);
		
	//generez un nr aleatoriu intre [minProcessingTime, maxProcessingTime]
		randomProcessingTime = rand.nextInt(this.maxProcessingTine) + this.minProcessingTime;
		Task newTask = new Task(timpScurs, randomProcessingTime);
		return newTask;
	}
	
	
//verific daca mai sunt elemente in servere
	public int checkServers(LinkedList<Server> servers) {
		Iterator<Server> it = (Iterator<Server>) servers.iterator();
		
		while (it.hasNext()) {
			Server sv = it.next();
			
			if (sv.getQueueIndex() > 0) return 0;
		}
		
		return 1;
	}

	
	public synchronized void run() {
		AtomicInteger currentTime = new AtomicInteger(0);
		LinkedList<Server> servers = new LinkedList<Server>();
		
		
		servers = scheduler.getServers();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < scheduler.getNoOfServers(); i++) {
			executor.execute(servers.get(i));
//			System.out.println(servers.get(i).getTotalWaitingPeriodOnServer());
		}
		
		try {
		//&& checkServers(servers) == 0
		while (currentTime.intValue() < timeLimit && ok == 0) {
//			System.out.println("timp scurs: " + currentTime);
//			System.out.println("ct: " + currentTime.intValue());
			
			timpScurs++;
			simFrame.timpScurs = timpScurs;
			simFrame.setTimeFrame(timpScurs);
			simFrame.setPeakHour(peakHour);
			simFrame.setAverageWaitingTime(averageWaitingTime);
			simFrame.substractFromLabels();
			simFrame.refreshMatrix();
			
			simFrame.index = Server.getNoOfServer();
			simFrame.setLabelTrue();
			
		//	Server.decrementServerQueue(servers, simFrame.getDecrementServerQueue());
			
			if (Server.getPeakHour(servers) > maxPeakHour) {
				maxPeakHour = Server.getPeakHour(servers);
				peakHour = simFrame.toHour(timpScurs);
			}

			
			Iterator<Server> it = servers.iterator();
			this.averageWaitingTimeI = 0;
			
			while (it.hasNext()) {
				Server sv = it.next();
				
				this.averageWaitingTimeI += sv.getAverageWaitingTimePerServer();
				
			}
			
			if (this.averageWaitingTimeI > this.maxAverageWaitingTime)
				this.maxAverageWaitingTime = this.averageWaitingTimeI;
			
			if (Server.getNoOfServer() > 0)
				this.averageWaitingTime = simFrame.toHour(this.maxAverageWaitingTime / Server.getNoOfServer());
			else this.averageWaitingTime = simFrame.toHour(0);
			
//			simFrame.revalidate();
//			simFrame.repaint();
			
			Thread.sleep(1000);
			currentTime.incrementAndGet();
		}
		
		simFrame.setPeakHour(peakHour);
		
		JOptionPane.showMessageDialog(null, "Simularea s-a oprit!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		SimulationManager simManager = new SimulationManager();
		Thread mainThread = new Thread(simManager);
		String[] arg = new String[4];
		
		
		simFrame.createSimulationFrame();
		simFrame.setStrategy(sp);
		simFrame.revalidate();
		simFrame.setLabelTrue();
//		simFrame.createSimulationFrame();
		
		
	// adaug datele
		simFrame.getBtn0().addActionListener(e-> {

			arg[0] = new String(simFrame.getTextField0().getText());
			arg[1] = new String(simFrame.getTextField1().getText());
			arg[2] = new String(simFrame.getTextField2().getText());
			arg[3] = new String(simFrame.getTextField3().getText());
			
			for (int i = 0; i < 4; i++)
				System.out.println(arg[i]);
			
			simManager.setSimulationManager(arg);
			scheduler = new Scheduler(arg[3], "15", servers);
			
			for (int i = 0; i < Integer.parseInt(arg[3]); i++) 
				servers.add(new Server());
			
			simFrame.index = Server.getNoOfServer();
			simFrame.setLabelTrue();
			simFrame.resetTextFields();
			simFrame.revalidate();
			
		});
		
		
	// schimb strategia 
		simFrame.getBtn3().addActionListener(e-> {
			
			if (sp == SelectionPolicy.SHORTEST_QUEUE) sp = SelectionPolicy.SHORTEST_TIME;
			else if (sp == SelectionPolicy.SHORTEST_TIME) sp = SelectionPolicy.SHORTEST_QUEUE;
			
			simFrame.setStrategy(sp);
			simFrame.revalidate();
		});
		
		
	// start simulare
		simFrame.getBtn2().addActionListener(e-> {
			ok = 0;
			mainThread.start();
		});
		
		
	//adaug task random
		simFrame.getBtn1().addActionListener(e-> {
			
			int x = strategy.getQueue(servers, sp);
			Task newTask = simManager.generateRandomTask();
			
			Integer rand = newTask.getProcessingTime() + servers.get(x).getTotalWaitingPeriodOnServer();
			
//			System.out.println(servers.get(x).getTotalWaitingPeriodOnServer());
			//servers.get(strategy.getQueue(servers, sp)).addTask(simManager.generateRandomTask());
			
			servers.get(x).addTask(newTask);
			
			simFrame.addTaskOnFrame(x, rand.toString());
			simFrame.revalidate();
		});
		
		
	// opresc simularea
		simFrame.getBtn4().addActionListener(e-> {
			ok = 1;
		});
		
	
	//adaug un server
		simFrame.getBtn5().addActionListener(e->{
			servers.add(new Server());
			simFrame.index = Server.getNoOfServer();
		});
		
		
	// sterg ultimul server
		simFrame.getBtn6().addActionListener(e-> {
			servers.removeLast();
			simFrame.index = Server.getNoOfServer();
			simFrame.removeServerFromFrame();
			simFrame.setLabelTrue();
			
			Server.decNumberOfServers();
		});
		
		
		
//		simManager.setSimulationManager("20", "2", "10", "5");
//		servers = scheduler.getServers();
//		
//		servers.get(0).addTask(simManager.generateRandomTask());
//		servers.get(0).addTask(simManager.generateRandomTask());
//		servers.get(1).addTask(simManager.generateRandomTask());
//		
//		mainThread.start();
	}
}
