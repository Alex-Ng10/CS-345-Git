package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import grading.JMUCourseTable;
import grading.LetterGrade;
import math.CompositeLabeledDouble;
import math.LeafLabeledDouble;
import math.WeightedAverageCalculator;

/**
 * CompositeGradeEntryPanel is responsible for displaying multiple GradeEntryPanels for different
 * courses, and managing the collection of grades entered.
 */
public class CompositeGradeEntryPanel extends JPanel implements CompositeGradeSubject
{
  private static final long serialVersionUID = 1L;
  private ArrayList<GradeEntryPanel> panels;

  /**
   * Constructor to initialize the panel with a set of courses and their corresponding credits. It
   * creates GradeEntryPanels for each course.
   * 
   * @param courses
   *          Array of course names to display
   * @param map
   *          Map of course names to their corresponding credit values
   */
  public CompositeGradeEntryPanel(final String[] courses, final Map<String, Double> map)
  {

    panels = new ArrayList<GradeEntryPanel>();
    this.setLayout(new GridLayout());

    for (String course : courses)
    {
      if (map.containsKey(course))
      {
        double credits = map.get(course);
        GradeEntryPanel panel = new GradeEntryPanel(course, credits);
        panels.add(panel);
        this.add(panel);
      }
    }
  }

  /**
   * Adds an ActionListener to all the GradeEntryPanel components.
   * 
   * @param listener The ActionListener to attach
   */
  public void addActionListener(final ActionListener listener)
  {
    for (GradeEntryPanel panel : panels)
    {
      panel.addActionListener(listener);
    }
  }

  /**
   * Resets all grade entries to "N/A" in all GradeEntryPanels.
   */
  public void reset()
  {
    for (GradeEntryPanel panel : panels)
    {
      panel.reset();
    }
  }

  @Override
  public CompositeLabeledDouble getGradeHistory()
  {
    JMUCourseTable courseTable = new JMUCourseTable();
    WeightedAverageCalculator wac = new WeightedAverageCalculator(courseTable);
    CompositeLabeledDouble history = new CompositeLabeledDouble("history", null, wac);

    for (GradeEntryPanel panel : panels)
    {
      LetterGrade grade = LetterGrade.fromCode(panel.getGrade());
      LeafLabeledDouble element;
      if (grade == null)
      {
        element = new LeafLabeledDouble(panel.getCourse(), null);
      }
      else
      {
        element = new LeafLabeledDouble(panel.getCourse(), grade.getValue());
      }
      history.add(element);
    }
    return history;
  }
}
