package math;

import java.util.Map;
import java.util.List;

public class WeightedTotalCalculator implements WeightedAverageCalculator {

	
	public WeightedTotalCalculator() {
		this.weights = null;
	}
  
	public WeightedTotalCalculator(Map<String, Double> weights) {
		this.weights = weights;
	}
	
	public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws IllegalArgumentException, SizeException {
		
		if (resultLabel == null || resultLabel.isEmpty()) {
			throw new IllegalArgumentException ("Result label can't be null / empty");
		}
		
		if (data == null) {
			throw new SizeException ("Data list cannot be null");
		}
		
		
	}
}
