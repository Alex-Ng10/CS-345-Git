package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import math.Calculator;
import math.LabeledDouble;
import math.SizeException;
import java.util.*;
import math.WeightedAverageCalculator;

/**
 * Unit tests for the WeightedAverageCalculator class.
 * 
 * This test class verifies the behavior of the WeightedAverageCalculator,
 * ensuring that it correctly calculates the weighted average of a list of LabeledDouble objects.
 * 
 * @author Alex Nguyen
 */
public class WeightedAverageCalculatorTest
{

  private static String Label1 = "Label1";
  private static String Label2 = "Label2";
  private static String Label3 = "Label3";
  
  /**
   * Tests the default constructor of WeightedAverageCalculator.
   * 
   * Ensures that the calculator is instantiated without throwing exceptions.
   */
  @Test
  public void testWeightedAverageCalculator()
  {
    boolean test = false;
    try
    {
      new WeightedAverageCalculator();
    }
    catch (IllegalArgumentException e)
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
  public void testWeightedAverageCalculatorMapStringDouble()
  {
    boolean test = false;
    try
    {
      new WeightedAverageCalculator(new HashMap<String, Double>());
    }
    catch (IllegalArgumentException e)
    {
      test = true;
    }
    assertFalse(test);
  }

  /**
   * Tests the calculate method with various scenarios.
   * 
   * Verifies that the calculator correctly handles 
   * null lists, empty lists, and lists with null values,
   * and correctly applies weights when provided.
   */
  @Test
  public void testCalculate()
  {
    WeightedAverageCalculator calc = new WeightedAverageCalculator();

    List<LabeledDouble> list = null;
    boolean test = false;
    try
    {
      calc.calculate(Label1, list);
    }
    catch (SizeException e)
    {
      test = true;
    }
    assertTrue(test);

    list = new LinkedList<LabeledDouble>();
    test = false;
    try
    {
      calc.calculate(Label1, list);
    }
    catch (IllegalArgumentException e)
    {
      test = true;
    }
    assertTrue(test);

    list.add(new LabeledDouble(Label1, 3.0));
    list.add(new LabeledDouble(Label2, null));

    LabeledDouble ld = calc.calculate(Label3, list);
    assertTrue(ld.getValue().equals(3.0));

    Map<String, Double> weights = new HashMap<String, Double>();
    weights.put("Weights", 2.0);
    weights.put("Weights2", 1.0);
    weights.put("Label", 1.0);

    calc = new WeightedAverageCalculator(weights);
    list.add(null);

    ld = calc.calculate(Label3, list);
    assertTrue(ld.getValue().equals(3.0));

    weights = null;

    ld = calc.calculate(Label3, list);
    assertTrue(ld.getValue().equals(1.5));
  }

}
