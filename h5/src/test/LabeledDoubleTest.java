package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import math.LeafLabeledDouble;

/**
 * Test class for the LeafLabeledDouble class.
 * 
 * This class tests the constructors, comparison logic, and string representation methods of the
 * LeafLabeledDouble class.
 * 
 * @author Alex Nguyen
 */
public class LabeledDoubleTest {

  private static final String TestLabel = "TestLabel";
  private static final String Label1 = "Label1";
  private static final String Label2 = "Label2";
  private static final String labelNA = "TestLabel: N/A";
  private static final String NA = "N/A";

  /**
   * Tests the constructor with only a label.
   * 
   * Ensures that the label is correctly assigned and the default value is set to 0.0.
   */
  @Test
  public void testConstructorWithLabel() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel);
    assertEquals(TestLabel, ld.getLabel());
    assertEquals(0.0, ld.getValue());
  }

  /**
   * Tests the constructor with a label and a double value.
   * 
   * Ensures that both the label and value are correctly assigned.
   */
  @Test
  public void testConstructorWithLabelAndDoubleValue() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, 5.5);
    assertEquals(TestLabel, ld.getLabel());
    assertEquals(5.5, ld.getValue());
  }

  /**
   * Tests the constructor with a label and a null double value.
   * 
   * Ensures that the label is correctly assigned and the value is set to null.
   */
  @Test
  public void testConstructorWithLabelAndNullDoubleValue() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, (Double) null);
    assertEquals(TestLabel, ld.getLabel());
    assertNull(ld.getValue());
  }

  /**
   * Tests the constructor with a null label.
   * 
   * Ensures that an IllegalArgumentException is thrown.
   */
  @Test
  public void testConstructorWithNullLabel() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new LeafLabeledDouble(null);
    });
    assertEquals("Label must not be null or empty", exception.getMessage());
  }

  /**
   * Tests the constructor with an empty label.
   * 
   * Ensures that an IllegalArgumentException is thrown.
   */
  @Test
  public void testConstructorWithEmptyLabel() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new LeafLabeledDouble("");
    });
    assertEquals("Label must not be null or empty", exception.getMessage());
  }

  /**
   * Tests the getValue method for non-null values.
   * 
   * Ensures that the getValue method returns the correct value.
   */
  @Test
  public void testGetValueWithNonNull() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, 5.0);
    assertEquals(5.0, ld.getValue());
  }

  /**
   * Tests the getValue method for null values.
   * 
   * Ensures that the getValue method returns null when the value is null.
   */
  @Test
  public void testGetValueWithNull() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, (Double) null);
    assertNull(ld.getValue());
  }

  /**
   * Tests the toString method in non-verbose mode with a non-null value.
   * 
   * Ensures that the string representation is correct for the non-verbose mode.
   */
  @Test
  public void testToStringNonVerboseValuePresent() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, 5.0);
    assertEquals("5.000000", ld.toString());
  }

  /**
   * Tests the toString method in non-verbose mode with a null value.
   * 
   * Ensures that the string representation is "N/A" for null values in non-verbose mode.
   */
  @Test
  public void testToStringNonVerboseValueNull() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, null);
    assertEquals(NA, ld.toString());

    LeafLabeledDouble ld2 = new LeafLabeledDouble(TestLabel, Double.NaN);
    assertEquals(NA, ld2.toString());
  }

  /**
   * Tests the toString method (verbose) when the value is present. Ensures that the correct label
   * and value are returned as a string.
   */
  @Test
  public void testToStringVerboseValuePresent() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, 5.0);
    assertEquals("TestLabel: 5.000000", ld.toString(true));
  }

  /**
   * Tests the toString method (verbose) when the value is null. Ensures that the correct label and
   * "N/A" are returned as a string.
   */
  @Test
  public void testToStringVerboseValueNull() {
    LeafLabeledDouble ld = new LeafLabeledDouble(TestLabel, null);
    assertEquals(labelNA, ld.toString(true));

    LeafLabeledDouble ld2 = new LeafLabeledDouble(TestLabel, Double.NaN);
    assertEquals(labelNA, ld2.toString(true));
  }

  /**
   * Tests the compareTo method when both LabeledDouble values are null.
   * 
   * Ensures that the comparison returns 0, indicating equality.
   */
  @Test
  public void testCompareToBothValuesNull() {
    LeafLabeledDouble ld1 = new LeafLabeledDouble(Label1, null);
    LeafLabeledDouble ld2 = new LeafLabeledDouble(Label2, null);
    assertEquals(0, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method when the current object's value is null.
   * 
   * Ensures that the comparison returns -1, indicating that the current object is less than the
   * other.
   */
  @Test
  public void testCompareToCurrentValueNull() {
    LeafLabeledDouble ld1 = new LeafLabeledDouble(Label1, null);
    LeafLabeledDouble ld2 = new LeafLabeledDouble(Label2, 5.0);
    assertEquals(-1, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method when the other object's value is null.
   * 
   * Ensures that the comparison returns 1, indicating that the current object is greater than the
   * other.
   */
  @Test
  public void testCompareToOtherValueNull() {
    LeafLabeledDouble ld1 = new LeafLabeledDouble(Label1, 5.0);
    LeafLabeledDouble ld2 = new LeafLabeledDouble(Label2, null);
    assertEquals(1, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method with valid non-null values.
   * 
   * Ensures that the comparison correctly identifies greater, lesser, and equal values.
   */
  @Test
  public void testCompareToValidValues() {
    LeafLabeledDouble ld1 = new LeafLabeledDouble(Label1, 5.0);
    LeafLabeledDouble ld2 = new LeafLabeledDouble(Label2, 10.0);
    assertTrue(ld1.compareTo(ld2) < 0);
    assertTrue(ld2.compareTo(ld1) > 0);
  }
}
