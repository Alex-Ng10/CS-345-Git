package app;

import grading.*;
import java.util.*;
import math.*;

/**
 * The main class for the Transcriptz application.
 * 
 * This version calculates the quality points, attempted hours, earned hours, and grade point
 * average for a single individual.
 * 
 * @author Ann E. Koder, Sagacious Media
 * @version H1
 */
public class TranscriptzH1
{
  /**
   * The entry point of the application.
   * 
   * The command line arguments must contain course identifiers and grades for all of the
   * individual's courses (separated by spaces), followed by a space and the individual's name
   * 
   * @param args
   *          The command line arguments (see above)
   */
  public static void main(final String[] args)
  {
    if ((args == null) || ((args.length % 2) != 1))
    {
      System.err.println("You must enter course identifiers and letter grades for all of the"
          + "individual's courses (separated by spaces) followed by a space and the "
          + "individual's name.");
      System.exit(9);
    }

    // Setup the Map containing the credits for each course
    Map<String, Double> courseTable = new JMUCourseTable();

    // Print the header
    System.out.printf("Results for %s\n\n", args[args.length - 1]);

    // Process the command line arguments
    int n = (args.length - 1) / 2;
    List<LabeledDouble> grades = new ArrayList<LabeledDouble>();
    for (int i = 0; i < n; i++)
    {
      String label = args[i * 2];
      String letter = args[i * 2 + 1];
      LetterGrade letterGrade = LetterGrade.fromCode(letter);
      LabeledDouble element;
      if (letterGrade == null)
        element = new LabeledDouble(label, null);
      else
        element = new LabeledDouble(label, letterGrade.getValue());
      grades.add(element);
    }

    // Find the courses that the individual passed
    List<LabeledDouble> passingGrades;
    try
    {
      ThresholdFilter passingFilter = new ThresholdFilter(LetterGrade.F.getValue(), 1);
      passingGrades = passingFilter.apply(grades);
    }
    catch (SizeException se)
    {
      passingGrades = null;
      System.out.println("The individual did not have any grades.");
      System.exit(0);
    }

    // Create a List containing information about attempted hours
    List<LabeledDouble> attemptedHours = new ArrayList<LabeledDouble>();
    for (LabeledDouble g : grades)
    {
      if (g.getValue() != null)
      {
        String label = g.getLabel();
        Double credits = courseTable.get(label);
        attemptedHours.add(new LabeledDouble(label, credits));
      }
    }

    // Create a List containing information about earned hours
    List<LabeledDouble> earnedHours = new ArrayList<LabeledDouble>();
    for (LabeledDouble g : passingGrades)
    {
      String label = g.getLabel();
      Double credits = courseTable.get(label);
      earnedHours.add(new LabeledDouble(label, credits));
    }

    LabeledDouble qp, ah, eh, gpa;

    // Calculate the quality points, attempted hours and completed hours
    try
    {
      WeightedTotalCalculator wtc = new WeightedTotalCalculator(courseTable);
      qp = wtc.calculate("Quality Points", grades);

      WeightedTotalCalculator tc = new WeightedTotalCalculator();
      ah = tc.calculate("Attempted Hours", attemptedHours);

      eh = tc.calculate("Earned Hours", earnedHours);
    }
    catch (SizeException se)
    {
      // Shouldn't get here
      qp = null;
      ah = null;
      eh = null;
      System.out.println("There was a problem with the individual's grades.");
      System.exit(1);
    }

    // Calculate the GPA
    try
    {
      WeightedAverageCalculator wac = new WeightedAverageCalculator(courseTable);
      gpa = wac.calculate("GPA", grades);
    }
    catch (SizeException se)
    {
      gpa = null;
    }

    // Display the results
    System.out.println(qp.toString(true));
    System.out.println(ah.toString(true));
    System.out.println(eh.toString(true));

    if (gpa != null)
      System.out.println(gpa.toString(true));
    else
      System.out.println("GPA: Undefined");
  }
}
