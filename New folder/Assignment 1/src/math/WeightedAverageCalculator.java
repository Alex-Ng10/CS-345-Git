package math;

import java.util.List;
import java.util.Map;

/**
 * A calculator for computing the average weight of a list of LabeledDouble objects.
 * 
 * This class supports the the calculation of a weighted average
 * 
 * @see LabeledDouble
 * @see SizeException
 * @see Calculator
 * @see WeightedTotalCalculator
 */
public class WeightedAverageCalculator implements Calculator
{
  protected double denominator;
  protected Map<String, Double> weights;

  /**
   * Default constructor initializes with no weights and a denominator of 0.
   */
  public WeightedAverageCalculator()
  {
    this.denominator = 0.0;
    this.weights = null;
  }

  /**
   * Constructor that accepts a map of weights.
   * 
   * @param weights
   *          A map where the key is a label and the value is the corresponding weight
   */
  public WeightedAverageCalculator(final Map<String, Double> weights)
  {
    this.denominator = 0.0;
    this.weights = weights;
  }

  /**
   * Calculates the weighted average of the provided data.
   * 
   * @param resultLabel
   *          The label to use for the result
   * @param data
   *          A list of LabeledDouble objects to be averaged
   * @return A LabeledDouble representing the weighted average
   * @throws SizeException
   *           If the data list is null
   * @throws IllegalArgumentException
   *           If the denominator is 0
   */
  @Override
  public LabeledDouble calculate(final String resultLabel, final List<LabeledDouble> data)
      throws SizeException
  {
    if (data == null)
    {
      throw new SizeException("Data can't be null");
    }

    double numerator = performIntermediateCalculations(data);
    double weightedAverage;

    if (denominator == 0)
    {
      throw new IllegalArgumentException("Denominator can't be 0");
    }

    weightedAverage = numerator / denominator;

    return new LabeledDouble(resultLabel, weightedAverage);

  }

  /**
   * Performs the intermediate calculations required to compute the weighted average.
   * 
   * This method calculates the numerator and accumulates the denominator based on the weights
   * 
   * @param data
   *          A list of LabeledDouble objects to be processed
   * @return The computed numerator
   * @throws SizeException
   *           If the data list is invalid
   * @author Alex Nguyen
   */
  protected double performIntermediateCalculations(final List<LabeledDouble> data)
      throws SizeException
  {
    double numerator = 0.0;

    for (LabeledDouble item : data)
    {
      if (item == null || item.getValue() == null)
      {
        continue;
      }

      double value = item.getValue();
      String label = item.getLabel();
      double weight;

      if (weights != null && weights.containsKey(label))
      {
        weight = weights.get(label);
      }
      else
      {
        weight = 1.0;
      }

      numerator += value * weight;
      denominator += weight;
    }

    return numerator;
  }
}
