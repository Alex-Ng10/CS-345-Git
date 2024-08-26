package test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.LabeledDouble;
import math.SizeException;

class WeightedTotalCalculator {
  
  private WeightedTotalCalculator calculator;
  private List<LabeledDouble> data;
  private Map<String, Double> weights;

  @BeforeEach
  public void setUp() {
      weights = new HashMap<>();
      weights.put("A", 2.0);
      weights.put("B", 1.5);
      weights.put("C", 1.0);

      data = new ArrayList<>();
      data.add(new LabeledDouble("A", 4.0));
      data.add(new LabeledDouble("B", 3.0));
      data.add(new LabeledDouble("C", 2.0));
  }
  @Test
  public void testCalculateWithValidDataAndWeights() throws SizeException {
      calculator = new WeightedTotalCalculator(weights);
      LabeledDouble result = calculator.calculate("Total", data);
      assertEquals(15.5, result.getValue(), 0.01); 
      assertEquals("Total", result.getLabel());
  }

  @Test
  public void testCalculateWithValidDataAndNoWeights() throws SizeException {
      calculator = new WeightedTotalCalculator();
      LabeledDouble result = calculator.calculate("Total", data);
      assertEquals(9.0, result.getValue(), 0.01); 
      assertEquals("Total", result.getLabel());
  }

  @Test
  public void testCalculateWithNullDataThrowsException() {
      calculator = new WeightedTotalCalculator(weights);
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          calculator.calculate("Total", null);
      });
      assertEquals("Result label can't be null", exception.getMessage());
  }

  @Test
  public void testCalculateWithEmptyDataReturnsZeroTotal() throws SizeException {
      calculator = new WeightedTotalCalculator(weights);
      List<LabeledDouble> emptyData = new ArrayList<>();
      LabeledDouble result = calculator.calculate("Total", emptyData);
      assertEquals(0.0, result.getValue(), 0.01); 
      assertEquals("Total", result.getLabel());
  }

}
