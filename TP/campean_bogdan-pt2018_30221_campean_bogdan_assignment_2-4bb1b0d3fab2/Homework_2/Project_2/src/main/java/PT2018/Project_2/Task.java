package PT2018.Project_2;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {

	private int arrivalTime;
	private int processingTime;
	private int finishTime;
	
	private static int number = 0;
	private int taskNumber;
	
	
	public Task(int arrivalTime, int processingTime) {
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
		this.taskNumber = number;
		number++;
	}
	
	
	// arrivalTime - processingTime
	public int getAverageTask() {
		return this.processingTime - this.taskNumber;
	}
	
	
	//timpul total de asteptare al task-ului
	public void setTaskFinsihTime(int serverTotalTime) {
		this.finishTime = this.processingTime + this.arrivalTime + serverTotalTime;
	}
	
	
	public synchronized int getFinsihTime() {
		return finishTime;
	}
	
	
	public synchronized void decTaskFinishTime() {
		this.finishTime--;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
	public void decProcessingTime() {
		this.processingTime--;
	}
	
	public int getTaskNumber() {
		return this.taskNumber;
	}
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}
}
