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

}
