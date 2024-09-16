package app;

import java.io.*;
import java.util.*;

import grading.*;
import math.*;

/**
 * The main class for the Transcriptz application.
 * 
 * This version calculates the quality points, attempted hours,
 * earned hours, and grade point average for the information in one or more .trn files.
 * It also calculate the grade point average for the "cohort" (i.e., all of the individuals).
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H5
 */
public class TranscriptzH5
{
  /**
   * The entry point of the application.
   * 
   * @param args The command line arguments (i.e., the names of the .trn files)
   */
  public static void main(final String[] args)
  {
    JMUCourseTable courseTable = new JMUCourseTable();
    WeightedTotalCalculator tc = new WeightedTotalCalculator();
    WeightedAverageCalculator wac = new WeightedAverageCalculator(courseTable);
    WeightedTotalCalculator wtc = new WeightedTotalCalculator(courseTable);
    
    WeightedAverageCalculator ac = new WeightedAverageCalculator(null); // Unweighted
    CompositeLabeledDouble cohort = new CompositeLabeledDouble("Cohort's GPA", null, ac);

    
    for (int i=0; i<args.length; i++)
    {
      System.out.println(args[i]);
      
      try (BufferedReader in = new BufferedReader(new FileReader(args[i])))
      {
        CompositeLabeledDouble all = InputUtilities.readGradeHistory("GPA", in, null, wac);
        in.close();
        System.out.println(all.toString(true));

        cohort.add(all);
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for GPA.");
      }

      try (BufferedReader in = new BufferedReader(new FileReader(args[i])))
      {
        CompositeLabeledDouble all = InputUtilities.readGradeHistory("Quality Points", 
            in, null, wtc);
        in.close();
        System.out.println(all.toString(true));
        
        try
        {
          CompositeLabeledDouble attempted = createHoursHistory("Attempted Hours", null, tc, all);
          System.out.println(attempted.toString(true));
        }
        catch (SizeException se)
        {
          System.out.println("No data for quality points and attempted hours.");
        }
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for quality points/attempted hours.");
      }

      try (BufferedReader in = new BufferedReader(new FileReader(args[i])))
      {
        Filter passingFilter = new ThresholdFilter(LetterGrade.F.getValue(), 1);
        CompositeLabeledDouble passed = InputUtilities.readGradeHistory("Passed", 
            in, passingFilter, tc);
        try
        {
          CompositeLabeledDouble earned = createHoursHistory("Earned Hours", null, tc, passed);
          System.out.println(earned.toString(true));
        }
        catch (SizeException se)
        {
          System.out.println("No data for passed courses and earned hours.");
        }
      }
      catch (IOException ioe)
      {
        System.out.println("Unable to read file for passed courses/earned hours.");
      }
      
      System.out.println();
    }
    System.out.println(cohort.toString(true));
  }
  
  /**
   * Create an hours composite from a grades composite.
   * 
   * @param label The label for the result
   * @param filter The Filter for the result
   * @param calculator The Calculator for the result
   * @param grades The grade composite to use to construct the credit composite
   * @return A composite hours information
   * @throws SizeException if data in the grade History is null
   */
  private static CompositeLabeledDouble createHoursHistory(final String label, 
      final Filter filter, final Calculator calculator, final CompositeLabeledDouble grades) 
          throws SizeException
  {
    CompositeLabeledDouble result = new CompositeLabeledDouble(label, filter, calculator);
    JMUCourseTable courseTable = new JMUCourseTable();
    List<LeafLabeledDouble> temp = grades.filter();
    for (LeafLabeledDouble d: temp)
    {
      LeafLabeledDouble element = new LeafLabeledDouble(d.getLabel(), courseTable.get(d.getLabel()));
      result.add(element);
    }
    return result;
  }

}
