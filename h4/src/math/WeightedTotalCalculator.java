package math;

import java.util.*;

/**
 * A Calculator that calculates the weighted total of a List of LabeledNumber
 * objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public class WeightedTotalCalculator extends WeightedAverageCalculator 
{
  /**
   * Default Constructor.
   */
  public WeightedTotalCalculator() 
  {
    super(null);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param weights The Map of weights to use (or null for equal weights)
   */
  public WeightedTotalCalculator(final Map<String, Double> weights) 
  {
    super(weights);
  }

  /**
   * Calculate a weighted total from a List of LabeledDouble objects.
   *
   * Notes: 
   * 
   * (1) If the Map of weights is null then all weights are equal (i.e., 1).
   * (2) If there is no weight associated with a particular label then a weight of 0 is used.
   * (3) Missing LabeledDouble values are ignored. 
   * (4) If the List is null then this method throws a SizeException.
   * 
   * @param resultLabel   The label to use for the result
   * @param data  The List of LabeledNumber objects to use in the calculation
   * @return The resulting LabeledNumber
   * @throws SizeException if the List is null or empty
   */
  public LabeledDouble calculate(final String resultLabel, 
      final List<LabeledDouble> data) throws IllegalArgumentException, SizeException 
  {
    double total = super.performIntermediateCalculations(data);
    return new LabeledDouble(resultLabel, total);
  }

}
