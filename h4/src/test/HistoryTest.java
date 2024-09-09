package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grading.History;
import math.Calculator;
import math.Filter;
import math.LabeledDouble;
import math.SizeException;
import math.ThresholdFilter;
import math.WeightedAverageCalculator;

/**
 * Unit test for the History Class.
 * 
 * This test class tests constructors, the filter method and the GetValue method
 */
class HistoryTest
{

  private Filter filterTest;
  private Calculator calculatorTest;
  History historyTest;
  private String label = "label";
  LabeledDouble testBelow;
  LabeledDouble testEqual;
  LabeledDouble testAbove;
  private String exceptionMessage = "Label cannot be null or empty";

  @BeforeEach
  public void setUp()
  {

    filterTest = new ThresholdFilter(85.0, -1, 0, 1);
    calculatorTest = new WeightedAverageCalculator();
    historyTest = new History(label, filterTest, calculatorTest);

    testBelow = new LabeledDouble(label, 78.0);
    testEqual = new LabeledDouble(label, 85.0);
    testAbove = new LabeledDouble(label, 90.0);
  }

  @Test
  void testHistory()
  {
    this.historyTest = new History(label, filterTest, calculatorTest);
    assertEquals(this.label, this.historyTest.getLabel());

    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> this.historyTest = new History(null, filterTest, calculatorTest));
    assertEquals(exceptionMessage, e.getMessage());

    e = assertThrows(IllegalArgumentException.class,
        () -> this.historyTest = new History("", filterTest, calculatorTest));
    assertEquals(exceptionMessage, e.getMessage());
  }
  
  @Test
  void testAdd() {
    LabeledDouble ld = new LabeledDouble("Hello", 90.0);
    this.historyTest.add(ld);
    assertEquals(ld.getValue(), this.historyTest.filter().get(0).getValue());
  }
  
  @Test
  void testNullAdd() {
    this.historyTest.add(null);
    assertTrue(this.historyTest.filter().isEmpty());
  }
  
  @Test 
  void testFilter() {
    // Test filter all values out
    this.filterTest = new ThresholdFilter(85.0);
    this.historyTest= new History(label, filterTest, calculatorTest);
    this.historyTest.add(testBelow);
    this.historyTest.add(testEqual);
    this.historyTest.add(testAbove);
    assertTrue(this.historyTest.filter().isEmpty());
    
    // Test all values
    this.filterTest = new ThresholdFilter(85.0, -1, 0, 1);
    this.historyTest = new History(label, filterTest, calculatorTest);
    this.historyTest.add(testBelow);
    this.historyTest.add(testEqual);
    this.historyTest.add(testAbove);
    assertTrue(this.historyTest.filter().contains(testEqual));
    assertTrue(this.historyTest.filter().contains(testBelow));
    assertTrue(this.historyTest.filter().contains(testAbove));

    // Test when the filter is null
    this.historyTest = new History(label, null, calculatorTest);
    this.historyTest.add(testBelow);
    this.historyTest.add(testEqual);
    this.historyTest.add(testAbove);
    assertTrue(this.historyTest.filter().contains(testBelow));
    assertTrue(this.historyTest.filter().contains(testEqual));
    assertTrue(this.historyTest.filter().contains(testAbove));
  }
  
  @Test
  void testGetValue() throws SizeException {
      this.filterTest = new ThresholdFilter(2.5, -1, 0, 1);
      this.historyTest = new History(label, filterTest, calculatorTest);

      // Add two LabeledDouble items with the same value
      LabeledDouble value1 = new LabeledDouble(label, 2.5);
      LabeledDouble value2 = new LabeledDouble(label, 2.5);
      
      this.historyTest.add(value1);
      this.historyTest.add(value2);

      LabeledDouble result = this.historyTest.getValue();
      
      assertNotNull(result);
      assertEquals(2.5, result.getValue(), 0.001); // Assert that the result value is 2.5
  }

}
