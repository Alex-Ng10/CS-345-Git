package gui;

import grading.LetterGrade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GradeEntryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> gradeField;
    private JLabel creditLabel;

    public GradeEntryPanel(String course, double credits) {
        setLayout(new BorderLayout());

        // Populate JComboBox with LetterGrade values
        gradeField = new JComboBox<>();
        gradeField.addItem("N/A");  
        for (LetterGrade grade : LetterGrade.values()) {
            gradeField.addItem(grade.getLabel());
        }

        creditLabel = new JLabel(String.valueOf(credits));

        add(new JLabel(course), BorderLayout.WEST);
        add(gradeField, BorderLayout.CENTER);
        add(creditLabel, BorderLayout.EAST);
    }

    // Add action listener for grade selection changes
    public void addActionListener(ActionListener listener) {
        gradeField.addActionListener(listener);
    }

    // Return the selected grade as a LetterGrade object
    public LetterGrade getGrade() {
        String selectedGrade = (String) gradeField.getSelectedItem();
        return LetterGrade.fromCode(selectedGrade);
    }

    // Reset grade selection to "N/A"
    public void reset() {
        gradeField.setSelectedItem("N/A");
    }
}
