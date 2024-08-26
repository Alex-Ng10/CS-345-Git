package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.Calculator;
import math.LabeledDouble;
import math.SizeException;
import math.WeightedAverageCalculator;

import java.util.*;

public class WeightedAverageCalculatorTest implements Calculator  {

    private WeightedAverageCalculator calculator;
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
    public void testCalculateWithWeights() throws SizeException {
        calculator = new WeightedAverageCalculator(weights);
        LabeledDouble result = calculator.calculate("Average", data);
        assertEquals(3.1, result.getValue(), 0.01);
        assertEquals("Average", result.getLabel());
    }

    @Test
    public void testCalculateWithValidDataAndNoWeights() throws SizeException {
        calculator = new WeightedAverageCalculator();
        LabeledDouble result = calculator.calculate("Average", data);
        assertEquals(3.0, result.getValue(), 0.01);
        assertEquals("Average", result.getLabel());
    }

    @Test
    public void testCalculateWithNullDataThrowsException() {
        calculator = new WeightedAverageCalculator(weights);
        Exception exception = assertThrows(SizeException.class, () -> {
            calculator.calculate("Average", null);
        });
        assertEquals("Data can't be null", exception.getMessage());
    }

    @Test
    public void testCalculateWithZeroDenominatorThrowsException() {
        calculator = new WeightedAverageCalculator();
        List<LabeledDouble> emptyData = new ArrayList<>();
        emptyData.add(new LabeledDouble("D", null)); 
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate("Average", emptyData);
        });
        assertEquals("Denominator can't be 0", exception.getMessage());
    }

    @Test
    public void testPerformIntermediateCalculationsWithValidData() throws SizeException {
        calculator = new WeightedAverageCalculator(weights);
        double result = calculator.performIntermediateCalculations(data);
        assertEquals(15.5, result, 0.01); 
        assertEquals(4.5, calculator.denominator, 0.01);
    }
}
