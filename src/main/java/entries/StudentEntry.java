package entries;

import de.nixosoft.jlr.JLRConverter;
import entries.assignments.Assignment;
import util.Functions;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static util.Constants.*;

/**
 * Created by Howerton on 2/16/2016.
 */
public class StudentEntry implements Rubricable{

    /*  Fields obtained through the constructor  */
    private String name, compErr, runErr, compErrComments, runErrComments,
            resultComments, implementationComments, styleComments,
            programInput, programOutput;

    private double resultGivenPoints, implementationGivenPoints, styleGivenPoints, assignmentGrade;
    /*  End fields obtained through constructor */

    private Assignment assignment;



    public StudentEntry(Assignment assignment, String name, String compErr, String runErr, String compErrComments, String runErrComments,
                        String resultComments, String implementationComments, String styleComments, String programInput,
                        String programOutput,
                        double resultGivenPoints, double implementationGivenPoints, double styleGivenPoints, double assignmentGrade) {

        this.assignment = assignment;
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
        this.resultGivenPoints = resultGivenPoints;
        this.implementationGivenPoints = implementationGivenPoints;
        this.styleGivenPoints = styleGivenPoints;
        this.assignmentGrade = assignmentGrade;
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

    public String getAssignmentName() {
        return assignment.getAssignmentName();
    }

    public double getResultGivenPoints() {
        return resultGivenPoints;
    }

    public double getImplementationGivenPoints() {
        return implementationGivenPoints;
    }

    public double getStyleGivenPoints() {
        return styleGivenPoints;
    }

    public double getAssignmentGrade() {
        return assignmentGrade;
    }

    public void writePDF() {
        File texFile = writeTexFile();

        try {
            Functions.createPDF(texFile);
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    public File writeTexFile() {

        JLRConverter converter = new JLRConverter(TEMP_DIR_FILE);

        NumberFormat nf = new DecimalFormat("##.#");

        String studentName = this.getName();

        double gradeReceived = this.getAssignmentGrade();

        String compErr = this.getCompErr();
        String runErr = this.getRunErr();
        String compComm = this.getCompErrComments();
        String runComm = this.getRunErrComments();

        double styleGrade = this.getStyleGivenPoints();
        double impGrade = this.getImplementationGivenPoints();
        double resultGrade = this.getResultGivenPoints();

        String input = this.getProgramInput();
        String output = this.getProgramOutput();

        String styleComm = this.getStyleComments();
        String impComm = this.getImplementationComments();
        String resComm = this.getResultComments();

        converter.replace("semester", SEMESTER);
        converter.replace("className", assignment.getClassName());
        converter.replace("section", assignment.getSection());
        converter.replace("assignmentTitle", assignment.getAssignmentName());
        converter.replace("totalPoints", nf.format(assignment.totalPoints()));


        converter.replace("studentName", studentName);
        converter.replace("gradeReceived", nf.format(gradeReceived));

        converter.replace("compilationError", compErr);
        converter.replace("compilationComment", compComm);

        converter.replace("runTimeError", runErr);
        converter.replace("runTimeComment", runComm);

        converter.replace("resultMaxPoints", nf.format(this.assignment.maxResultPoints()));
        converter.replace("resultPointsGiven", nf.format(resultGrade));
        converter.replace("resultComments", resComm);

        converter.replace("implementationMaxPoints", nf.format(this.assignment.maxImplementationPoints()));
        converter.replace("implementationPointsGiven", nf.format(impGrade));
        converter.replace("implementationComments", impComm);

        converter.replace("styleDescription", this.assignment.styleDescription());
        converter.replace("styleMaxPoints", nf.format(this.assignment.maxStylePoints()));
        converter.replace("stylePointsGiven", nf.format(styleGrade));
        converter.replace("styleComments", styleComm);

        converter.replace("input", input);
        converter.replace("output", output);



        File tempTexFile = new File(TEMP_DIR_FILE.getAbsolutePath().concat(util.Functions.makeFileName(this, "", "tex")));

        try {
            if (!converter.parse(RUBRIC_TEMPLATE_TEX, tempTexFile)) {
                System.out.println(converter.getErrorMessage());
            }
        }catch (IOException i){
            i.printStackTrace();
        }

        return tempTexFile;
    }

}
