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
    assertTrue(ld.getValue().equals(2.0));
  }
  
//
//  private WeightedAverageCalculator calculator;
//  private List<LabeledDouble> data;
//  private Map<String, Double> weights;
//
//  @BeforeEach
//  public void setUp()
//  {
//    weights = new HashMap<>();
//    weights.put("A", 2.0);
//    weights.put("B", 1.5);
//    weights.put("C", 1.0);
//
//    data = new ArrayList<>();
//    data.add(new LabeledDouble("A", 4.0));
//    data.add(new LabeledDouble("B", 3.0));
//    data.add(new LabeledDouble("C", 2.0));
//  }
//
//  @Test
//  public void testCalculateWithWeights() throws SizeException
//  {
//    calculator = new WeightedAverageCalculator(weights);
//    LabeledDouble result = calculator.calculate("Average", data);
//    assertEquals(3.1, result.getValue(), 0.01);
//    assertEquals("Average", result.getLabel());
//  }
//
//  @Test
//  public void testCalculateWithValidDataAndNoWeights() throws SizeException
//  {
//    calculator = new WeightedAverageCalculator();
//    LabeledDouble result = calculator.calculate("Average", data);
//    assertEquals(3.0, result.getValue(), 0.01);
//    assertEquals("Average", result.getLabel());
//  }
//
//  @Test
//  public void testCalculateWithNullDataThrowsException()
//  {
//    calculator = new WeightedAverageCalculator(weights);
//    Exception exception = assertThrows(SizeException.class, () -> {
//      calculator.calculate("Average", null);
//    });
//    assertEquals("Data can't be null", exception.getMessage());
//  }
//
//  @Test
//  public void testCalculateWithZeroDenominatorThrowsException()
//  {
//    calculator = new WeightedAverageCalculator();
//    List<LabeledDouble> emptyData = new ArrayList<>();
//    emptyData.add(new LabeledDouble("D", null));
//    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//      calculator.calculate("Average", emptyData);
//    });
//    assertEquals("Denominator can't be 0", exception.getMessage());
//  }

  // @Test
  // public void testPerformIntermediateCalculationsWithValidData() throws SizeException {
  // calculator = new WeightedAverageCalculator(weights);
  // double result = calculator.performIntermediateCalculations(data);
  // assertEquals(15.5, result, 0.01);
  // assertEquals(4.5, calculator.denominator, 0.01);
  // }
  
  

}
