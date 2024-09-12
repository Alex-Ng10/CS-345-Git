package grading;

import java.util.ArrayList;
import java.util.List;
import math.WeightedAverageCalculator;
import math.Calculator;
import math.LabeledDouble;
import math.SizeException;

/**
 * A Cohort represents a collection of History objects for a group of students, for example,
 * students in a specific major or course.
 * 
 * The class also provides functionality to calculate the overall average of the cohort using a
 * calculator.
 */
public class Cohort
{
  private String label;
  private List<History> histories;
  private Calculator calculator;

  /**
   * Constructor for Cohort. Initializes histories as an empty list and sets the calculator to a
   * WeightedAverageCalculator with null weights.
   * 
   * @param label
   *          The label of the cohort.
   */
  public Cohort(final String label)
  {
    this.histories = new ArrayList<>();
    this.calculator = new WeightedAverageCalculator();
    this.label = label;
  }

  /**
   * Adds a History object to the cohort.
   * 
   * @param history The history to be added.
   */
  public void add(final History history)
  {
    histories.add(history);
  }

  /**
   * Returns the label of the cohort.
   * 
   * @return The label of the cohort.
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Computes and returns the overall value (e.g., average GPA) for the cohort by calculating.
   * the average of all History objects in the cohort.
   * 
   * @return The LabeledDouble representing the average value of the cohort.
   * @throws SizeException if there are issues calculating the average (e.g., no data).
   */
  public LabeledDouble getValue() throws SizeException
  {
    List<LabeledDouble> values = new ArrayList<>();
    for (History history : histories)
    {
      values.add(history.getValue());
    }
    return calculator.calculate(label, values);
  }

  /**
   * Returns the list of History objects in the cohort.
   * 
   * @return The list of History objects.
   */
  public List<History> getHistories()
  {
    return histories;
  }
}
