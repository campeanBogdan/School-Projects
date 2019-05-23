import java.io.File; 
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.*;


public class Methods implements Serializable {

	private List<String> list;
	private static final String fileName = "C:\\Users\\Bogdan\\eclipse-workspace\\Project5\\Files\\Activities.txt";
	private int ii;
	private Integer hour = new Integer(0);
	
	
	public Methods() {
		list = new LinkedList<String>();
	}
	
	
// readFromFile
	@SuppressWarnings("unchecked")
	public ArrayList<MonitoredData> readFromFile() {
		
		ArrayList<MonitoredData> list = new ArrayList<MonitoredData>();
		@SuppressWarnings("rawtypes")
		LinkedList<String> fileLine = new LinkedList<String>();
		
	
		try {
			Stream<String> lines = Files.lines(Paths.get(fileName));
			
			lines.collect(Collectors.toCollection(() -> fileLine));
						
			list = (ArrayList<MonitoredData>) fileLine.stream()
					.map(f -> {
						
						int i = 0;
						String data1 = new String("");
						String data2 = new String("");
						String activity = new String("");
						String time1 = new String("");
						String time2 = new String("");
						
						
						while (f.charAt(i) != ' ' && i < f.length()) {
							data1 = data1 + f.charAt(i);
							i++;
						}
						
						i++;
						
						while (f.charAt(i) != '\t' && i < f.length()) {
							time1 = time1 + f.charAt(i);
							i++;
						}
						
						while (f.charAt(i) == '\t') i++;
						
						
						while (f.charAt(i) != ' ' && i < f.length()) {
							data2 = data2 + f.charAt(i);
							i++;
						}
						
						i++;
						
						while (f.charAt(i) != '\t') {
							time2 = time2 + f.charAt(i);
							i++;
						}
						
						while (f.charAt(i) == '\t') i++;
						
						
						while (i < f.length()) {
							activity = activity + f.charAt(i);
							i++;
						}
						
						data1 = data1.replace("\t", "");
						data2 = data2.replace("\t", "");
						time1 = time1.replace("\t", "");
						time2 = time2.replace("\t", "");
						activity = activity.replace("\t", "");
						
						return new MonitoredData(data1, time1, data2, time2, activity);
						
					}).collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
// getDistinctDays
	public Integer getDistinctDays(List<MonitoredData> list) {
		Integer distinctDays = new Integer(0);
		
		
		distinctDays = list.stream()
				.filter(f -> !f.getStartData().equals(f.getEndData()))
				.map(f -> 1)
				.reduce(0, Integer::sum);
		
		distinctDays++;
		
		return distinctDays;
	}
	
	
// getDistinctActivities
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> getDistinctActivities(List<MonitoredData> list) {
		HashMap<String, Integer> distinctActivities = new HashMap<String, Integer>();
		List<MonitoredData> list2;
		
		
//		Map<String, Integer> distinctActivities = list.stream()
//				.collect(Collectors.toMap(f -> f.getActivity(), value -> 0));
		
		for (MonitoredData i : list) {
			distinctActivities.put(i.getActivity(), 0);
		}
		
		list.stream()
				.filter(f -> true)
				.map(f -> {
					Integer counter = distinctActivities.get(f.getActivity());
					distinctActivities.replace(f.getActivity(), counter + 1);
					
					return null;
				})
				.collect(Collectors.toList());
		
		try {
			FileWriter f = new FileWriter("C:\\Users\\Bogdan\\eclipse-workspace\\Project5\\Files\\Distinct Activities.txt");
		
			f.write("Distinct activities:\r\n");
				
			for (String s : distinctActivities.keySet())
				f.write("	" + s + ":	" + distinctActivities.get(s) + "\r\n");
			
			f.close();
			
			} catch (Exception e) {	
			}
		
		for (Map.Entry<String, Integer> entry : distinctActivities.entrySet())
			System.out.println("	" + entry.getKey() + ": " + entry.getValue().toString());
		
		return (HashMap<String, Integer>) distinctActivities;
	}
	
	
// getDistinctActivitiesPerDays
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public Map<Integer, Map<String, Integer>> getDistinctActivitiesPerDays(List<MonitoredData> list) {
		Map<Integer, Map<String, Integer>> distinctActivities = new HashMap<Integer, Map<String, Integer>>();
		Map<String, Integer> date = new HashMap<String, Integer>();
		List<MonitoredData> list2;
		int j = 1;
		String str = list.get(0).getStartData();
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		
		
		for (MonitoredData i : list) {
			
			if (!str.equals(i.getStartData())) {
				hashMap = new HashMap<String, Integer>();
				j++;
				str = i.getStartData();
			}
			
			hashMap.put(i.getActivity(), 0);
			distinctActivities.put(j, hashMap);
			date.put(i.getStartData(), j);
		}
		
		final int k = j;
		
		
		for (ii = 1; ii <= k; ii++) {
			list.stream()
					.filter(f -> {
						if (date.get(f.getStartData()).equals(ii)) 
							return true;
						else return false;
					})
					.map(f -> {
						Map<String, Integer> hashMap2 = new HashMap<String, Integer>();
						
							hashMap2 = distinctActivities.get(ii);
							Integer counter = hashMap2.get(f.getActivity());
							
							hashMap2.put(f.getActivity(), counter + 1);
							distinctActivities.replace(ii, hashMap2);					
						
						return null;
					})
					.collect(Collectors.toList());
		}
		
		
		for (int i = 1; i <= j; i++) {
			System.out.println("	Day " + i + ":");
			Map<String, Integer> hashMap3 = distinctActivities.get(i);
			
			for (String s : hashMap3.keySet())
				System.out.println("		" + s + ":	" + hashMap3.get(s));
		}
		
		try {
			FileWriter f = new FileWriter("C:\\Users\\Bogdan\\eclipse-workspace\\Project5\\Files\\Distinct Activities Per Days.txt");
			
			for (int i = 1; i <= j; i++) {
				f.write("\r\nDay " + i + ":\r\n");
				Map<String, Integer> hashMap3 = distinctActivities.get(i);
				
				for (String s : hashMap3.keySet())
					f.write("	" + s + ":	" + hashMap3.get(s));
			}
			
			f.close();
		} catch (Exception e) {
			
		}
		
		return distinctActivities;
	}
	
	
// getHour
	private Integer getHour(String time) {
		Integer hour = new Integer(0);
		String hourString = new String();
		
		
		hourString = hourString + time.charAt(0) + time.charAt(1);
		
		hour = Integer.parseInt(hourString);
		
		return hour;
	}
	
	
// getMinutes
	private Integer getMinutes(String time) {
		Integer minutes = new Integer(0);
		String minutesString = new String();
		
		
		minutesString = minutesString + time.charAt(3) + time.charAt(4);
		minutes = Integer.parseInt(minutesString);
		
		return minutes;
	}
	
	
// getSeconds
	private Integer getSeconds(String time) {
		Integer seconds = new Integer(0);
		String secondsString = new String();
		
		
		secondsString = secondsString + time.charAt(6) + time.charAt(7);
		seconds = Integer.parseInt(secondsString);
		
		return seconds;
	}
	
	
// getYear
	private Integer getYear(String date) {
		Integer year = new Integer(0);
		String yearString = new String();
		
		
		for (int i = 0; i < 4; i++)
			yearString += date.charAt(i);
		
		year = Integer.parseInt(yearString);
		
		return year;
	}
	

// getMonth
	private Integer getMonth(String date) {
		Integer month = new Integer(0);
		String monthString = new String();
		
		
		monthString += monthString + date.charAt(5) + date.charAt(6);
		month = Integer.parseInt(monthString);
		
		return month;
	}
	
	
// getDay
	private Integer getDay(String date) {
		Integer day = new Integer(0);
		String dayString = new String();

		
		dayString = dayString + date.charAt(8) + date.charAt(9);
		day = Integer.parseInt(dayString);
		
		return day;
	}
	
	
// getActivities
	@SuppressWarnings("deprecation")
	public Map<String, DateTime> getActivities(List<MonitoredData> list) {
		Map<String, DateTime> activities = new HashMap<String, DateTime>();
		List<MonitoredData> list2 = new ArrayList<MonitoredData>();
		DateFormat format = new SimpleDateFormat("hh:mm:ss");
			
			
			for (MonitoredData j : list)
				activities.put(j.getActivity(), new DateTime(2017, 11, 1, 0, 0, 0, 0));
	
			
			list.stream()
					.filter(f -> true)
					.map(f -> {
						Integer hour = new Integer(0);
						Integer minutes = new Integer(0);
						Integer seconds = new Integer(0);
						Integer year = new Integer(0);
						Integer month = new Integer(0);
						Integer day = new Integer(0);

						
						hour += Math.abs(this.getHour(f.getEndTime()) - this.getHour(f.getStartTime()));
						minutes = Math.abs(this.getMinutes(f.getEndTime()) - this.getMinutes(f.getStartTime()));
						seconds = Math.abs(this.getSeconds(f.getEndTime()) - this.getSeconds(f.getStartTime()));
						year = this.getYear(f.getStartData());
						month = this.getMonth(f.getStartData());
						day = this.getDay(f.getStartData());
						

						DateTime oldDate = activities.get(f.getActivity());
						DateTime newDate = new DateTime(year, month, oldDate.getDayOfMonth(), hour, minutes, seconds, 0);

						
						newDate = newDate.plusSeconds(oldDate.getSecondOfMinute());				
						newDate = newDate.plusMinutes(oldDate.getMinuteOfHour());
						newDate = newDate.plusHours(oldDate.getHourOfDay());
						
						activities.replace(f.getActivity(), newDate);
						
						
				// print
						System.out.println("	" + hour + ":" + minutes + ":" + seconds);
						
						
						return null;
					})
					.collect(Collectors.toList());
			
			
			System.out.println();
			System.out.println("All activities: ");
			
			for (String str : activities.keySet()) {
				DateTime date2 = activities.get(str);
				
				System.out.println("	" + str.toString() + ":		" + date2.getDayOfMonth() + " d, " + date2.getHourOfDay() + ":" + date2.getMinuteOfHour() + ":" + date2.getSecondOfMinute());
			}
		
		return activities;
	}
	
	
// getFilteredActivities
	public Map<String, DateTime> getFilteredActivities(Map<String, DateTime> list) {
		Map<String, DateTime> filteredActivities = new HashMap<String, DateTime>();
		
		
		filteredActivities = list.entrySet().stream()
				.filter(f -> {
					DateTime dateTime = f.getValue();
					
					if (dateTime.getDayOfMonth() > 1)
						return true;
					else
						if (dateTime.getHourOfDay() > 10)
							return true;
						else
							return false;
				})
				.collect(Collectors.toMap(f -> f.getKey(), f -> f.getValue()));
		
		
		for (String str : filteredActivities.keySet()) {
			DateTime dateTime = filteredActivities.get(str);
			
			System.out.println("	" + str.toString() + ":		" + dateTime.getDayOfMonth() + " d, " + dateTime.getHourOfDay() + ":" + dateTime.getMinuteOfHour() + ":" + dateTime.getSecondOfMinute());
		}
		
		
		try {
			FileWriter f = new FileWriter("C:\\Users\\Bogdan\\eclipse-workspace\\Project5\\Files\\Activities Larger Than 10 Hours.txt");
			
			for (String str : filteredActivities.keySet()) {
				DateTime dateTime = filteredActivities.get(str);
				
				f.write(str.toString() + ":		" + dateTime.getDayOfMonth() + " d, " + dateTime.getHourOfDay() + ":" + dateTime.getMinuteOfHour() + ":" + dateTime.getSecondOfMinute() + "\r\n");
			}
			
			f.close();
		} catch (Exception e) {
			
		}
		
		
		return filteredActivities;
	}
	
	
// filterFiveMinutes
	@SuppressWarnings("unchecked")
	public List<String> filterFiveMinutes(List<MonitoredData> list, Map<String, Integer> distinctActivities) {
		List<String> filteredList = new ArrayList<String>();
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		Map<String, Integer> hashMap2 = new HashMap<String, Integer>();
		
		
		for (int i = 0; i < list.size(); i++)
			hashMap.put(list.get(i).getActivity(), 0);
		
			list.stream()
				.filter(f -> {
					Integer time = Math.abs(this.getMinutes(f.getEndTime()) - this.getMinutes(f.getStartTime()));
					
					if (time <= 5)
						return true;
					else
						return false;
				})
				.map(f -> {
					Integer counter = hashMap.get(f.getActivity());
					
					hashMap.replace(f.getActivity(), counter + 1);
					
					return null;
				})
				.collect(Collectors.toList());


			hashMap.entrySet().stream()
					.filter(f -> {
						Integer p1 = f.getValue();
						Integer p2 = distinctActivities.get(f.getKey());
						
						
						Double procent = (double) p1 / p2;
						
						if (procent >= 0.9) {
							filteredList.add(f.getKey());
							return true;
						}
						else return false;
					})
					.collect(Collectors.toList());
			
			
			try {
				FileWriter f = new FileWriter("C:\\Users\\Bogdan\\eclipse-workspace\\Project5\\Files\\Activities 5 minutes.txt");
				
				for (String str : filteredList) {
					System.out.println("	" + str);
					f.write(str + "\r\n");
				}
				
				f.close();
			} catch (Exception e) {
				
			}
			
			
		
		return filteredList;
	}
}
