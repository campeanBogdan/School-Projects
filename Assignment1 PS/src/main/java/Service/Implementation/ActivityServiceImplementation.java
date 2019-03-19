package Service.Implementation;

import Model.Activity;
import Repository.Implementation.ActivityRepositoryImplementation;
import Service.ActivityService;

import java.util.List;

public class ActivityServiceImplementation implements ActivityService {

    private final ActivityRepositoryImplementation ari;


    public ActivityServiceImplementation(ActivityRepositoryImplementation ari) {
        this.ari = ari;
    }

    public int save(Activity activity) {
        ari.create(activity);
        return 0;
    }

    public List<Activity> getAll() {
        List<Activity> activitiesList = ari.findAll();
        return activitiesList;
    }
}
