package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grading.Cohort;
import grading.History;
import math.LabeledDouble;
import math.SizeException;
import math.WeightedAverageCalculator;

public class CohortTest {

    private Cohort cohort;
    private WeightedAverageCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new WeightedAverageCalculator();
        cohort = new Cohort("TestCohort");
    }

    @Test
    public void testCohortInitialization() {
        assertNotNull(cohort);
        assertEquals("TestCohort", cohort.getLabel());
        assertTrue(cohort.getHistories().isEmpty(), "Histories should be initialized as an empty list.");
    }

    @Test
    public void testAdd() {
        History history = new History("Student1", null, calculator);
        cohort.add(history);

        assertEquals(1, cohort.getHistories().size());
        assertEquals("Student1", cohort.getHistories().get(0).getLabel());
    }
    
    @Test
    public void testAddNull() {
        cohort.add(null);

        assertTrue(cohort.getHistories().isEmpty(), "Histories should still be empty when adding null.");
    }

    @Test
    public void testGetValue() throws SizeException {
        History history1 = new History("Student3", null, calculator);
        history1.add(new LabeledDouble("Course1", 3.0));
        cohort.add(history1);

        History history2 = new History("Student4", null, calculator);
        history2.add(new LabeledDouble("Course2", 3.5));
        cohort.add(history2);

        LabeledDouble cohortValue = cohort.getValue();
        assertEquals(3.25, cohortValue.getValue(), 0.01, "Cohort GPA should be the average of all histories.");
    }

    @Test
    public void testCohortConstructorWithNullLabel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cohort(null);
        }, "Expected IllegalArgumentException when label is null.");
    }

    @Test
    public void testCohortConstructorWithEmptyLabel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cohort("");
        }, "Expected IllegalArgumentException when label is empty.");
    }
}
