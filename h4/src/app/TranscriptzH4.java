package app;

import java.io.*;
import java.util.List;

import grading.*;
import math.*;

/**
 * The main class for the Transcriptz application.
 * 
 * This version calculates the quality points, attempted hours,
 * earned hours, and grade point average for the information in one or more .trn files.
 * It also calculate the grade point average for the "cohort" (i.e., all of the individuals).
 * 
 * @author Ann E. Koder, SagaciousMedia
 * @version H4
 */
public class TranscriptzH4
{
  /**
   * The entry point of the application.
   * 
   * @param args The command line arguments (i.e., the names of the .trn files)
   */
  public static void main(final String[] args)
  {
    WeightedTotalCalculator tc = new WeightedTotalCalculator();
    WeightedAverageCalculator wac = new WeightedAverageCalculator(new JMUCourseTable());
    WeightedTotalCalculator wtc = new WeightedTotalCalculator(new JMUCourseTable());
     
    Cohort cohort = new Cohort("Cohort's GPA");
    
    for (int i=0; i<args.length; i++)
    {
      System.out.println(args[i]);
      
      // Calculate the GPA
      try (BufferedReader in = new BufferedReader(new FileReader(args[i]))) // try with resources
      {
        History all = InputUtilities.readGradeHistory("GPA", in, null, wac);
        cohort.add(all);
        try
        {
          LabeledDouble gpa = all.getValue();
          System.out.println(gpa.toString(true));
        }
        catch (SizeException se)
        {
          System.out.println("No data for GPA.");
        }
        in.close();
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for GPA.");
      }

      // Calculate the quality points and attempted hours
      try (BufferedReader in = new BufferedReader(new FileReader(args[i]))) // try with resources
      {
        History all = InputUtilities.readGradeHistory("Quality Points", in, null, wtc);
        try
        {
          LabeledDouble qp = all.getValue();
          System.out.println(qp.toString(true));

          History attempted = createHoursHistory("Attempted Hours", null, tc, all);
          //CreditHistory attempted = new CreditHistory("Attempted Hours", null, tc, all);
          System.out.println(attempted.getValue().toString(true));
        }
        catch (SizeException se)
        {
          System.out.println("No data for quality points and attempted hours.");
        }
        in.close();
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for quality points/attempted hours.");
      }

      // Calculate the earned hours
      try (BufferedReader in = new BufferedReader(new FileReader(args[i]))) // try with resources
      {
        Filter passingFilter = new ThresholdFilter(LetterGrade.F.getValue(), 1);
        History passing = InputUtilities.readGradeHistory("Passed Courses", in, passingFilter, wac);
        try
        {
          History earned = createHoursHistory("Earned Hours", null, tc, passing);
          //CreditHistory earned = new CreditHistory("Earned Hours", null, tc, passing);
          System.out.println(earned.getValue().toString(true));
        }
        catch (SizeException se)
        {
          System.out.println("No data for passed courses and earned hours.");
        }
        in.close();
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for passed courses/earned hours.");
      }
      System.out.println();
    }
    
    // Calculate the GPA for the Cohort
    try
    {
      LabeledDouble gpa = cohort.getValue();
      System.out.println(gpa.toString(true));
    }
    catch (SizeException se)
    {
      // Shouldn't get here
      System.out.println("The cohort has no elements.");
    }
  }

  /**
   * Create an hours History from a grades History.
   * 
   * @param label The label for the result
   * @param filter The Filter for the result
   * @param calculator The Calculator for the result
   * @param gradeHistory The grade History to use to construct the credit History
   * @return A History containing hours information
   * @throws SizeException if data in the grade History is null
   */
  private static History createHoursHistory(final String label, 
      final Filter filter, final Calculator calculator, final History gradeHistory) 
          throws SizeException
  {
    History result = new History(label, filter, calculator);
    JMUCourseTable courseTable = new JMUCourseTable();
    List<LabeledDouble> temp = gradeHistory.filter();
    for (LabeledDouble d: temp)
    {
      LabeledDouble element = new LabeledDouble(d.getLabel(), courseTable.get(d.getLabel()));
      result.add(element);
    }
    return result;
  }
}