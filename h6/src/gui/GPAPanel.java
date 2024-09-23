package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import math.CompositeLabeledDouble;
import math.WeightedAverageCalculator;

public class GPAPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private CompositeGradeSubject subject;
  private JLabel gpaLabel;

  public GPAPanel()
  {
    setLayout(new BorderLayout());
    gpaLabel = new JLabel("GPA: 0.000000");
    add(gpaLabel);
  }

  public void actionPerformed(ActionEvent evt)
  {
    if (subject != null)
    {
      CompositeLabeledDouble history = subject.getGradeHistory();
      WeightedAverageCalculator calculator = new WeightedAverageCalculator();
      double gpa = calculator.calculate(history);
      gpaLabel.setText(String.format("GPA: %.6f", gpa));
    }
  }

  public void setCompositeGradeSubject(CompositeGradeSubject subj)
  {
    this.subject = subj;
  }
}
