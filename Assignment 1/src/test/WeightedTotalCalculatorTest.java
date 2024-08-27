package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import math.LabeledDouble;
import math.SizeException;
import math.WeightedTotalCalculator;

class WeightedTotalCalculatorTest {
  
  @Test
  void testCalculate() {
    WeightedTotalCalculator calc = new WeightedTotalCalculator();
    List<LabeledDouble> list = new LinkedList<LabeledDouble>();
    list.add(new LabeledDouble("Label", 1.0));
    list.add(new LabeledDouble("Label2", 2.0));
    
    boolean test = false;
    try {
      calc.calculate("Result", null);
    } catch (SizeException e) {
      test = true;
    }
    assertTrue(test);
    
    LabeledDouble rd = calc.calculate("Return", list);
    assertEquals(0, new LabeledDouble("Return", 3.0).compareTo(rd));
  }

  @Test
  void testWeightedTotalCalculator() {
    boolean test = false;
    try {
      new WeightedTotalCalculator();
    } catch (Exception e) {
      test = true;
    }
    assertFalse(test);
  }
  
  @Test
  void testWeightedTotalCalculatorMap() {
    boolean test = false;
    try {
      new WeightedTotalCalculator(new HashMap<String, Double>());
    } catch (Exception e) {
      test = true;
    }
    assertFalse(test);
  }
}
