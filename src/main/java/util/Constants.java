package util;

import org.apache.commons.lang.SystemUtils;

import java.io.File;

/**
 * Created by Howerton on 3/30/2016.
 */
public class Constants {

    //Separator is forward slash for unix based, or a double (escaped) back slash for windows based OSes
    public static final String SEP = (System.getProperty("file.separator") == "/")?
            System.getProperty("file.separator") :
            System.getProperty("file.separator") + System.getProperty("file.separator");

    public static final String SEMESTER = "Spring 2016";
    public static final String TEMP_DIR = SystemUtils.JAVA_IO_TMPDIR;
    public static final String CURRENT_DIR = System.getProperty("user.dir");


    //local files in program
    public static final File LOCAL_RUBRIC_TEMPLATE_TEX = new File(CURRENT_DIR.concat(SEP).concat("res").concat(SEP).concat("rubric_template.tex"));
    public static final File LOCAL_UML_RUBRIC_TEMPLATE_TEX = new File(CURRENT_DIR.concat(SEP).concat("res").concat(SEP).concat("uml_rubric.tex"));

    //files to use in program for generating pdfs.
    public static final File TEMP_DIR_FILE = new File(TEMP_DIR.concat(SEP));

    public static final File RUBRIC_TEMPLATE_TEX = new File(TEMP_DIR_FILE.getAbsolutePath().concat(SEP).concat("rubric_template.tex"));
    public static final File UML_RUBRIC_TEMPLATE_TEX = new File(TEMP_DIR_FILE.getAbsolutePath().concat(SEP).concat("uml_rubric.tex"));

    //should make folder in same directory as program.
    public static final File OUTPUT_DIR_FILE = new File(new File(CURRENT_DIR).getParent().concat(SEP).concat("rubric_pdfs").concat(SEP));

    //grade calculation constants
    public static final float RESULTS_GRADE_PERCENTAGE = .4f;
    public static final float IMPLEMENTATION_GRADE_PERCENTAGE = .4f;
    public static final float STYLE_GRADE_PERCENTAGE = .2f;
    public static final float UML_GRADE_PERCENTAGE = .3f;

    public static final double TOTAL_POINTS_HOMEWORK = 100;
    public static final double TOTAL_POINTS_LAB = 10;
}
