package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import math.Calculator;
import math.LabeledDouble;
import math.SizeException;
import java.util.*;
import math.WeightedAverageCalculator;

public class WeightedAverageCalculatorTest {
  
  @Test
  public void testWeightedAverageCalculator() {
    boolean test = false;
    try {
      new WeightedAverageCalculator();
    } catch (Exception e) {
      test = true;
    } 
    assertFalse(test);
  }
  
  @Test
  public void testWeightedAverageCalculatorMapStringDouble() {
    boolean test = false;
    try {
      new WeightedAverageCalculator(new HashMap<String, Double>());
    } catch (Exception e) {
      test = true;
    } 
    assertFalse(test);
  }
  
  @Test
  public void testCalculate() {
    WeightedAverageCalculator calc = new WeightedAverageCalculator();
    
    List<LabeledDouble> list = null;
    boolean test = false;
    try {
      calc.calculate("Label", list);
    } catch (Exception e) {
      test = true;
    }
    assertTrue(test);
    
    list = new LinkedList<LabeledDouble>();
    test = false;
    try {
      calc.calculate("Label", list);
    } catch (IllegalArgumentException e) {
      test = true;
    }
    assertTrue(test);
    
    list.add(new LabeledDouble("Label", 3.0));
    list.add(new LabeledDouble("Label2", null));
    
    LabeledDouble ld = calc.calculate("Label 3", list);
    assertTrue(ld.getValue().equals(3.0));
    
    Map<String, Double> weights = new HashMap<String, Double>();
    weights.put("Weights", 2.0);
    weights.put("Weights2", 1.0);
    weights.put("Label", 1.0);
    
    calc = new WeightedAverageCalculator(weights);
    list.add(null);
    
    ld = calc.calculate("Label3", list);
    assertTrue(ld.getValue().equals(3.0));
    
    weights = null; 
    
    ld = calc.calculate("Label3", list);
    assertTrue(ld.getValue().equals(1.5));
  }

}
