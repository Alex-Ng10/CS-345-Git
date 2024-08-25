package math;
import java.util.List;
import java.util.Map;

public class WeightedAverageCalculator implements Calculator {
  protected double denominator;
  protected Map<String, Double> weights;
  
  public WeightedAverageCalculator() {
	  this.denominator = 0.0;
	  this.weights = null;
  }
  
  public WeightedAverageCalculator(Map<String, Double> weights) {
	  this.denominator = 0.0;
	  this.weights = weights;
  }
  
  @Override 
  public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws SizeException {
	  if (data == null) {
		  throw new SizeException("Data can't be null");
	  }
	  
	  double numerator = performIntermediateCalculations(data);
	  double weightedAverage;
	  
	  if (denominator == 0) {
		  throw new IllegalArgumentException("Denominator can't be 0");
	  } 
	  
	  weightedAverage = numerator / denominator;
	  
	  return new LabeledDouble(resultLabel, weightedAverage);
	  
  }
  
  protected double performIntermediateCalculations(List<LabeledDouble> data) throws SizeException {
	  double numerator = 0.0;
	  
	  for (LabeledDouble item : data) {
		  if (item.getValue() == null) {
			 continue;
		  }
		  
		  double value = item.getValue();
		  String label = item.getLabel();
		  double weight;
		  
		  
		  if (weights != null && weights.containsKey(label)) {
        weight = weights.get(label);	 
		  } else {
			  weight = 1.0;
		  }
		  
		  numerator += value * weight;
		  denominator += weight;
	  }
	  
	  return numerator;
  }
}
  
