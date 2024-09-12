package grading;

import java.util.ArrayList;
import java.util.List;

import math.Calculator;
import math.Filter;
import math.LabeledDouble;
import math.SizeException;

/**
 * The History class represents a student's grades for multiple courses. It uses a Calculator to
 * compute a final result and a Filter to filter the data.
 */
public class History
{
  private String label;
  private List<LabeledDouble> data;
  private Calculator calculator;
  private Filter filter;

  /**
   * Constructs a History object.
   * 
   * @param label
   *          The label for the history (e.g., student's name or identifier).
   * @param filter
   *          The filter to apply to the history data.
   * @param calculator
   *          The calculator to use for final computations.
   * @throws IllegalArgumentException
   *           if the label is null or empty.
   */
  public History(final String label, final Filter filter, final Calculator calculator)
      throws IllegalArgumentException
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
    this.filter = filter;
    this.calculator = calculator;
    this.data = new ArrayList<>();
  }

  /**
   * Adds a LabeledDouble item (representing a course grade) to the history.
   * 
   * @param item
   *          The LabeledDouble item to add.
   */
  public void add(final LabeledDouble item)
  {
    if (item != null)
    {
      data.add(item);
    }
  }

  /**
   * Applies the filter to the data and returns a filtered list of LabeledDouble objects.
   * 
   * @return A filtered list of LabeledDouble objects.
   * @throws SizeException
   *           if filtering fails or the data is invalid.
   */
  public List<LabeledDouble> filter() throws SizeException
  {
    if (filter == null)
    {
      return new ArrayList<>(data);
    }
    return filter.apply(data);
  }

  /**
   * Returns the label of the history.
   * 
   * @return The label of the history.
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Uses the calculator to compute the value of the history (e.g., GPA) after filtering the data.
   * 
   * @return The computed value as a LabeledDouble.
   * @throws SizeException
   *           if there are issues in the calculation.
   */
  public LabeledDouble getValue() throws SizeException
  {
    return calculator.calculate(label, filter());
  }
}
