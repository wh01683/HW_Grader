/**
 * Created by Howerton on 2/16/2016.
 */
public class StudentEntry {

    private String name, compErr, runErr, compErrComments, runErrComments,
            resultComments, implementationComments, styleComments,
            programInput, programOutput,
            className, section, assignmentName;

    private int resultGivenPoints, implementationGivenPoints, styleGivenPoints, assignmentGrade, assignmentTotalPoints;


    public StudentEntry(String name, String compErr, String runErr, String compErrComments, String runErrComments,
                        String resultComments, String implementationComments, String styleComments, String programInput,
                        String programOutput, String className, String section, String assignmentName,
                        int resultGivenPoints, int implementationGivenPoints, int styleGivenPoints, int assignmentGrade, int assignmentTotalPoints) {
        this.name = name;
        this.compErr = compErr;
        this.runErr = runErr;
        this.compErrComments = compErrComments;
        this.runErrComments = runErrComments;
        this.resultComments = resultComments;
        this.implementationComments = implementationComments;
        this.styleComments = styleComments;
        this.programInput = programInput;
        this.programOutput = programOutput;
        this.className = className;
        this.section = section;
        this.assignmentName = assignmentName;
        this.resultGivenPoints = resultGivenPoints;
        this.implementationGivenPoints = implementationGivenPoints;
        this.styleGivenPoints = styleGivenPoints;
        this.assignmentGrade = assignmentGrade;
        this.assignmentTotalPoints = assignmentTotalPoints;
    }



    public String getName() {
        return name;
    }

    public String getCompErr() {
        return compErr;
    }

    public String getRunErr() {
        return runErr;
    }

    public String getCompErrComments() {
        return compErrComments;
    }

    public String getRunErrComments() {
        return runErrComments;
    }

    public String getResultComments() {
        return resultComments;
    }

    public String getImplementationComments() {
        return implementationComments;
    }

    public String getStyleComments() {
        return styleComments;
    }

    public String getProgramInput() {
        return programInput;
    }

    public String getProgramOutput() {
        return programOutput;
    }

    public String getClassName() {
        return className;
    }

    public String getSection() {
        return section;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public int getResultGivenPoints() {
        return resultGivenPoints;
    }

    public int getImplementationGivenPoints() {
        return implementationGivenPoints;
    }

    public int getStyleGivenPoints() {
        return styleGivenPoints;
    }

    public int getAssignmentGrade() {
        return assignmentGrade;
    }

    public int getAssignmentTotalPoints() {
        return assignmentTotalPoints;
    }
}
