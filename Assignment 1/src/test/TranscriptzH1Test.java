package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.TranscriptzH1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TranscriptzH1Test
{

  
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp()
  {
    System.setOut(new PrintStream(outputStreamCaptor));
    System.setErr(new PrintStream(outputStreamCaptor));
  } 

  @Test
  public void testValidInput() throws Exception
  {
    // Test case where all courses have valid grades
    String[] args = {"CS101", "A", "MATH101", "B+", "HIST101", "C-", "John Doe"};
    TranscriptzH1.main(args);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Results for John Doe"));
    assertTrue(output.contains("Quality Points:"));
    assertTrue(output.contains("Attempted Hours:"));
    assertTrue(output.contains("Earned Hours:"));
    assertTrue(output.contains("GPA:"));
  }

  @Test
  public void testInvalidInput()
  {
    // Test case where command-line arguments are incorrect
    String[] args = {"CS101", "A", "MATH101", "John Doe"};
    TranscriptzH1.main(args);

    String output = outputStreamCaptor.toString().trim();
    assertEquals("You must enter course identifiers and letter grades for all of the"
        + "individual's courses (separated by spaces) followed by a space and the "
        + "individual's name.", output);
  }

  @Test
  public void testInvalidGrade()
  {
    // Test case where a grade is invalid
    String[] args = {"CS101", "A++", "MATH101", "B+", "John Doe"};
    TranscriptzH1.main(args);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Results for John Doe"));
    assertTrue(output.contains("Quality Points:"));
    assertTrue(output.contains("Attempted Hours:"));
    assertTrue(output.contains("Earned Hours:"));
    assertTrue(output.contains("GPA: Undefined"));
  }

  @Test
  public void testEmptyGrades()
  {
    // Test case where no grades are provided
    String[] args = {""};
    TranscriptzH1.main(args);

    String output = outputStreamCaptor.toString().trim();
    assertEquals("You must enter course identifiers and letter grades for all of the"
        + "individual's courses (separated by spaces) followed by a space and the "
        + "individual's name.", output);
  }

  @Test
  public void testNoGradesPassed()
  {
    // Test case where all grades are F
    String[] args = {"CS101", "F", "MATH101", "F", "HIST101", "F", "John Doe"};
    TranscriptzH1.main(args);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("The individual did not have any grades."));
  }
}
