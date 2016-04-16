package forms;

import java.io.IOException;

import static util.Constants.*;
import static util.Functions.copyFile;

/**
 * Created by Howerton on 2/16/2016.
 */


public class TestMain {

    public static void main(String[] args){

        init();
        HomeworkEval test = new HomeworkEval();

    }

    public static void init(){

        try{

            //Setting up files in temp directory.
            System.out.println("Copying " + LOCAL_RUBRIC_TEMPLATE_TEX + " file to " + RUBRIC_TEMPLATE_TEX);
            copyFile(LOCAL_RUBRIC_TEMPLATE_TEX, RUBRIC_TEMPLATE_TEX);


            System.out.println("Copying " + LOCAL_UML_RUBRIC_TEMPLATE_TEX + " file to " + UML_RUBRIC_TEMPLATE_TEX);
            copyFile(LOCAL_UML_RUBRIC_TEMPLATE_TEX, UML_RUBRIC_TEMPLATE_TEX);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
