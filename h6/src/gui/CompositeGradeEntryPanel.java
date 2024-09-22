package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import math.CompositeLabeledDouble;

public class CompositeGradeEntryPanel extends JPanel implements CompositeGradeSubject {
    private static final long serialVersionUID = 1L;
    private String[] courses;
    private GradeEntryPanel[] gradePanels;

    public CompositeGradeEntryPanel(String[] courses, Map<String, Double> map) {
        this.courses = courses;
        setLayout(new GridLayout(courses.length, 1));
        gradePanels = new GradeEntryPanel[courses.length];

        for (int i = 0; i < courses.length; i++) {
            String course = courses[i];
            double credits = map.get(course);
            gradePanels[i] = new GradeEntryPanel(course, credits);
            add(gradePanels[i]);
        }
    }

    // Adds an ActionListener to each GradeEntryPanel
    public void addActionListener(ActionListener listener) {
        for (GradeEntryPanel panel : gradePanels) {
            panel.addActionListener(listener);
        }
    }

    // Resets each GradeEntryPanel
    public void reset() {
        for (GradeEntryPanel panel : gradePanels) {
            panel.reset();
        }
    }

    // Constructs and returns a CompositeLabeledDouble object from grade history
    public CompositeLabeledDouble getGradeHistory() {
        CompositeLabeledDouble history = new CompositeLabeledDouble();
        for (GradeEntryPanel panel : gradePanels) {
            String grade = panel.getGrade();
            double credit = Double.parseDouble(panel.creditLabel.getText());
            history.add(grade, credit);
        }
        return history;
    }
}
