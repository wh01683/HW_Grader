import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Howerton on 2/16/2016.
 */
public class PP1HWEvaluation extends JFrame{
    private JTabbedPane tbHomework;
    private JPanel pnlMain;
    private JCheckBox chkCompError;
    private JCheckBox chkRuntimeError;
    private JTextField txtMaxPoints;
    private JTextField txtAssignmentTitle;
    private JTextField txtClassName;
    private JTextField txtSection;
    private JTextField txtResultGrade;
    private JTextField txtImplementationGrade;
    private JTextField txtStyleGrade;
    private JTextField txtResultComments;
    private JTextField txtImplementationComments;
    private JTextField txtStyleComments;
    private JTextField txtStudentName;
    private JButton btnClearStudent;
    private JButton btnSavePDF;
    private JTextArea txtInput;
    private JTextArea txtOutput;


    public PP1HWEvaluation(){
        btnClearStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearStudentEntries();
            }
        });

        btnSavePDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentRubricMaker.makePDF(makeEntry());
                //Creates a pdf with the student's info but does not automatically clear the information from the input fields.
                // the program user may want to create another entry with very similar information.
            }
        });

        this.setContentPane(pnlMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }



    public StudentEntry makeEntry(){

        String name = txtStudentName.getText(), compErr = (chkCompError.isSelected())? "Yes" : "No", runErr = (chkRuntimeError.isSelected())? "Yes" : "No",
                compErrComments = (chkCompError.isSelected())? "Does not compile." : "", runErrComments = (chkRuntimeError.isSelected())? "Run time error encountered." : "",
                resultComments = txtResultComments.getText(), implementationComments = txtImplementationComments.getText(), styleComments = txtStyleComments.getText(),
                programInput = txtInput.getText(), programOutput = processText(txtOutput.getText()),
                className = txtClassName.getText(), section = txtSection.getText(), assignmentName = txtAssignmentTitle.getText();

        System.out.printf(programOutput);

        int resultGivenPoints = Integer.parseInt(txtResultGrade.getText()), implementationGivenPoints = Integer.parseInt(txtImplementationGrade.getText()),
                styleGivenPoints = Integer.parseInt(txtStyleGrade.getText()), assignmentGrade = (resultGivenPoints + implementationGivenPoints + styleGivenPoints),
                assignmentTotalPoints = Integer.parseInt(txtMaxPoints.getText());

        StudentEntry temp = new StudentEntry(name, compErr, runErr,compErrComments, runErrComments, resultComments, implementationComments,
                styleComments, programInput, programOutput, className, section, assignmentName, resultGivenPoints, implementationGivenPoints, styleGivenPoints,
                assignmentGrade, assignmentTotalPoints);



        return temp;
    }


    /**
     * Processes text for compatibility with LaTeX by replacing \n new lines with \newline and \t with \hspace{1cm}.
     * This produces similar formatting effects in LaTeX.
     *
     * @param oldText Text to process.
     * @return Text with LaTeX compatible new lines and tabs.
     */
    private String processText(String oldText){

        String newText = "";
        System.out.printf("Newline found at position %d.", oldText.indexOf("\n"));
        String newline = new StringBuilder(" ").append("\\\\").append("newline").append(" ").toString();
        String tab = new StringBuilder(" ").append("\\\\").append("hspace{1cm}").append(" ").toString();
        System.out.printf("\nActual new line: %s\n", newline);


        newText = oldText.replaceAll("\\n", newline).replaceAll("\\t", tab);

        return newText;
    }

    private void clearStudentEntries(){
        chkRuntimeError.setSelected(false);
        chkCompError.setSelected(false);
        txtStudentName.setText("");
        txtResultGrade.setText("");
        txtStyleGrade.setText("");
        txtImplementationGrade.setText("");
        txtImplementationComments.setText("");
        txtStyleComments.setText("");
        txtResultComments.setText("");
        txtInput.setText("");
        txtOutput.setText("");
    }


}
