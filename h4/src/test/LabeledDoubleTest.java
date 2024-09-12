package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import math.LabeledDouble;

/**
 * Test class for the LabeledDouble class.
 * 
 * This class tests the constructors, comparison logic, and string representation methods of the
 * LabeledDouble class.
 * 
 * @author Alex Nguyen
 */
public class LabeledDoubleTest
{

  private static String labelNA = "TestLabel: N/A";
  private static String TestLabel = "TestLabel";
  private static String Label1 = "Label1";
  private static String Label2 = "Label2";
  private static String A = "A";
  private static String AMINUS = "A-";
  private static String BPLUS = "B+";
  private static String B = "B";
  private static String BMINUS = "B-";
  private static String CPLUS = "C+";
  private static String C = "C";
  private static String CMINUS = "C-";
  private static String DPLUS = "D+";
  private static String D = "D";
  private static String DMINUS = "D-";
  private static String F = "F";
  private static String NA = "N/A";

  /**
   * Tests the constructor with only a label.
   * 
   * Ensures that the label is correctly assigned and the default value is set to 0.0.
   */
  @Test
  public void testConstructorWithLabel()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel);
    assertEquals(TestLabel, ld.getLabel());
    assertEquals(0.0, ld.getValue());
  }

  /**
   * Tests the constructor with a label and a double value.
   * 
   * Ensures that both the label and value are correctly assigned.
   */
  @Test
  public void testConstructorWithLabelAndDoubleValue()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, 5.5);
    assertEquals(TestLabel, ld.getLabel());
    assertEquals(5.5, ld.getValue());
  }

  /**
   * Tests the constructor with a label and a null double value.
   * 
   * Ensures that the label is correctly assigned and the value is set to null.
   */
  @Test
  public void testConstructorWithLabelAndNullDoubleValue()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, (Double) null);
    assertEquals(TestLabel, ld.getLabel());
    assertNull(ld.getValue());
  }

  /**
   * Tests the constructor with a null label.
   * 
   * Ensures that an IllegalArgumentException is thrown.
   */
  @Test
  public void testConstructorWithNullLabel()
  {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> 
    {
      new LabeledDouble(null);
    });
    assertEquals(null, exception.getMessage());
  }

  /**
   * Tests the constructor with an empty label.
   * 
   * Ensures that an IllegalArgumentException is thrown.
   */
  @Test
  public void testConstructorWithEmptyLabel()
  {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> 
    {
      new LabeledDouble("");
    });
    assertEquals(null, exception.getMessage());
  }

  /**
   * Tests the compareTo method when both LabeledDouble values are null.
   * 
   * Ensures that the comparison returns 0, indicating equality.
   */
  @Test
  public void testCompareToBothValuesNull()
  {
    LabeledDouble ld1 = new LabeledDouble(Label1, null);
    LabeledDouble ld2 = new LabeledDouble(Label2, null);
    assertEquals(0, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method when the current object's value is null.
   * 
   * Ensures that the comparison returns -1, indicating that the current object is less than the
   * other.
   */
  @Test
  public void testCompareToCurrentValueNull()
  {
    LabeledDouble ld1 = new LabeledDouble(Label1, null);
    LabeledDouble ld2 = new LabeledDouble(Label2, 5.0);
    assertEquals(-1, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method when the current object's value is null.
   * 
   * Ensures that the comparison returns -1, indicating that the current object is less than the
   * other.
   */
  @Test
  public void testCompareToThisValueNull()
  {
    LabeledDouble ld1 = new LabeledDouble(Label1, null);
    LabeledDouble ld2 = new LabeledDouble(Label2, 5.0);
    assertEquals(-1, ld1.compareTo(ld2));

  }

  /**
   * Tests the compareTo method when the other object's value is null.
   * 
   * Ensures that the comparison returns 1, indicating that the current object is greater than the
   * other.
   */
  @Test
  public void testCompareToOtherValueNull()
  {
    LabeledDouble ld1 = new LabeledDouble(Label1, 5.0);
    LabeledDouble ld2 = new LabeledDouble(Label2, null);
    assertEquals(1, ld1.compareTo(ld2));
  }

  /**
   * Tests the compareTo method with valid non-null values.
   * 
   * Ensures that the comparison correctly identifies greater, lesser, and equal values.
   */
  @Test
  public void testCompareToValidValues()
  {
    LabeledDouble ld1 = new LabeledDouble(Label1, 5.0);
    LabeledDouble ld2 = new LabeledDouble(Label2, 10.0);
    assertTrue(ld1.compareTo(ld2) < 0);
    assertTrue(ld2.compareTo(ld1) > 0);
  }

  /**
   * Tests the LabeledDouble constructor with a string label and a double value.
   * 
   * This test ensures that valid labels and values are accepted, and that null and empty labels are
   * correctly rejected.
   */
  @Test
  public void testLabeledDoubleStringdoubleValue()
  {
    boolean flag = false;
    boolean flagNull = false;
    boolean flagEmpty = false;
    try
    {
      new LabeledDouble(A, 4.0);
      new LabeledDouble(AMINUS, 3.7);
      new LabeledDouble(BPLUS, 3.3);
      new LabeledDouble(B, 3.0);
      new LabeledDouble(BMINUS, 2.7);
      new LabeledDouble(CPLUS, 2.3);
      new LabeledDouble(C, 2.0);
      new LabeledDouble(CMINUS, 1.7);
      new LabeledDouble(DPLUS, 1.3);
      new LabeledDouble(D, 1.0);
      new LabeledDouble(DMINUS, 0.7);
      new LabeledDouble(F, 0.0);
    }
    catch (IllegalArgumentException e)
    {
      flag = true;
    }
    assertFalse(flag);

    try
    {
      new LabeledDouble(null, 0.0);
    }
    catch (IllegalArgumentException e)
    {
      flagNull = true;
    }
    assertTrue(flagNull);

    try
    {
      new LabeledDouble("", 0.0);
    }
    catch (IllegalArgumentException e)
    {
      flagEmpty = true;
    }
    assertTrue(flagEmpty);
  }

  /**
   * Tests the LabeledDouble constructor with a string label and a Double object.
   * 
   * This test ensures that valid labels and Double objects are accepted, and that null and empty
   * labels are correctly rejected.
   */
  @Test
  public void testLabeledDoubleDoubleValue()
  {
    boolean flag = false;
    boolean flagNull = false;
    boolean flagEmpty = false;
    try
    {
      new LabeledDouble(A, Double.valueOf(4.0));
      new LabeledDouble(AMINUS, Double.valueOf(3.7));
      new LabeledDouble(BPLUS, Double.valueOf(3.3));
      new LabeledDouble(B, Double.valueOf(3.0));
      new LabeledDouble(BMINUS, Double.valueOf(2.7));
      new LabeledDouble(CPLUS, Double.valueOf(2.3));
      new LabeledDouble(C, Double.valueOf(2.0));
      new LabeledDouble(CMINUS, Double.valueOf(1.7));
      new LabeledDouble(DPLUS, Double.valueOf(1.3));
      new LabeledDouble(D, Double.valueOf(1.0));
      new LabeledDouble(DMINUS, Double.valueOf(0.7));
      new LabeledDouble(F, Double.valueOf(0.0));
    }
    catch (IllegalArgumentException e)
    {
      flag = true;
    }
    assertFalse(flag);

    try
    {
      new LabeledDouble(A, Double.valueOf(4.0));
      new LabeledDouble(AMINUS, Double.valueOf(3.7));
      new LabeledDouble(BPLUS, Double.valueOf(3.3));
      new LabeledDouble(B, Double.valueOf(3.0));
      new LabeledDouble(BMINUS, Double.valueOf(2.7));
      new LabeledDouble(CPLUS, Double.valueOf(2.3));
      new LabeledDouble(C, Double.valueOf(2.0));
      new LabeledDouble(CMINUS, Double.valueOf(1.7));
      new LabeledDouble(DPLUS, Double.valueOf(1.3));
      new LabeledDouble(D, Double.valueOf(1.0));
      new LabeledDouble(DMINUS, Double.valueOf(0.7));
      new LabeledDouble(F, Double.valueOf(0.0));
    }
    catch (IllegalArgumentException e)
    {
      flag = true;
    }
    assertFalse(flag);

    try
    {
      new LabeledDouble(null, Double.valueOf(0.0));
    }
    catch (IllegalArgumentException e)
    {
      flagNull = true;
    }
    assertTrue(flagNull);

    try
    {
      new LabeledDouble("", Double.valueOf(0.0));
    }
    catch (IllegalArgumentException e)
    {
      flagEmpty = true;
    }
    assertTrue(flagEmpty);
  }

  /**
   * Tests the toString method in non-verbose mode with a non-null value.
   * 
   * Ensures that the string representation is correct for the non-verbose mode.
   */
  @Test
  public void testToStringNonVerboseValuePresent()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, 5.0);
    assertEquals("5.000000", ld.toString());
  }

  /**
   * Tests the toString method in non-verbose mode with a null value.
   * 
   * Ensures that the string representation is "N/A" for null values in non-verbose mode.
   */
  @Test
  public void testToStringNonVerboseValueNull()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, null);
    assertEquals(NA, ld.toString());

    LabeledDouble ld2 = new LabeledDouble(TestLabel, Double.NaN);
    assertEquals(NA, ld2.toString());
  }

  /**
   * Tests the toString method (verbose) when the value is present. Ensures that the correct label
   * and value are returned as a string.
   */
  @Test
  public void testToStringVerboseValuePresent()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, 5.0);
    assertEquals("TestLabel: 5.000000", ld.toString(true));
  }

  /**
   * Tests the toString method (verbose) when the value is null. Ensures that the correct label and
   * "N/A" are returned as a string.
   */
  @Test
  public void testToStringVerboseValueNull()
  {
    LabeledDouble ld = new LabeledDouble(TestLabel, null);
    assertEquals(labelNA, ld.toString(true));

    LabeledDouble ld2 = new LabeledDouble(TestLabel, Double.NaN);
    assertEquals(labelNA, ld2.toString(true));
  }
}
