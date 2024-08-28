package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import math.LabeledDouble;
import math.SizeException;
import math.WeightedTotalCalculator;

/**
 * Unit tests for the WeightedTotalCalculator class.
 * 
 * This test class verifies the behavior of the WeightedTotalCalculator, ensuring that it correctly
 * calculates the weighted total of a list of LabeledDouble objects.
 * 
 * @author Alex Nguyen
 */
class WeightedTotalCalculatorTest
{

  private static String Return = "Return";
  private static String Label1 = "Label1";
  private static String Label2 = "Label2";

  /**
   * Tests the calculate method with a null data list.
   * 
   * Verifies that the calculator throws a SizeException when the data list is null.
   */
  @Test
  void testNullListWithGoodMap()
  {
    WeightedTotalCalculator calc = new WeightedTotalCalculator(new HashMap<String, Double>());
    assertThrows(SizeException.class, () -> calc.calculate(Return, null),
        "Null list should throw SizeException");
  }

  /**
   * Tests the calculate method with a null data list and null map.
   * 
   * Verifies that the calculator throws a SizeException when both the data list and weights map are
   * null.
   */
  @Test
  void testNullListWithNullMap()
  {
    WeightedTotalCalculator calc = new WeightedTotalCalculator(null);
    assertThrows(SizeException.class, () -> calc.calculate(Return, null),
        "Null list with null map should throw SizeException");
  }

  /**
   * Tests the calculate method with a list that has a missing weight.
   * 
   * Verifies that the calculator correctly applies a default weight of 1.0 when the weight is
   * missing.
   */
  @Test
  void testMissingWeight01()
  {
    Map<String, Double> weights = new HashMap<>();
    weights.put(Label1, 2.0);

    WeightedTotalCalculator calc = new WeightedTotalCalculator(weights);
    List<LabeledDouble> list = new LinkedList<>();
    list.add(new LabeledDouble(Label1, 4.0));
    list.add(new LabeledDouble(Label2, 3.0));

    LabeledDouble ld = calc.calculate(Return, list);
    assertEquals(11.0, ld.getValue(), 0.001,
        "Missing weight should default to 1.0 and total should be 11.0 ");
  }

  /**
   * Tests the calculate method with a list that has a missing weight (scenario 2).
   * 
   * Verifies that the calculator correctly applies a default weight of 1.0 when the weight is
   * missing.
   */
  @Test
  void testMissingWeight02()
  {
    Map<String, Double> weights = new HashMap<>();
    weights.put(Label1, 2.0);

    WeightedTotalCalculator calc = new WeightedTotalCalculator(weights);
    List<LabeledDouble> list = new LinkedList<>();
    list.add(new LabeledDouble(Label1, 4.0));
    list.add(new LabeledDouble("Label3", 3.0));

    LabeledDouble ld = calc.calculate(Return, list);
    assertEquals(11.0, ld.getValue(), 0.001,
        "Missing weight should default to 1.0 and total should be 11.0");
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
   * Ensures that the calculator is instantiated without throwing exceptions, even when an empty map
   * is provided.
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
