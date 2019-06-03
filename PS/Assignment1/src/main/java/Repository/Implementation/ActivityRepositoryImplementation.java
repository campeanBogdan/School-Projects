package Repository.Implementation;

import Model.Activity;
import Repository.ActivityRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ActivityRepositoryImplementation implements ActivityRepository {

    private final JDBConnection jdbConnection;


    public ActivityRepositoryImplementation(JDBConnection jdbConnection) {
        this.jdbConnection = jdbConnection;
    }

    public void create(Activity activity) {
        Connection conn = jdbConnection.getConnection();
        try {
            String sql = "INSERT INTO Assignment1.`activity`(name, date) VALUES ('" +
                        activity.getName() + "', '" + activity.getDate() + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Activity> findAll() {
        Connection conn = jdbConnection.getConnection();
        List<Activity> activityList = new LinkedList<Activity>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Assignment1.`activity`", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Activity activity = new Activity();
                activity.setName(rs.getString("name"));
                activity.setDate(rs.getString("date"));
                activityList.add(activity);
            }
            return activityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
