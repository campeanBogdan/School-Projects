import java.sql.Time;
import java.util.*;

import org.joda.time.DateTime;


public class Controller {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		List<MonitoredData> list = new ArrayList<MonitoredData>();
		Methods methods = new Methods();
		Map<String, Integer> distinctActivities = new HashMap<String, Integer>();
		Map<Integer, Map<String, Integer>> distinctActivitiesPerDays = new HashMap<Integer, Map<String, Integer>>();
		Map<String, DateTime> allActivities = new HashMap<String, DateTime>();
		Map<String, DateTime> filteredActivities = new HashMap<String, DateTime>();
		List<String> stringList = new ArrayList<String>();
		
		
		
	// readFromFile 
		list = methods.readFromFile();
		
		
	// distinctDays
		System.out.println("1) Distinct days: " + methods.getDistinctDays(list));
		System.out.println();
		
	// distinctActivities
		System.out.println("2) Distinct activities: ");
		distinctActivities = methods.getDistinctActivities(list);
		System.out.println();
		
	// distinctActivitesPerDays
		System.out.println("3) Distinct activities per days:");
		distinctActivitiesPerDays = methods.getDistinctActivitiesPerDays(list);
		System.out.println();
		
	// totalDurationPerActivity
		System.out.println("4) END_TIME - START_TIME: ");
		allActivities = methods.getActivities(list);
		System.out.println();
		
	// filterActivities
		System.out.println("5) Activity larger than 10 hours:");
		filteredActivities = methods.getFilteredActivities(allActivities);
		System.out.println();
		
	// filterFiveMinutes
		System.out.println("6) Filtered activities 5 minutes:");
		stringList = methods.filterFiveMinutes(list, distinctActivities);
	}

}
