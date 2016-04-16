package util;

import de.nixosoft.jlr.JLRGenerator;
import entries.StudentEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static util.Constants.OUTPUT_DIR;
import static util.Constants.TEMP_DIR_FILE;


/**
 * Created by Howerton on 3/30/2016.
 */
public class Functions {

    public static String makeFileName(StudentEntry entry, String etc, String extension){

        NumberFormat nf = new DecimalFormat("##.#");
        String name = entry.getName();
        String assignment = entry.getAssignmentName();
        String gradeStr = nf.format(entry.getAssignmentGrade());

        StringBuilder sb = new StringBuilder("\\").append(name).append("_").append(assignment).append("_").append(gradeStr);
        if(etc != ""){
            sb.append("_").append(etc);
        }

        sb.append(".").append(extension);

        return sb.toString();
    }

    public static void createPDF(File texFile) throws IOException {
        JLRGenerator pdfGen = new JLRGenerator();

        System.out.printf("\nWorking Dir: %s\nTex File Name: %s\nOutput Dir: %s\n",
                TEMP_DIR_FILE.getAbsolutePath(),
                texFile.getAbsolutePath(),
                OUTPUT_DIR.getAbsolutePath());

        // create a pdf from latex file
        if (!pdfGen.generate(texFile, OUTPUT_DIR, TEMP_DIR_FILE)) {
            System.out.println(pdfGen.getErrorMessage());

        }else{
            //else, creating a .pdf file was successful

            //create File objects with paths to the aux and log files.
            File auxFile = new File(texFile.getAbsolutePath().replace(".tex", ".aux"));
            File logFile = new File(texFile.getAbsolutePath().replace(".tex", ".log"));

            String texName = texFile.getName();
            String logName = logFile.getName();
            String auxName = auxFile.getName();

            //delete all associated temporary files.
            if(texFile.delete() ){
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

    public static void copyFile(File source, File dest)
            throws IOException {

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    /**
     * Processes text for compatibility with LaTeX by replacing \n new lines with \newline and \t with \hspace{1cm}.
     * This produces similar formatting effects in LaTeX.
     *
     * @param oldText Text to process.
     * @return Text with LaTeX compatible new lines and tabs.
     */
    public static String processText(String oldText){

        String newText = "";
        System.out.printf("Newline found at position %d.", oldText.indexOf("\n"));
        String newline = new StringBuilder(" ").append("\\\\").append("newline").append(" ").toString();
        String tab = new StringBuilder(" ").append("\\\\").append("hspace{1cm}").append(" ").toString();
        System.out.printf("\nActual new line: %s\n", newline);


        newText = oldText.replaceAll("\\n", newline).replaceAll("\\t", tab);

        return newText;
    }
}
