package math;

import java.util.Map;
import java.util.List;

public class WeightedTotalCalculator extends WeightedAverageCalculator {

	
	public WeightedTotalCalculator() {
		super(null);
	}
  
	public WeightedTotalCalculator(Map<String, Double> weights) {
		super(weights);
	}
	
	public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws IllegalArgumentException, SizeException {
		
		if (data == null) {
			throw new IllegalArgumentException("Result label can't be null");
		}
		
		double total = performIntermediateCalculations(data);
		
		return new LabeledDouble(resultLabel, total);
		
	} 
}
