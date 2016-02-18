import de.nixosoft.jlr.JLRConverter;
import de.nixosoft.jlr.JLRGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Howerton on 2/16/2016.
 */
public class StudentRubricMaker {

    private static final String SEMESTER = "Spring 2016";
    private static final String WORKING_DIR = "C:\\Users\\Howerton\\Documents\\grading_template\\";
    private static final File WORKING_DIR_FILE = new File(WORKING_DIR);
    private static final File RUBRIC_TEMPLATE_TEX = new File(WORKING_DIR_FILE.getAbsolutePath().concat("\\rubric_template.tex"));
    private static final File OUTPUT_DIR = new File("C:\\Users\\Howerton\\Desktop\\pdfs_test\\");

    public static void makePDF(StudentEntry entry){
        createTexFile(entry);
        try {

            File tempTexFile = new File(WORKING_DIR_FILE.getAbsolutePath().concat(makeFileName(entry, "tex")));

            createPDF(WORKING_DIR_FILE, tempTexFile, OUTPUT_DIR);

        }catch (IOException i){
            i.printStackTrace();
        }
    }

    private static void createPDF(File workingDirectory, File texFileName, File outputDirectory) throws IOException {
        JLRGenerator pdfGen = new JLRGenerator();

        System.out.printf("\nWorking Dir: %s\nTex File Name: %s\nOutput Dir: %s\n",
                workingDirectory.getAbsolutePath(),
                texFileName.getAbsolutePath(),
                outputDirectory.getAbsolutePath());

        // create a pdf from latex file
        if (!pdfGen.generate(texFileName, outputDirectory, workingDirectory)) {
            System.out.println(pdfGen.getErrorMessage());
        }else{
            //else, creating a .pdf file was successful

            //create File objects with paths to the aux and log files.
            File auxFile = new File(texFileName.getAbsolutePath().replace(".tex", ".aux"));
            File logFile = new File(texFileName.getAbsolutePath().replace(".tex", ".log"));

            String texName = texFileName.getName();
            String logName = logFile.getName();
            String auxName = auxFile.getName();

            //delete all associated temporary files.
            if(texFileName.delete() ){
                System.out.printf("%s was successfully deleted.\n", texName);
            }
            if(auxFile.delete() ){
                System.out.printf("%s was successfully deleted.\n", auxName);
            }
            if(logFile.delete() ){
                System.out.printf("%s was successfully deleted.\n", logName);
            }
        }
    }

    private static void createTexFile(StudentEntry entry){
        JLRConverter converter = new JLRConverter(WORKING_DIR_FILE);

        //variables dependent on assignment style (homework vs lab)
        String styleDescription = "";
        int resultMaxPoints;
        int impMaxPoints;
        int styleMaxPoints;


        if(entry.getAssignmentName().contains("Homework")){
            styleDescription = "Proper style and documentation";
            resultMaxPoints = 40;
            impMaxPoints = 40;
            styleMaxPoints = 20;
        }else{
            styleDescription = "Proper programming style";
            resultMaxPoints = 4;
            impMaxPoints = 4;
            styleMaxPoints = 2;
        }

        int totalPoints = entry.getAssignmentTotalPoints();

        String studentName = entry.getName();

        String className = entry.getClassName();
        String section = entry.getSection();
        String assignmentTitle = entry.getAssignmentName();

        int gradeReceived = entry.getAssignmentGrade();

        String compErr = entry.getCompErr();
        String runErr = entry.getRunErr();

        String compComm = entry.getCompErrComments();
        String runComm = entry.getRunErrComments();

        int styleGrade = entry.getStyleGivenPoints();
        int impGrade = entry.getImplementationGivenPoints();
        int resultGrade = entry.getResultGivenPoints();

        String input = entry.getProgramInput();
        String output = entry.getProgramOutput();

        String styleComm = entry.getStyleComments();
        String impComm = entry.getImplementationComments();
        String resComm = entry.getResultComments();

        converter.replace("semester", SEMESTER);
        converter.replace("className", className);
        converter.replace("section", section);
        converter.replace("assignmentTitle", assignmentTitle);
        converter.replace("totalPoints", totalPoints);


        converter.replace("studentName", studentName);
        converter.replace("gradeReceived", gradeReceived);

        converter.replace("compilationError", compErr);
        converter.replace("compilationComment", compComm);

        converter.replace("runTimeError", runErr);
        converter.replace("runTimeComment", runComm);

        converter.replace("resultMaxPoints", resultMaxPoints);
        converter.replace("resultPointsGiven", resultGrade);
        converter.replace("resultComments", resComm);

        converter.replace("implementationMaxPoints", impMaxPoints);
        converter.replace("implementationPointsGiven", impGrade);
        converter.replace("implementationComments", impComm);

        converter.replace("styleDescription", styleDescription);
        converter.replace("styleMaxPoints", styleMaxPoints);
        converter.replace("stylePointsGiven", styleGrade);
        converter.replace("styleComments", styleComm);

        converter.replace("input", input);
        converter.replace("output", output);



        File tempTexFile = new File(WORKING_DIR_FILE.getAbsolutePath().concat(makeFileName(entry, "tex")));

        try {
            if (!converter.parse(RUBRIC_TEMPLATE_TEX, tempTexFile)) {
                System.out.println(converter.getErrorMessage());
            }
        }catch (IOException i){
            i.printStackTrace();
        }

    }

    private static String makeFileName(StudentEntry entry, String extension){
        String name = entry.getName();
        String assignment = entry.getAssignmentName();
        String grade = entry.getAssignmentGrade()+"";

        StringBuilder sb = new StringBuilder("\\").append(name).append("_").append(assignment).append("_").append(grade).append(".").append(extension);

        return sb.toString();
    }



}
