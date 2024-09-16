package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grading.Cohort;
import grading.History;
import math.LeafLabeledDouble;
import math.SizeException;
import math.WeightedAverageCalculator;

public class CohortTest
{

  private static String student1 = "Student1";
  private static String testcohort = "TestCohort";
  private Cohort cohort;
  private WeightedAverageCalculator calculator;

  @BeforeEach
  public void setUp()
  {
    calculator = new WeightedAverageCalculator();
    cohort = new Cohort(testcohort);
  }

  @Test
  public void testCohortInitialization()
  {
    assertNotNull(cohort);
    assertEquals(testcohort, cohort.getLabel());

    assertTrue(cohort.getHistories().isEmpty(),
        "Histories should be initialized as an empty list.");
  }

  @Test
  public void testAddHistory()
  {
    History history = new History(student1, null, calculator);
    cohort.add(history);

    assertEquals(1, cohort.getHistories().size(), "Histories should contain 1 history.");
    assertEquals(student1, cohort.getHistories().get(0).getLabel());
  }

  @Test
  public void testGetValue() throws SizeException
  {
    History history1 = new History("Student3", null, calculator);
    history1.add(new LeafLabeledDouble("Course1", 3.0));
    cohort.add(history1);

    History history2 = new History("Student4", null, calculator);
    history2.add(new LeafLabeledDouble("Course2", 3.5));
    cohort.add(history2);

    LeafLabeledDouble cohortValue = cohort.getValue();
    assertEquals(3.25, cohortValue.getValue(), 0.01,
        "Cohort GPA should be the average of all histories.");
  }

}
