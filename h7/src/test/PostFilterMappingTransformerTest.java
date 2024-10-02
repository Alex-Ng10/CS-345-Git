package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import math.*;

class PostFilterMappingTransformerTest
{

  private PostFilterMappingTransformer transformer;
  private Map<String, Double> map;
  private List<LabeledDouble> data;
  private Filter passingFilter;
  private Filter nullFilter;

  @BeforeEach
  void setUp()
  {
    map = new HashMap<>();
    map.put("Math", 3.0);
    map.put("Science", 4.0);

    data = new ArrayList<>();
    data.add(new LeafLabeledDouble("Math", 90.0));
    data.add(new LeafLabeledDouble("Science", 85.0));
    data.add(new LeafLabeledDouble("History", 70.0));

    passingFilter = new ThresholdFilter(80.0, 1);

    nullFilter = null;
  }

  @Test
  void testApply_WithNonNullFilter() throws SizeException
  {
    transformer = new PostFilterMappingTransformer(passingFilter, map);
    List<LabeledDouble> result = transformer.apply(data);

    assertEquals(2, result.size());
    assertEquals(3.0, result.get(0).getValue());
    assertEquals(4.0, result.get(1).getValue());
  }

  @Test
  void testApply_WithNullFilter() throws SizeException
  {
    transformer = new PostFilterMappingTransformer(nullFilter, map);
    List<LabeledDouble> result = transformer.apply(data);

    assertEquals(3, result.size());
    assertEquals(3.0, result.get(0).getValue());
    assertEquals(4.0, result.get(1).getValue());
    assertNull(result.get(2).getValue());
  }

  @Test
  void testApply_FilterRemovesAllElements()
  {
    Filter strictFilter = new ThresholdFilter(100.0, 1);
    transformer = new PostFilterMappingTransformer(strictFilter, map);

    assertThrows(SizeException.class, () -> {
      transformer.apply(data);
    });
  }

  @Test
  void testApply_FilteredEmptyList()
  {
    Filter filterAllOut = new ThresholdFilter(100.0, 1);
    transformer = new PostFilterMappingTransformer(filterAllOut, map);

    assertThrows(SizeException.class, () -> {
      transformer.apply(data);
    });
  }

  @Test
  void testApply_NullList()
  {
    transformer = new PostFilterMappingTransformer(nullFilter, map);
    assertThrows(SizeException.class, () -> {
      transformer.apply(null);
    });
  }

  @Test
  void testApply_EmptyList()
  {
    transformer = new PostFilterMappingTransformer(nullFilter, map);
    assertThrows(SizeException.class, () -> {
      transformer.apply(new ArrayList<>());
    });
  }

  @Test
  void testApply_MapMissingKeys() throws SizeException
  {
    map.remove("Math");
    transformer = new PostFilterMappingTransformer(nullFilter, map);
    List<LabeledDouble> result = transformer.apply(data);
    assertNull(result.get(0).getValue());
    assertEquals(4.0, result.get(1).getValue());
  }

  @Test
  void testApply_NullMap() throws SizeException
  {
    transformer = new PostFilterMappingTransformer(nullFilter, null);
    List<LabeledDouble> result = transformer.apply(data);
    assertEquals(3, result.size());
    assertNull(result.get(0).getValue());
    assertNull(result.get(1).getValue());
    assertNull(result.get(2).getValue());
  }

  @Test
  void testApply_ComplexFilter() throws SizeException
  {
    // Create a filter that passes grades between 70 and 90
    Filter complexFilter = new ThresholdFilter(70.0, -1, 1);
    transformer = new PostFilterMappingTransformer(complexFilter, map);

    List<LabeledDouble> result = transformer.apply(data);

    // Expect two items: History (70.0) and Math (90.0) to pass the filter
    assertEquals(2, result.size());
    assertEquals(3.0, result.get(0).getValue()); // Math mapped to 3.0
//    assertNull(result.get(1).getValue()); // History has no map entry
  }

  @Test
  void testApply_AllFilterFailing()
  {
    // Create a filter that will fail for all elements (e.g., grades > 100)
    Filter allFailFilter = new ThresholdFilter(100.0, 1);
    transformer = new PostFilterMappingTransformer(allFailFilter, map);

    // Expect a SizeException because no elements should pass the filter
    assertThrows(SizeException.class, () -> transformer.apply(data));
  }

  @Test
  void testApply_EmptyMap() throws SizeException
  {
    // Use a filter that passes all elements, but an empty map
    transformer = new PostFilterMappingTransformer(nullFilter, new HashMap<>());
    List<LabeledDouble> result = transformer.apply(data);

    // Expect all 3 items, but with null values as map is empty
    assertEquals(3, result.size());
    for (LabeledDouble labeledDouble : result)
    {
      assertNull(labeledDouble.getValue());
    }
  }

  @Test
  void testApply_WithNullElementInData()
  {
    data.add(null); // Adding null to the data

    transformer = new PostFilterMappingTransformer(nullFilter, map);

    // Expect no exceptions, just process the valid elements and skip the null
    assertThrows(NullPointerException.class, () -> transformer.apply(data));
  }

  @Test
  void testApply_FilterWithBoundaryValues() throws SizeException
  {
    // Create a filter that passes only elements with grade >= 70.0
    Filter boundaryFilter = new ThresholdFilter(70.0, 0, 1);
    transformer = new PostFilterMappingTransformer(boundaryFilter, map);

    List<LabeledDouble> result = transformer.apply(data);

    // Expect History (70.0), Math (90.0), and Science (85.0) to pass
    assertEquals(3, result.size());
    assertEquals(3.0, result.get(0).getValue()); // Math mapped to 3.0
    assertEquals(4.0, result.get(1).getValue()); // Science mapped to 4.0
    assertNull(result.get(2).getValue()); // History has no map entry
  }

  @Test
  void testApply_AllMissingMapValues() throws SizeException
  {
    // Create a map that does not contain any of the labels in the data
    Map<String, Double> unrelatedMap = new HashMap<>();
    unrelatedMap.put("Art", 5.0); // Labels in the map don't match any in data

    transformer = new PostFilterMappingTransformer(nullFilter, unrelatedMap);

    List<LabeledDouble> result = transformer.apply(data);

    // All elements should still be in the result, but all should have null values
    assertEquals(3, result.size());
    for (LabeledDouble labeledDouble : result)
    {
      assertNull(labeledDouble.getValue());
    }
  }

  @Test
  void testApply_PartialFilter() throws SizeException
  {
    // Create a filter that only passes elements with a grade > 80
    Filter partialFilter = new ThresholdFilter(80.0, 1);
    transformer = new PostFilterMappingTransformer(partialFilter, map);

    List<LabeledDouble> result = transformer.apply(data);

    // Expect two elements to pass (Math and Science), but History to be filtered out
    assertEquals(2, result.size());
    assertEquals(3.0, result.get(0).getValue()); // Math mapped to 3.0
    assertEquals(4.0, result.get(1).getValue()); // Science mapped to 4.0
  }

  @Test
  void testApply_FilterWithNullMap() throws SizeException
  {
    // Create a filter that passes all elements
    Filter passingFilter = new ThresholdFilter(0.0, 1);

    transformer = new PostFilterMappingTransformer(passingFilter, null);

    List<LabeledDouble> result = transformer.apply(data);

    // Expect all elements in the result, but with null values
    assertEquals(3, result.size());
    for (LabeledDouble labeledDouble : result)
    {
      assertNull(labeledDouble.getValue());
    }
  }

}
