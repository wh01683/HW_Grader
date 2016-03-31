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
 * Created by Howerton on 3/30/2016.
 */
public class StudentEntryWithUML extends StudentEntry implements Rubricable{

    double umlGrade;
    String umlComments;
    Assignment assignment;

    public StudentEntryWithUML(Assignment assignment, String name, String compErr, String runErr, String compErrComments, String runErrComments,
                               String resultComments, String implementationComments, String styleComments,
                               String programInput, String programOutput,
                               double resultGivenPoints, double implementationGivenPoints,
                               double styleGivenPoints, double assignmentGrade, double umlGrade, String umlComments) {
        super(assignment, name, compErr, runErr, compErrComments, runErrComments,
                resultComments, implementationComments, styleComments, programInput,
                programOutput, resultGivenPoints,
                implementationGivenPoints, styleGivenPoints, assignmentGrade);

        this.umlGrade = umlGrade;
        this.umlComments = umlComments;
        this.assignment = assignment;
    }


    @Override
    public File writeTexFile() {
        return super.writeTexFile();
    }

    @Override
    public void writePDF() {
        super.writePDF();
        writeUMLPDF();
    }

    @Override
    public double getAssignmentGrade() {
        return super.getAssignmentGrade() + this.umlGrade;
    }

    private File writeUMLTexFile() {
        JLRConverter converter = new JLRConverter(WORKING_DIR_FILE);

        double umlGrade = this.getUmlGrade();


        String studentName = this.getName();

        String umlComm = this.getUmlComments();

        NumberFormat nf = new DecimalFormat("##.#");

        converter.replace("semester", SEMESTER);
        converter.replace("className", assignment.getClassName());
        converter.replace("section", assignment.getSection());
        converter.replace("assignmentTitle", assignment.getAssignmentName() + " UML");
        converter.replace("totalPoints", nf.format(assignment.masterTotalPoints() * UML_GRADE_PERCENTAGE));
        converter.replace("studentName", studentName);

        converter.replace("umlMaxPoints", nf.format(assignment.masterTotalPoints() * UML_GRADE_PERCENTAGE));
        converter.replace("umlGrade", nf.format(umlGrade));
        converter.replace("umlPointsGiven", nf.format(umlGrade));
        converter.replace("umlComments", umlComm);

        File tempTexFile = new File(WORKING_DIR_FILE.getAbsolutePath().concat(util.Functions.makeFileName(this, "UML", "tex")));


        try {
            if (!converter.parse(UML_RUBRIC_TEMPLATE_TEX, tempTexFile)) {
                System.out.println(converter.getErrorMessage());
            }
        } catch (IOException i) {
            i.printStackTrace();
        }

        return tempTexFile;
    }

    private void writeUMLPDF(){

        File texFile = writeUMLTexFile();

        try{
            Functions.createPDF(texFile);
        }catch (IOException i){
            i.printStackTrace();
        }

    }

    public double getUmlGrade() {
        return umlGrade;
    }

    public String getUmlComments() {
        return umlComments;
    }

}
