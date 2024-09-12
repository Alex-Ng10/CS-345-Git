package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grading.Cohort;
import grading.InputUtilities;
import math.ThresholdFilter;
import math.WeightedAverageCalculator;

class InputUtilitiesTest
{
  BufferedReader[] cohort;
  BufferedReader bart;
  BufferedReader empty;
  BufferedReader marge;
  BufferedReader maggie;
  ThresholdFilter filter;
  WeightedAverageCalculator calc;

  @Test
  void testDefaultConstructor()
  {
    new InputUtilities();
  }

  @BeforeEach
  void test() throws IOException
  {
    bart = new BufferedReader(new FileReader("bart.trn"));
    empty = new BufferedReader(new FileReader("empty.trn"));
    marge = new BufferedReader(new FileReader("marge.trn"));
    maggie = new BufferedReader(new FileReader("maggie.trn"));
    cohort = new BufferedReader[4];
    cohort[0] = bart;
    cohort[1] = empty;
    cohort[2] = marge;
    cohort[3] = maggie;
    filter = new ThresholdFilter(4, -1, 0, 1);
    calc = new WeightedAverageCalculator();
  }

  @Test
  void testReadGradeHistory() throws IOException
  {
    assertNotNull(InputUtilities.readGradeHistory("GPA", bart, filter, calc));
  }

  @Test
  void testReadCohort() throws IOException
  {
    assertNotNull(InputUtilities.readCohort("Label1", cohort, filter, calc));
  }
  
  @Test
  void testReadCohort_null() throws IOException
  {
    BufferedReader[] nullCohort = { null, null, null };  // All null readers
    Cohort cohortResult = InputUtilities.readCohort("Label2", nullCohort, filter, calc);
    assertNotNull(cohortResult, "Cohort should not be null even when all readers are null.");
    assertTrue(cohortResult.getHistories().isEmpty(), "Cohort should contain no histories when all readers are null.");
  }
}
