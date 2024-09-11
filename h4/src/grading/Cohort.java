package grading;

import java.util.ArrayList;
import java.util.List;
import math.WeightedAverageCalculator;
import math.Calculator;
import math.LabeledDouble;
import math.SizeException;

public class Cohort
{
  private String label;
  private List<History> histories;
  private Calculator calculator;
  
  /**
   * Constructor for Cohort.
   * Initializes histories as an empty list and sets the calculator to
   * a WeightedAverageCalculator with null weights.
   * 
   * @param label The label of the cohort.
   */
  public Cohort(String label) {
    if (label == null || label.isEmpty()) {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.histories = new ArrayList<>();
    this.calculator = new WeightedAverageCalculator();
    this.label = label;
  }
  
  public void add(History history) {
    if (history != null) {
      histories.add(history);
    }
  }
  
  public String getLabel() {
    return label;
  }
  
  public LabeledDouble getValue() throws SizeException {
    List<LabeledDouble> values = new ArrayList<>();
    for (History history : histories) {
      values.add(history.getValue());
    }
    return calculator.calculate(label, values);
  }
  
  public List<History> getHistories() {
    return histories;
  }
}
