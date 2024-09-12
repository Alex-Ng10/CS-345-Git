package math;

import java.util.*;

/**
 * A Calculator that calculates the weighted average of a List of LabeledNumber objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public class WeightedAverageCalculator implements Calculator
{
  private double denominator;
  private Map<String, Double> weights;

  /**
   * Default Constructor.
   */
  public WeightedAverageCalculator()
  {
    this(null); // Spec 2
  }

  /**
   * Explicit Value Constructor.
   *
   * @param weights
   *          The Map of weights to use (or null for equal weights)
   */
  public WeightedAverageCalculator(final Map<String, Double> weights)
  {
    this.weights = weights;
  }

  /**
   * Calculate a weighted average from a List of LabeledDouble objects.
   *
   * Notes: (1) If the Map of weights is null then all weights are equal (i.e., 1). (2) If there is
   * no weight associated with a label then a weight of 0 is used. (3) Missing LabeledDouble values
   * are ignored. (4) If the List is null then this method throws a SizeException.
   * 
   * @param resultLabel
   *          The label to use for the result
   * @param data
   *          The List of LabeledNumber objects to use in the calculation
   * @return The resulting LabeledNumber
   * @throws SizeException
   *           if the List is null or empty
   */
  public LabeledDouble calculate(final String resultLabel, final List<LabeledDouble> data)
      throws IllegalArgumentException, SizeException
  {
    double numerator = performIntermediateCalculations(data);

    if (denominator == 0.0)
      return new LabeledDouble(resultLabel, null);
    else
      return new LabeledDouble(resultLabel, numerator / denominator);
  }

  /**
   * Perform the intermediate calculations that are needed to find the weighted average.
   * Specifically, calculate the numerator (which is returned) and the denominator (which is stored
   * in an attribute) needed for the final calculation.
   * 
   * @param data
   *          The List of LabeledNumber objects to use in the calculation
   * @return The numerator (i.e., the weighted total)
   * @throws SizeException
   *           if the List is null
   */
  protected double performIntermediateCalculations(final List<LabeledDouble> data)
      throws SizeException
  {
    // Early return
    if (data == null)
      throw new SizeException("No Data"); // Spec 5.2.1

    double numerator = 0.0; // Local variable
    this.denominator = 0.0; // Attribute

    // Weight to use if weights is null
    double w = 1.0; // Spec 5.3.1

    for (LabeledDouble x : data)
    {
      if (weights != null)
        w = Numerics.doubleValueOf(weights.get(x.getLabel()), 0.0); // Spec 5.3.3

      // Only include non-missing values
      Double value = x.getValue();
      if (value != null) // Spec 5.2.2
      {
        numerator += w * Numerics.doubleValueOf(x.getValue());
        this.denominator += w; // Spec 6.2
      }
    }
    return numerator; // Spec 6.1
  }
}
