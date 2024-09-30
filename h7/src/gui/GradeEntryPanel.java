package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import grading.LetterGrade;

/**
 * GradeEntryPanel is responsible for displaying a grade entry form for a specific course. It allows
 * users to select a grade from a drop-down menu and displays the course's credit value.
 * 
 * Each panel corresponds to one course, showing its name, credits, and a grade selector.
 */
public class GradeEntryPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private JComboBox<String> gradeField;
  private JLabel creditLabel;
  private String course;
  private final String notApplicable = "N/A";

  /**
   * Constructor to initialize the panel for a course and its corresponding credits. Creates a
   * JComboBox for grade selection and a label for credit display.
   * 
   * @param course
   *          The course name for this panel
   * @param credits
   *          The credit value of the course
   */
  public GradeEntryPanel(final String course, final double credits)
  {
    this.course = course;

    // Initialize grade drop-down menu
    gradeField = new JComboBox<String>();
    gradeField.setMaximumSize(new Dimension(50, 25));

    LetterGrade[] grades = LetterGrade.values();
    for (int i = grades.length - 1; i >= 0; --i)
    {
      gradeField.addItem(grades[i].getLabel());
    }
    gradeField.addItem(notApplicable);

    // Create label displaying the number of credits
    creditLabel = new JLabel((int) credits + " credits");

    // Set up the layout and borders for the panel
    BorderLayout layout = new BorderLayout();
    this.setLayout(layout);
    this.setBorder(BorderFactory.createTitledBorder(course));

    // Add the grade selector at the top and the credits label at the bottom
    this.add(gradeField, BorderLayout.NORTH);
    this.add(creditLabel, BorderLayout.SOUTH);

    // Set a fixed preferred size for the panel
    this.setPreferredSize(new Dimension(100, 100));
  }

  /**
   * Adds an ActionListener to the grade selection drop-down menu. This allows the panel to notify
   * listeners when a grade is selected.
   * 
   * @param listener
   *          The ActionListener to attach
   */
  public void addActionListener(final ActionListener listener)
  {
    gradeField.addActionListener(listener);
  }

  /**
   * Retrieves the currently selected grade from the drop-down menu.
   * 
   * @return The selected grade as a string
   */
  public String getGrade()
  {
    return gradeField.getSelectedItem().toString();
  }

  /**
   * Returns the name of the course associated with this panel.
   * 
   * @return The course name
   */
  public String getCourse()
  {
    return this.course;
  }

  /**
   * Resets the grade selection to "N/A", indicating no grade has been selected.
   */
  public void reset()
  {
    gradeField.setSelectedItem(notApplicable);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(100, 100);
  }
}
