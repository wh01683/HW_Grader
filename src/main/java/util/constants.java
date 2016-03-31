package util;

import java.io.File;

/**
 * Created by Howerton on 3/30/2016.
 */
public class Constants {
    public static final String SEMESTER = "Spring 2016";
    public static final String WORKING_DIR = "C:\\Users\\Howerton\\Documents\\grading_template\\";
    public static final File WORKING_DIR_FILE = new File(WORKING_DIR);
    public static final File RUBRIC_TEMPLATE_TEX = new File(WORKING_DIR_FILE.getAbsolutePath().concat("\\rubric_template.tex"));
    public static final File UML_RUBRIC_TEMPLATE_TEX = new File(WORKING_DIR_FILE.getAbsolutePath().concat("\\uml_rubric.tex"));
    public static final File OUTPUT_DIR = new File("C:\\Users\\Howerton\\Desktop\\pdfs_test\\");

    public static final float RESULTS_GRADE_PERCENTAGE = .4f;
    public static final float IMPLEMENTATION_GRADE_PERCENTAGE = .4f;
    public static final float STYLE_GRADE_PERCENTAGE = .2f;
    public static final float UML_GRADE_PERCENTAGE = .3f;

    public static final double TOTAL_POINTS_HOMEWORK = 100;
    public static final double TOTAL_POINTS_LAB = 10;

}
