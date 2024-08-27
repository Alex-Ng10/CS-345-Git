package math;

import java.util.Map;
import java.util.List;

/**
 * A calculator for computing the weighted total of a list of LabeledDouble objects
 * 
 * This class extends WeightedAverageCalculator by calculating the total of weighted values
 *
 * @see LabeledDouble
 * @see SizeException
 * @see Calculator
 * @see WeightedAverageCalculator
 * @author Alex Nguyen
 */
public class WeightedTotalCalculator extends WeightedAverageCalculator {

	/**
	 * Default constructor
	 */
	public WeightedTotalCalculator() {
		super(null);
	}
  
	/**
	 * Constructor accepting a map of weights
	 * @param weights
	 */
	public WeightedTotalCalculator(Map<String, Double> weights) {
		super(weights);
	}
	
	/**
	 * Calculates the weighted total of the given data
	 * @param resultLabel The label to use for the result
   * @param data A list of LabeledDouble objects to be summed
   * @return A LabeledDouble representing the weighted total
   * @throws IllegalArgumentException If the data list is null
   * @throws SizeException If the data list is invalid
	 */
	public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws IllegalArgumentException, SizeException {
		
		if (data == null) {
			throw new IllegalArgumentException("Result label can't be null");
		}
		
		double total = performIntermediateCalculations(data);
		
		return new LabeledDouble(resultLabel, total);
		
	} 
}
