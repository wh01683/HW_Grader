package entries.assignments;

import static util.Constants.*;

/**
 * Created by Howerton on 3/30/2016.
 */
public class Lab implements Assignment{

    private static double totalPointWorth = 10;

    private String assignmentName, className, section;
    boolean reqUML;

    public Lab(String assignmentName, String className, String section, boolean reqUML){
        this.assignmentName = assignmentName;
        this.className = className;
        this.section = section;
        this.reqUML = reqUML;

        if(reqUML){
            totalPointWorth *= (1 - UML_GRADE_PERCENTAGE);
        }
    }

    public String type() {
        return "LAB";
    }

    public String styleDescription() {
        return "Proper programming style";
    }

    public double totalPoints() {
        return totalPointWorth;
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
        return TOTAL_POINTS_LAB;
    }
}
