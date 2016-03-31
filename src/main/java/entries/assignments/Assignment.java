package entries.assignments;

/**
 * Created by Howerton on 3/30/2016.
 */
public interface Assignment {

    String type();
    String styleDescription();
    String getClassName();
    String getSection();
    String getAssignmentName();

    double totalPoints();
    double maxImplementationPoints();
    double maxStylePoints();
    double maxResultPoints();

    double masterTotalPoints();




}
