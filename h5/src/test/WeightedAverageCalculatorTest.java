package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.LabeledDouble;
//import math.Calculator;
import math.LeafLabeledDouble;
import math.SizeException;
import java.util.*;
import math.WeightedAverageCalculator;

/**
 * Unit tests for the WeightedAverageCalculator class.
 * 
 * This test class verifies the behavior of the WeightedAverageCalculator, ensuring that it
 * correctly calculates the weighted average of a list of LabeledDouble objects.
 * 
 * @author Alex Nguyen
 */
public class WeightedAverageCalculatorTest
{

  private static String label1 = "Label1";
  private static String label2 = "Label2";
  private static String label3 = "Label3";
  private WeightedAverageCalculator calc;

  /**
   * setUp before each test.
   */
  @BeforeEach
  public void setUp()
  {
    calc = new WeightedAverageCalculator(null);
  }

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
   * Ensures that the calculator is instantiated without throwing exceptions, even when an empty map
   * is provided.
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
   * Verifies that the calculator correctly handles null lists, empty lists, and lists with null
   * values, and correctly applies weights when provided.
   */
  @Test
  public void testCalculate()
  {
    WeightedAverageCalculator calc = new WeightedAverageCalculator();

    List<LabeledDouble> list = null;
    boolean test = false;
    try
    {
      calc.calculate(label1, list);
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
      calc.calculate(label1, list);
    }
    catch (IllegalArgumentException e)
    {
      test = true;
    }
    assertFalse(test);
  }

  @Test
  public void testCalculateWithNonNullValues() throws SizeException
  {
    List<LabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(label1, 5.0));
    data.add(new LeafLabeledDouble(label2, 10.0));

    LabeledDouble result = calc.calculate(label3, data);

    assertEquals(7.5, result.getValue(), 0.001,
        "Weighted average should be 7.5 with default weights.");
  }

  @Test
  public void testCalculateWithNullValues() throws SizeException
  {
    List<LabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(label1, 5.0));
    data.add(new LeafLabeledDouble(label2, null));

    LabeledDouble result = calc.calculate(label3, data);

    assertEquals(5.0, result.getValue(), 0.001,
        "Weighted average should be 5.0 with default weights.");
  }

  /**
   * Tests the calculate method when weights are applied correctly.
   * 
   * Verifies that the correct weights are applied when the weights map contains the key.
   */
  @Test
  public void testCalculateWithWeights()
  {
    Map<String, Double> weights = new HashMap<>();
    weights.put(label1, 2.0);
    weights.put(label2, 0.5);

    WeightedAverageCalculator calc = new WeightedAverageCalculator(weights);

    List<LabeledDouble> list = new LinkedList<>();
    list.add(new LeafLabeledDouble(label1, 4.0));
    list.add(new LeafLabeledDouble(label2, 2.0));

    LabeledDouble ld = calc.calculate(label3, list);

    assertEquals(3.6, ld.getValue(), 0.001);
  }

  /**
   * Tests the calculate method with weights where the label does not exist in the weights map.
   * 
   * Verifies that the default weight of 1.0 is applied when the label is not present in the weights
   * map.
   */
  @Test
  public void testCalculateWithMissingWeightLabel()
  {
    Map<String, Double> weights = new HashMap<>();
    weights.put(label1, 2.0);

    WeightedAverageCalculator calc = new WeightedAverageCalculator(weights);

    List<LabeledDouble> list = new LinkedList<>();
    list.add(new LeafLabeledDouble(label1, 3.666));
    list.add(new LeafLabeledDouble(label2, 3.0));

    LabeledDouble ld = calc.calculate(label3, list);

    assertEquals(3.666, ld.getValue(), 0.001);
  }
}
