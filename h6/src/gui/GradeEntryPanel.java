package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GradeEntryPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JComboBox<String> gradeField;
  private JLabel creditLabel;
  
  public GradeEntryPanel(String course, double credits) {
    setLayout(new FlowLayout());
    
    gradeField = new JComboBox<>(new String[]{"N/A", "A", "B", "c", "D", "F"});
    creditLabel = new JLabel(String.valueOf(credits));
    
    add(new JLabel(course));
    add(gradeField);
    add(creditLabel);
  }
  
  public void addActionListener(ActionListener listener) {
    gradeField.addActionListener(listener);
  }
  
  public String getGrade() {
    return (String) gradeField.getSelectedItem();
  }
  
  public void reset() {
    gradeField.setSelectedIndex(0);
  }
}
