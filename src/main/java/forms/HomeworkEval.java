package forms;

import entries.StudentEntry;
import entries.StudentEntryWithUML;
import entries.assignments.Assignment;
import entries.assignments.Homework;
import entries.assignments.Lab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Functions.processText;

/**
 * Created by Howerton on 2/16/2016.
 */
public class HomeworkEval extends JFrame{
    private JTabbedPane tbHomework;
    private JPanel pnlMain;
    private JCheckBox chkCompError;
    private JCheckBox chkRuntimeError;
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
    private JCheckBox chkReqUML;
    private JComboBox cbAssignmentType;
    private JTextField txtUMLComments;
    private JTextField txtUMLGrade;
    private JButton btnOverrideAssignment;

    private Assignment assignment;

    public HomeworkEval(){
        btnClearStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearStudentEntries();
            }
        });

        btnSavePDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(chkReqUML.isSelected()){
                    StudentEntryWithUML entry = makeUMLEntry();
                    entry.writePDF();
                }else{
                    StudentEntry entry = makeEntry();
                    entry.writePDF();
                }
                //Creates a pdf with the student's info but does not automatically clear the information from the input fields.
                // the program user may want to create another entry with very similar information.
            }
        });

        chkReqUML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean temp = chkReqUML.isSelected();
                txtUMLComments.setEnabled(temp);
                txtUMLGrade.setEnabled(temp);
            }
        });

        this.setContentPane(pnlMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

        btnOverrideAssignment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public StudentEntryWithUML makeUMLEntry(){

        String selected = getAssignmentTypeSelection();

        if(selected == null){
            JOptionPane.showMessageDialog(this, "Please make an assignment type selection.");
            cbAssignmentType.grabFocus();
            return null;
        }else {

            String name = txtStudentName.getText(), compErr = (chkCompError.isSelected()) ? "Yes" : "No", runErr = (chkRuntimeError.isSelected()) ? "Yes" : "No",
                    compErrComments = (chkCompError.isSelected()) ? "Does not compile." : "", runErrComments = (chkRuntimeError.isSelected()) ? "Run time error encountered." : "",
                    resultComments = txtResultComments.getText(), implementationComments = txtImplementationComments.getText(), styleComments = txtStyleComments.getText(),
                    programInput = txtInput.getText(), programOutput = processText(txtOutput.getText());

            System.out.printf(programOutput);

            double resultGivenPoints = Double.parseDouble(txtResultGrade.getText()), implementationGivenPoints = Double.parseDouble(txtImplementationGrade.getText()),
                    styleGivenPoints = Double.parseDouble(txtStyleGrade.getText()), assignmentGrade = (resultGivenPoints + implementationGivenPoints + styleGivenPoints);

            StudentEntryWithUML temp = new StudentEntryWithUML(makeAssignment(), name, compErr, runErr, compErrComments, runErrComments, resultComments, implementationComments,
                    styleComments, programInput, programOutput, resultGivenPoints, implementationGivenPoints, styleGivenPoints,
                    assignmentGrade, Double.parseDouble(txtUMLGrade.getText()), txtUMLComments.getText());


            return temp;
        }
    }

    public StudentEntry makeEntry(){

        String selected = getAssignmentTypeSelection();

        if(selected == null){
            JOptionPane.showMessageDialog(this, "Please make an assignment type selection.");
            cbAssignmentType.grabFocus();
            return null;
        }else {

            String name = txtStudentName.getText(), compErr = (chkCompError.isSelected()) ? "Yes" : "No", runErr = (chkRuntimeError.isSelected()) ? "Yes" : "No",
                    compErrComments = (chkCompError.isSelected()) ? "Does not compile." : "", runErrComments = (chkRuntimeError.isSelected()) ? "Run time error encountered." : "",
                    resultComments = txtResultComments.getText(), implementationComments = txtImplementationComments.getText(), styleComments = txtStyleComments.getText(),
                    programInput = txtInput.getText(), programOutput = processText(txtOutput.getText());

            System.out.printf(programOutput);

            double resultGivenPoints = Double.parseDouble(txtResultGrade.getText()), implementationGivenPoints = Double.parseDouble(txtImplementationGrade.getText()),
                    styleGivenPoints = Double.parseDouble(txtStyleGrade.getText()), assignmentGrade = (resultGivenPoints + implementationGivenPoints + styleGivenPoints);

            StudentEntry temp = new StudentEntry(makeAssignment(), name, compErr, runErr, compErrComments, runErrComments, resultComments,
                    implementationComments, styleComments, programInput, programOutput, resultGivenPoints, implementationGivenPoints,
                    styleGivenPoints, assignmentGrade);

            return temp;
        }
    }

    private String getAssignmentTypeSelection(){
        int selected = cbAssignmentType.getSelectedIndex();

        if(selected > 0){
            return cbAssignmentType.getSelectedItem().toString();
        }
        else{
            return null;
        }
    }

    private Assignment makeAssignment(){
        String type = getAssignmentTypeSelection();
        if(type == null){
            JOptionPane.showMessageDialog(this, "Please select assignment type.");
            cbAssignmentType.grabFocus();
            return null;
        }else{

            if(type.equalsIgnoreCase("HOMEWORK")){
                return new Homework(txtAssignmentTitle.getText(), txtClassName.getText(), txtSection.getText(), chkReqUML.isSelected());
            }else if(type.equalsIgnoreCase("LAB")){
                return new Lab(txtAssignmentTitle.getText(), txtClassName.getText(), txtSection.getText(), chkReqUML.isSelected());
            }else{
                JOptionPane.showMessageDialog(this, "Type " + type + " not found.");
                return null;
            }

        }
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
        txtUMLComments.setText("");
        txtUMLGrade.setText("");
    }


}
