package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.Numerics;

class NumericsTest {

  @Test
  public void testDoubleValueOf() {
    Double number = 5.5;
    double result = Numerics.doubleValueOf(number);
    assertEquals(5.5, result);
    
    Double number2 = null;
    double result2 = Numerics.doubleValueOf(number2);
    assertEquals(Numerics.DEFAULT_MISSING_VALUE, result2);
    
  }
  
  @Test
  public void testDoubleValueOfMissingValue() {
    Double number = 6.0;
    double missingValue = -1.0;
    double result = Numerics.doubleValueOf(number, missingValue);
    assertEquals(6.0, result);
    
    Double number2 = null;
    double missingValue2 = -1.0;
    double result2 = Numerics.doubleValueOf(number2, missingValue2);
    assertEquals(missingValue2, result2);
  }
  
  @Test
  public void testDoubleValueOflowerBound() {
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
  }

  @Test
  public void testSignum() {
      int result = Numerics.signum(10);
      assertEquals(1, result);
      
      int result2 = Numerics.signum(0);
      assertEquals(0, result2);
      
      int result3 = Numerics.signum(-10);
      assertEquals(-1, result3);
  }
}
