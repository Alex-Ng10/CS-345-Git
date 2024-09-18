package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.CompositeLabeledDouble;
import math.Filter;
import math.Calculator;
import math.LabeledDouble;
import math.LeafLabeledDouble;
import math.ThresholdFilter;
import math.SizeException;
import math.WeightedAverageCalculator;

import java.util.List;

class CompositeLabeledDoubleTest {

    private CompositeLabeledDouble composite;
    private Filter filter;
    private Calculator calculator;

    private LabeledDouble ld1;
    private LabeledDouble ld2;
    private LabeledDouble ld3;

    @BeforeEach
    void setUp() {
        // Initializing test data
        ld1 = new LeafLabeledDouble("Test1", 5.0);
        ld2 = new LeafLabeledDouble("Test2", 10.0);
        ld3 = new LeafLabeledDouble("Test3", 2.5);

        // Initializing a filter and a calculator
        filter = new ThresholdFilter(5.0, 1); // Filter for values greater than 5.0
        calculator = new WeightedAverageCalculator(); // WeightedAverageCalculator without weights

        // Creating the CompositeLabeledDouble instance
        composite = new CompositeLabeledDouble("Composite Test", filter, calculator);
    }

    @Test
    void testFilterWithValidFilter() throws SizeException {
        composite.add(ld1);
        composite.add(ld2);
        composite.add(ld3);

        List<LabeledDouble> filteredData = composite.filter();
        assertEquals(1, filteredData.size(), "Only one value should pass the filter.");
        assertEquals(ld2, filteredData.get(0), "Filtered value should be 10.0.");
    }

    @Test
    void testFilterWithNullFilter() throws SizeException {
        composite = new CompositeLabeledDouble("Test Label", null, calculator); // Null filter
        composite.add(ld1);
        composite.add(ld2);
        composite.add(ld3);

        List<LabeledDouble> unfilteredData = composite.filter();
        assertEquals(3, unfilteredData.size(), "All values should be returned when filter is null.");
    }

    @Test
    void testGetValueWithCalculator() throws SizeException {
        composite.add(ld1);
        composite.add(ld2);
        composite.add(ld3);

        Double calculatedValue = composite.getValue();
        assertNotNull(calculatedValue, "Calculated value should not be null.");
        assertEquals(10.0, calculatedValue, 0.001, "Calculated value should be 10.0.");
    }

    @Test
    void testGetValueWithNullCalculator() throws SizeException {
        composite = new CompositeLabeledDouble("Test Label", filter, null); // Null calculator
        composite.add(ld1);
        composite.add(ld2);

        Double calculatedValue = composite.getValue();
        assertNull(calculatedValue, "Calculated value should be null when calculator is null.");
    }

    @Test
    void testGetValueWithNoData() throws SizeException {
        Double calculatedValue = composite.getValue();
        assertNull(calculatedValue, "Value should be null when no data is added.");
    }
}
