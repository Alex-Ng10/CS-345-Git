package grading;

import java.io.BufferedReader;
import java.io.IOException;
import math.Calculator;
import math.CompositeLabeledDouble;
import math.Filter;
import math.LabeledDouble;
import math.LeafLabeledDouble;
import math.WeightedAverageCalculator;

/**
 * InputUtilities is a utility class responsible for reading data from files . and converting it
 * into History and Cohort objects.
 * 
 * @author Alex Nguyen.
 */
public class InputUtilities
{
  /**
   * Parses a line from a file to extract the course name and grade. If the grade is "N/A", a null
   * value is stored for the grade.
   * 
   * @param line
   *          The line from the file to parse.
   * @return A LabeledDouble object representing the course and grade.
   */
  private static LabeledDouble parseLine(final String line)
  {
    String[] fields = line.split("\t");
    LetterGrade grade = LetterGrade.fromCode(fields[1]);

    LabeledDouble element;

    if (grade == null)
    {
      element = new LeafLabeledDouble(fields[0], null);
    }
    else
    {
      element = new LeafLabeledDouble(fields[0], grade.getValue());
    }
    return element;
  }

  /**
   * Reads the grade history from a BufferedReader and returns a History object.
   * 
   * @param resultLabel
   *          The label for the History object.
   * @param in
   *          The BufferedReader to read data from.
   * @param filter
   *          The Filter to apply to the History object.
   * @param calculator
   *          The Calculator to use for the History object.
   * @return A History object populated with course grades from the file.
   * @throws IOException
   *           if there is an issue with file reading.
   */
  public static CompositeLabeledDouble readGradeHistory(final String resultLabel,
      final BufferedReader in, final Filter filter, final Calculator calculator) throws IOException
  {
    CompositeLabeledDouble compositeLabel = new CompositeLabeledDouble(resultLabel, filter,
        calculator);
    String line;

    while ((line = in.readLine()) != null)
    {
      LabeledDouble labeledDouble = parseLine(line);
      compositeLabel.add(labeledDouble);
    }

    return compositeLabel;
  }

  /**
   * Reads multiple grade histories (a cohort) from an array of BufferedReaders and returns a Cohort
   * object.
   * 
   * @param resultLabel
   *          The label for the Cohort object.
   * @param in
   *          An array of BufferedReaders to read data from.
   * @param filter
   *          The Filter to apply to each History.
   * @param calculator
   *          The Calculator to use for each History.
   * @return A Cohort object containing the histories of each student.
   * @throws IOException
   *           if there is an issue with file reading.
   */
  public static CompositeLabeledDouble readCohort(final String resultLabel,
      final BufferedReader[] in, final Filter filter, final Calculator calculator)
      throws IOException
  {
    CompositeLabeledDouble compositeLabel = new CompositeLabeledDouble(resultLabel, null,
        new WeightedAverageCalculator(null));

    for (int i = 0; i < in.length; i++)
    {
      if (in[i] != null)
      {
        CompositeLabeledDouble history = readGradeHistory("GPA", in[i], filter, calculator);
        compositeLabel.add(history);
      }
    }
    return compositeLabel;
  }
}
