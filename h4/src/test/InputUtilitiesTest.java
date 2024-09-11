package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import grading.Cohort;
import grading.History;
import grading.InputUtilities;
import math.Filter;
import math.WeightedAverageCalculator;
import math.Calculator;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

public class InputUtilitiesTest
{

  @Test
  public void testReadCohortWithNullReader() throws Exception
  {
    String data1 = "CS149\tA\nCS159\tB+\n";
    String data2 = "CS149\tB\nCS159\tA\n";

    BufferedReader[] readers = {new BufferedReader(new StringReader(data1)), 
        null, // Null reader
        new BufferedReader(new StringReader(data2)) 
    };

    Filter filter = null;
    Calculator calculator = new WeightedAverageCalculator();
    Cohort cohort = InputUtilities.readCohort("Test Cohort", readers, filter, calculator);

    List<History> histories = cohort.getHistories();
    assertEquals(2, histories.size(), "Cohort should contain 2 histories");

    // Verify the label for each history
    History history1 = histories.get(0);
    History history2 = histories.get(1);

    assertEquals("GPA", history1.getLabel());
    assertEquals("GPA", history2.getLabel());
  }
}
