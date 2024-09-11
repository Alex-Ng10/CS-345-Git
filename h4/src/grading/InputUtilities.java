package grading;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import math.Calculator;
import math.Filter;
import math.LabeledDouble;

public class InputUtilities
{
  private static LabeledDouble parseLine(String line)
  {
    String[] fields = line.split("\t");
    String course = fields[0];
    String grade = fields[1];

    if ("N/A".equals(grade))
    {
      return new LabeledDouble(course, null);
    }
    else
    {
      return new LabeledDouble(course, LetterGrade.fromCode(grade).getValue());
    }
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
  public static History readGradeHistory(String resultLabel, BufferedReader in, Filter filter,
      Calculator calculator) throws IOException
  {
    History history = new History(resultLabel, filter, calculator);
    String line;

    while ((line = in.readLine()) != null)
    {
      LabeledDouble labeledDouble = parseLine(line);
      history.add(labeledDouble);
    }

    return history;
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
  public static Cohort readCohort(String resultLabel, BufferedReader[] in, Filter filter,
      Calculator calculator) throws IOException
  {
    Cohort cohort = new Cohort(resultLabel);

    for (BufferedReader reader : in)
    {
      if (reader != null)
      {
        History history = readGradeHistory("GPA", reader, filter, calculator);
        cohort.add(history);
      }
    }

    return cohort;
  }
}
