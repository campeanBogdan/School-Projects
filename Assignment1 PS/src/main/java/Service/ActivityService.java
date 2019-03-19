package Service;

import Model.Activity;

import java.util.List;

public interface ActivityService {

    int save(Activity activity);
    List<Activity> getAll();
}
