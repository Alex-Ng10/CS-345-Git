package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.LeafLabeledDouble;
import math.SizeException;
import math.ThresholdFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the ThresholdFilter class.
 * 
 * This test class verifies the behavior of the ThresholdFilter class, ensuring that it correctly
 * filters lists of LabeledDouble objects based on different threshold criteria.
 * 
 * @author Alex Nguyen
 */
public class ThresholdFilterTest
{

  private static String Value1 = "Value1";
  private static String Value2 = "Value2";
  private static String Value3 = "Value3";

  private ThresholdFilter filterEqual;
  private ThresholdFilter filterGreaterThan;
  private ThresholdFilter filterLessThan;

  /**
   * Initializes the ThresholdFilter instances before each test.
   * 
   * Creates filters with different comparison criteria (equal, greater than, less than).
   */
  @BeforeEach
  void setUp()
  {
    // Initialize ThresholdFilter instances with different sign criteria
    filterEqual = new ThresholdFilter(10.0, 0);
    filterGreaterThan = new ThresholdFilter(10.0, 1);
    filterLessThan = new ThresholdFilter(10.0, -1);
  }

  /**
   * Tests the apply method with a filter that matches values equal to the threshold.
   * 
   * Ensures that only the LabeledDouble with a value of 10.0 is included in the result.
   */
  @Test
  void testApplyEqualThreshold()
  {
    List<LeafLabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(Value1, 10.0));
    data.add(new LeafLabeledDouble(Value2, 15.0));
    data.add(new LeafLabeledDouble(Value3, 5.0));

    List<LeafLabeledDouble> result = filterEqual.apply(data);

    assertEquals(1, result.size(), "There should be 1 item matching the threshold.");
    assertEquals(10.0, result.get(0).getValue(), "The value should be 10.0");
  }

  /**
   * Tests the apply method with a filter that matches values greater than the threshold.
   * 
   * Ensures that only the LabeledDouble with a value greater than 10.0 is included in the result.
   */
  @Test
  void testApplyGreaterThanThreshold()
  {
    List<LeafLabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(Value1, 10.0));
    data.add(new LeafLabeledDouble(Value2, 15.0));
    data.add(new LeafLabeledDouble(Value3, 5.0));

    List<LeafLabeledDouble> result = filterGreaterThan.apply(data);

    assertEquals(1, result.size(), "There should be 1 item greater than the threshold.");
    assertEquals(15.0, result.get(0).getValue(), "The value should be 15.0");
  }

  /**
   * Tests the apply method with a filter that matches values less than the threshold.
   * 
   * Ensures that only the LabeledDouble with a value less than 10.0 is included in the result.
   */
  @Test
  void testApplyLessThanThreshold()
  {
    List<LeafLabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(Value1, 10.0));
    data.add(new LeafLabeledDouble(Value2, 15.0));
    data.add(new LeafLabeledDouble(Value3, 5.0));

    List<LeafLabeledDouble> result = filterLessThan.apply(data);

    assertEquals(1, result.size(), "There should be 1 item less than the threshold.");
    assertEquals(5.0, result.get(0).getValue(), "The value should be 5.0");
  }

  /**
   * Tests the apply method with null data.
   * 
   * Ensures that applying the filter with null data throws a SizeException.
   */
  @Test
  void testApplyNullData()
  {
    assertThrows(SizeException.class, () -> {
      filterEqual.apply(null);
    }, "Applying filter with null data should throw SizeException.");
  }

  @Test
  void testApplyNullSign() throws SizeException
  {
    // Set up a filter with a null sign array
    ThresholdFilter filterWithNullSign = new ThresholdFilter(10.0, null);

    // Set up test data
    List<LeafLabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(Value1, 10.0));
    data.add(new LeafLabeledDouble(Value2, 15.0));

    // Apply the filter and verify the result is an empty list
    List<LeafLabeledDouble> result = filterWithNullSign.apply(data);
    assertTrue(result.isEmpty(), "When sign array is null , result should be an empty list.");
  }

  /**
   * Tests the apply method with no sign criteria provided.
   * 
   * Ensures that when no sign criteria is given, the result is an empty list.
   */
  @Test
  void testApplyNoSignCriteria()
  {
    ThresholdFilter filterNoSign = new ThresholdFilter(10.0);

    List<LeafLabeledDouble> data = new ArrayList<>();
    data.add(new LeafLabeledDouble(Value1, 10.0));
    data.add(new LeafLabeledDouble(Value2, 15.0));
    data.add(new LeafLabeledDouble(Value3, 5.0));

    List<LeafLabeledDouble> result = filterNoSign.apply(data);

    assertTrue(result.isEmpty(), "When no sign criteria is provided, the result should be empty.");
  }

}
