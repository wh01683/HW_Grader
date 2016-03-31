package entries.assignments;

import static util.Constants.*;

/**
 * Created by Howerton on 3/30/2016.
 */
public class Homework implements Assignment {


    private static double totalPointWorth = 100;

    private String assignmentName, className, section;
    private boolean reqUML;

    public Homework(String assignmentName, String className, String section, boolean reqUML){
        this.assignmentName = assignmentName;
        this.className = className;
        this.section = section;
        this.reqUML = reqUML;
        if(reqUML){
            totalPointWorth *= (1 - UML_GRADE_PERCENTAGE);
        }
    }

    public double maxImplementationPoints() {
        return IMPLEMENTATION_GRADE_PERCENTAGE * totalPointWorth;
    }

    public double maxStylePoints() {
        return STYLE_GRADE_PERCENTAGE * totalPointWorth;
    }

    public double maxResultPoints() {
        return RESULTS_GRADE_PERCENTAGE * totalPointWorth;
    }

    public String type() {
        return "HOMEWORK";
    }

    public String styleDescription() {
        return "Proper style and documentation";
    }

    public double totalPoints() {
        return totalPointWorth;
    }

    public String getClassName() {
        return this.className;
    }

    public String getSection() {
        return this.section;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public double masterTotalPoints() {
        return TOTAL_POINTS_HOMEWORK;
    }
}
