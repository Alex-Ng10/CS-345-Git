package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import math.LabeledDouble;

public class LabeledDoubleTest
{

  @Test
  public void testConstructorWithLabel() {
    LabeledDouble ld = new LabeledDouble("TestLabel");
    assertEquals("TestLabel", ld.getLabel());
    assertEquals(0.0, ld.getValue());
  }

  @Test
  public void testConstructorWithLabelAndDoubleValue() {
    LabeledDouble ld = new LabeledDouble("TestLabel", 5.5);
    assertEquals("TestLabel", ld.getLabel());
    assertEquals(5.5, ld.getValue());
  }

  @Test
  public void testConstructorWithLabelAndNullDoubleValue() {
    LabeledDouble ld = new LabeledDouble("TestLabel", (Double) null);
    assertEquals("TestLabel", ld.getLabel());
    assertNull(ld.getValue());
  }

  @Test
  public void testConstructorWithNullLabel() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new LabeledDouble(null);
    });
    assertEquals("Label cannot be null or empty", exception.getMessage());
  }

  @Test
  public void testConstructorWithEmptyLabel() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new LabeledDouble("");
    });
    assertEquals("Label cannot be null or empty", exception.getMessage());
  }

  @Test
  public void testCompareToBothValuesNull() {
    LabeledDouble ld1 = new LabeledDouble("Label1", null);
    LabeledDouble ld2 = new LabeledDouble("Label2", null);
    assertEquals(0, ld1.compareTo(ld2));
  }

  @Test
  public void testCompareToThisValueNull() {
    LabeledDouble ld1 = new LabeledDouble("Label1", null);
    LabeledDouble ld2 = new LabeledDouble("Label2", 5.0);
    assertEquals(-1, ld1.compareTo(ld2));
  }

  @Test
  public void testCompareToOtherValueNull() {
    LabeledDouble ld1 = new LabeledDouble("Label1", 5.0);
    LabeledDouble ld2 = new LabeledDouble("Label2", null);
    assertEquals(1, ld1.compareTo(ld2));
  }

  @Test
  public void testCompareToValidValues() {
    LabeledDouble ld1 = new LabeledDouble("Label1", 5.0);
    LabeledDouble ld2 = new LabeledDouble("Label2", 10.0);
    assertTrue(ld1.compareTo(ld2) < 0);
    assertTrue(ld2.compareTo(ld1) > 0);
  }

  @Test
  public void testLabeledDoubleStringdoubleValue() {
    boolean flag = false;
    boolean flagNull = false;
    boolean flagEmpty = false;
    try
    {
      new LabeledDouble("A", 4.0);
      new LabeledDouble("A-", 3.7);
      new LabeledDouble("B+", 3.3);
      new LabeledDouble("B", 3.0);
      new LabeledDouble("B-", 2.7);
      new LabeledDouble("C+", 2.3);
      new LabeledDouble("C", 2.0);
      new LabeledDouble("C-", 1.7);
      new LabeledDouble("D+", 1.3);
      new LabeledDouble("D", 1.0);
      new LabeledDouble("D-", 0.7);
      new LabeledDouble("F", 0.0);
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

  @Test
  public void testLabeledDoubleDoubleValue() {
    boolean flag = false;
    boolean flagNull = false;
    boolean flagEmpty = false;
    try
    {
      new LabeledDouble("A", Double.valueOf(4.0));
      new LabeledDouble("A-", Double.valueOf(3.7));
      new LabeledDouble("B+", Double.valueOf(3.3));
      new LabeledDouble("B", Double.valueOf(3.0));
      new LabeledDouble("B-", Double.valueOf(2.7));
      new LabeledDouble("C+", Double.valueOf(2.3));
      new LabeledDouble("C", Double.valueOf(2.0));
      new LabeledDouble("C-", Double.valueOf(1.7));
      new LabeledDouble("D+", Double.valueOf(1.3));
      new LabeledDouble("D", Double.valueOf(1.0));
      new LabeledDouble("D-", Double.valueOf(0.7));
      new LabeledDouble("F", Double.valueOf(0.0));
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
  
  @Test
  public void testToStringNonVerboseValuePresent() {
    LabeledDouble ld = new LabeledDouble("TestLabel", 4.0);
    assertEquals("4.000000", ld.toString());
  }

  @Test
  public void testToStringNonVerboseValueNull() {
    LabeledDouble ld = new LabeledDouble("TestLabel", (Double) null);
    assertEquals("N/A", ld.toString());
  }

  @Test
  public void testToStringValuePresent() {
    LabeledDouble ld = new LabeledDouble("TestLabel", 4.0);
    assertEquals("TestLabel: 4.000000", ld.toString(true));
  }

  @Test
  public void testToStringValueNull() {
    LabeledDouble ld = new LabeledDouble("TestLabel", (Double) null);
    assertEquals("TestLabel: N/A", ld.toString(true));
  }
}
