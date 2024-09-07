package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.Numerics;

/**
 * Unit tests for the Numerics class.
 * 
 * This test class verifies the functionality of the Numerics utility methods, including methods for
 * converting Double objects to primitive doubles, handling missing values, applying bounds, and
 * determining the sign of an integer.
 * 
 * @author Alex Nguyen
 */
class NumericsTest
{

  /**
   * Ensure that the Numeric class can be instantiated without throwing exceptions.
   */
  @Test
  public void testDefault()
  {
    boolean num = false;
    try
    {
      new Numerics();
    }
    catch (IllegalArgumentException e)
    {
      num = true;
    }
    assertFalse(num);
  }

  /**
   * Test the doubleValueOf method with a non-null Double. Ensures that the method returns the
   * correct primitive double value when a non-null Double is provided.
   */
  @Test
  public void testDoubleValueOf()
  {
    Double number = 5.5;
    double result = Numerics.doubleValueOf(number);
    assertEquals(5.5, result);

    Double number2 = null;
    double result2 = Numerics.doubleValueOf(number2);
    assertEquals(Numerics.DEFAULT_MISSING_VALUE, result2);

  }

  /**
   * Tests the doubleValueOf method with a specified missing value.
   * 
   * Ensures that the method returns the correct value when a non-null Double is provided, and the
   * missing value when the Double is null.
   */
  @Test
  public void testDoubleValueOfMissingValue()
  {
    Double number = 6.0;
    double missingValue = -1.0;
    double result = Numerics.doubleValueOf(number, missingValue);
    assertEquals(6.0, result);

    Double number2 = null;
    double missingValue2 = -1.0;
    double result2 = Numerics.doubleValueOf(number2, missingValue2);
    assertEquals(missingValue2, result2);
  }

  /**
   * Tests the doubleValueOf method with a lower bound.
   * 
   * Ensures that the method returns the correct value when the Double is above the lower bound, and
   * the missing value when the Double is null or below the lower bound.
   */
  @Test
  public void testDoubleValueOflowerBound()
  {
    Double number = 10.0;
    double missingValue = -1.0;
    double lowerBound = 5.0;
    double result = Numerics.doubleValueOf(number, missingValue, lowerBound);
    assertEquals(10.0, result);

    Double number2 = null;
    double missingValue2 = -1.0;
    double lowerBound2 = 5.0;
    double result2 = Numerics.doubleValueOf(number2, missingValue2, lowerBound2);
    assertEquals(missingValue2, result2);

    Double number3 = 4.0;
    double missingValue3 = -1.0;
    double lowerBound3 = 5.0;
    double result3 = Numerics.doubleValueOf(number3, missingValue3, lowerBound3);
    assertEquals(missingValue3, result3);
  }

  /**
   * Tests the signum method.
   * 
   * Ensures that the correct sign is returned for positive, zero, and negative integers.
   */
  @Test
  public void testSignum()
  {
    int result = Numerics.signum(10);
    assertEquals(1, result);

    int result2 = Numerics.signum(0);
    assertEquals(0, result2);

    int result3 = Numerics.signum(-10);
    assertEquals(-1, result3);
  }
}
