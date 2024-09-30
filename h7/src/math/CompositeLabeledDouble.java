package math;

import java.util.ArrayList;
import java.util.List;

/**
 * CompositeLabeledDouble is a composite class that manages a collection of LabeledDouble objects.
 * It allows filtering and calculating values based on the provided Filter and Calculator objects.
 * 
 * This class extends AbstractLabeledDouble and implements the logic for filtering the data and
 * calculating a composite value using external filtering and calculation strategies.
 * 
 */
public class CompositeLabeledDouble extends AbstractLabeledDouble
{
  private Filter filter;
  private Calculator calculator;
  private List<LabeledDouble> data;

  /**
   * Constructor for CompositeLabeledDouble.
   * 
   * Initializes the composite object with a label, filter, and calculator.
   * 
   * @param label
   *          The label for this CompositeLabeledDouble (must not be null or empty)
   * @param filter
   *          The Filter to be used for filtering the data (can be null)
   * @param calculator
   *          The Calculator used to compute a value based on the filtered data (can be null)
   * @throws IllegalArgumentException
   *           If the label is null or empty
   */
  public CompositeLabeledDouble(final String label, final Filter filter,
      final Calculator calculator)
  {

    super(label);
    this.filter = filter;
    this.calculator = calculator;
    this.data = new ArrayList<>();
  }

  /**
   * Adds a LabeledDouble object to the data collection.
   * 
   * @param labeledDouble
   *          The LabeledDouble object to add
   */
  public void add(final LabeledDouble labeledDouble)
  {
    data.add(labeledDouble);
  }

  /**
   * Filters the list of LabeledDouble objects using the specified Filter.
   * 
   * If the filter is null, it returns the unfiltered data. Otherwise, it applies the filter to the
   * data.
   * 
   * @return A filtered list of LabeledDouble objects or the original list if no filter is provided.
   */
  public List<LabeledDouble> filter()
  {
    if (this.filter == null)
    {
      return data;
    }
    return filter.apply(data);

  }

  /**
   * Calculates the value of this CompositeLabeledDouble using the Filter and Calculator.
   * 
   * - First, the data is filtered using the Filter (if one is provided). - Then, the Calculator (if
   * one is provided) is used to compute a value based on the filtered data. - If there is no
   * Calculator, it returns null.
   * 
   * @return The computed value or null if no Calculator is provided.
   */
  @Override
  public Double getValue()
  {
    this.data = filter();

    if (this.calculator == null)
    {
      return null;
    }

    return this.calculator.calculate(getLabel(), this.data).getValue();
  }
}
