
public class MonitoredData {
	
	private String data1;
	private String data2;
	private String startTime;
	private String endTime;
	private String activity;
	
	
	public MonitoredData(String data1, String startTime, String data2, String endTime, String activity) {
		this.data1 = new String(data1);
		this.data2 = new String(data2);
		this.startTime = new String(startTime);
		this.endTime = new String(endTime);
		this.activity = new String(activity);
	}
	
	public String getStartData() {
		return this.data1;
	}
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getEndData() {
		return this.data2;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public String getActivity() {
		return this.activity;
	}
}
