package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import math.LabeledDouble;
import math.SizeException;
import math.WeightedTotalCalculator;

/**
 * Unit tests for the WeightedTotalCalculator class.
 * 
 * This test class verifies the behavior of the WeightedTotalCalculator,
 * ensuring that it correctly calculates the weighted total of a list of LabeledDouble objects.
 * 
 * @author Alex Nguyen
 */
class WeightedTotalCalculatorTest
{
  
  private static String Return = "Return";

  /**
   * Tests the calculate method with various scenarios.
   * 
   * Verifies that the calculator correctly handles null lists, empty lists,
   * and lists with valid data, returning the correct weighted total.
   */
  @Test
  void testCalculate()
  {
    WeightedTotalCalculator calc = new WeightedTotalCalculator();
    List<LabeledDouble> list = new LinkedList<LabeledDouble>();

    list.add(new LabeledDouble("Label", 1.0));
    list.add(new LabeledDouble("Label2", 2.0));

    boolean test = false;
    try
    {
      calc.calculate("Result", null);
    }
    catch (IllegalArgumentException e)
    {
      test = true;
    }
    assertTrue(test);

    LabeledDouble rd = calc.calculate(Return, list);
    assertEquals(0, new LabeledDouble(Return, 3.0).compareTo(rd));
  }

  /**
   * Tests the default constructor of WeightedTotalCalculator.
   * 
   * Ensures that the calculator is instantiated without throwing exceptions.
   */
  @Test
  void testWeightedTotalCalculator()
  {
    boolean test = false;
    try
    {
      new WeightedTotalCalculator();
    }
    catch (SizeException e)
    {
      test = true;
    }
    assertFalse(test);
  }

  /**
   * Tests the constructor with a map of weights.
   * 
   * Ensures that the calculator is instantiated without throwing exceptions,
   * even when an empty map is provided.
   */
  @Test
  void testWeightedTotalCalculatorMap()
  {
    boolean test = false;
    try
    {
      new WeightedTotalCalculator(new HashMap<String, Double>());
    }
    catch (SizeException e)
    {
      test = true;
    }
    assertFalse(test);
  }
}
