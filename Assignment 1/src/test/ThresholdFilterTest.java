package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import math.LabeledDouble;
import math.SizeException;
import math.ThresholdFilter;

import java.util.ArrayList;
import java.util.List;

public class ThresholdFilterTest {

    private ThresholdFilter filterEqual;
    private ThresholdFilter filterGreaterThan;
    private ThresholdFilter filterLessThan;

    @BeforeEach
    public void setUp() {
        // Initialize ThresholdFilter instances with different sign criteria
        filterEqual = new ThresholdFilter(10.0, 0);
        filterGreaterThan = new ThresholdFilter(10.0, 1);
        filterLessThan = new ThresholdFilter(10.0, -1);
    }

    @Test
    public void testApplyEqualThreshold() throws SizeException {
        List<LabeledDouble> data = new ArrayList<>();
        data.add(new LabeledDouble("Value1", 10.0));
        data.add(new LabeledDouble("Value2", 15.0));
        data.add(new LabeledDouble("Value3", 5.0));

        List<LabeledDouble> result = filterEqual.apply(data);
        
        assertEquals(1, result.size(), "There should be 1 item matching the threshold.");
        assertEquals(10.0, result.get(0).getValue(), "The value should be 10.0");
    }

    @Test
    public void testApplyGreaterThanThreshold() throws SizeException {
        List<LabeledDouble> data = new ArrayList<>();
        data.add(new LabeledDouble("Value1", 10.0));
        data.add(new LabeledDouble("Value2", 15.0));
        data.add(new LabeledDouble("Value3", 5.0));

        List<LabeledDouble> result = filterGreaterThan.apply(data);
        
        assertEquals(1, result.size(), "There should be 1 item greater than the threshold.");
        assertEquals(15.0, result.get(0).getValue(), "The value should be 15.0");
    }

    @Test
    public void testApplyLessThanThreshold() throws SizeException {
        List<LabeledDouble> data = new ArrayList<>();
        data.add(new LabeledDouble("Value1", 10.0));
        data.add(new LabeledDouble("Value2", 15.0));
        data.add(new LabeledDouble("Value3", 5.0));

        List<LabeledDouble> result = filterLessThan.apply(data);
        
        assertEquals(1, result.size(), "There should be 1 item less than the threshold.");
        assertEquals(5.0, result.get(0).getValue(), "The value should be 5.0");
    }

    @Test
    public void testApplyNullData() {
        assertThrows(SizeException.class, () -> {
            filterEqual.apply(null);
        }, "Applying filter with null data should throw SizeException.");
    }

    @Test
    public void testApplyNoSignCriteria() throws SizeException {
        ThresholdFilter filterNoSign = new ThresholdFilter(10.0);

        List<LabeledDouble> data = new ArrayList<>();
        data.add(new LabeledDouble("Value1", 10.0));
        data.add(new LabeledDouble("Value2", 15.0));
        data.add(new LabeledDouble("Value3", 5.0));

        List<LabeledDouble> result = filterNoSign.apply(data);
        
        assertTrue(result.isEmpty(), "When no sign criteria is provided, the result should be empty.");
    }
}
