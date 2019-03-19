package Repository;

import Model.Activity;

import java.util.List;

public interface ActivityRepository {

    void create(Activity activity);
    List<Activity> findAll();
}
