package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grading.LetterGrade;
import math.LabeledDouble;

/**
 * Unit tests for the LetterGrade enum.
 * 
 * This test class verifies the functionality of the LetterGrade enum, including the methods for
 * retrieving a grade from a code, getting the label, getting the value, and converting the grade to
 * a string representation.
 * 
 * @author Alex Nguyen
 */
class LetterGradeTest
{

  private static String A = "A";
  private static String AMINUS = "A-";
  private static String BPLUS = "B+";
  private static String B = "B";
  private static String BMINUS = "B-";
  private static String CPLUS = "C+";
  private static String C = "C";
  private static String CMINUS = "C-";
  private static String DPLUS = "D+";
  private static String D = "D";
  private static String DMINUS = "D-";
  private static String F = "F";

  /**
   * Tests the fromCode method.
   * 
   * Verifies that each letter grade code returns the corresponding LetterGrade and that null code
   * returns null
   */
  @Test
  void testFromCode()
  {
    assertAll(() -> assertEquals(LetterGrade.A, LetterGrade.fromCode(A)),
        () -> assertEquals(LetterGrade.AMINUS, LetterGrade.fromCode(AMINUS)),
        () -> assertEquals(LetterGrade.BPLUS, LetterGrade.fromCode(BPLUS)),
        () -> assertEquals(LetterGrade.B, LetterGrade.fromCode(B)),
        () -> assertEquals(LetterGrade.BMINUS, LetterGrade.fromCode(BMINUS)),
        () -> assertEquals(LetterGrade.CPLUS, LetterGrade.fromCode(CPLUS)),
        () -> assertEquals(LetterGrade.C, LetterGrade.fromCode(C)),
        () -> assertEquals(LetterGrade.CMINUS, LetterGrade.fromCode(CMINUS)),
        () -> assertEquals(LetterGrade.DPLUS, LetterGrade.fromCode(DPLUS)),
        () -> assertEquals(LetterGrade.D, LetterGrade.fromCode(D)),
        () -> assertEquals(LetterGrade.DMINUS, LetterGrade.fromCode(DMINUS)),
        () -> assertEquals(LetterGrade.F, LetterGrade.fromCode(F)),
        () -> assertEquals(null, LetterGrade.fromCode(null)));
  }

  /**
   * Ensure that the correct label is returned for each LetterGrade.
   */
  @Test
  void testGetLabel()
  {
    assertAll(() -> assertEquals(A, LetterGrade.A.getLabel()),
        () -> assertEquals(AMINUS, LetterGrade.AMINUS.getLabel()),
        () -> assertEquals(BPLUS, LetterGrade.BPLUS.getLabel()),
        () -> assertEquals(B, LetterGrade.B.getLabel()),
        () -> assertEquals(BMINUS, LetterGrade.BMINUS.getLabel()),
        () -> assertEquals(CPLUS, LetterGrade.CPLUS.getLabel()),
        () -> assertEquals(C, LetterGrade.C.getLabel()),
        () -> assertEquals(CMINUS, LetterGrade.CMINUS.getLabel()),
        () -> assertEquals(DPLUS, LetterGrade.DPLUS.getLabel()),
        () -> assertEquals(D, LetterGrade.D.getLabel()),
        () -> assertEquals(DMINUS, LetterGrade.DMINUS.getLabel()),
        () -> assertEquals(F, LetterGrade.F.getLabel()));
  }

  /**
   * Ensures that the correct grade point value is returned for each LetterGrade.
   */
  @Test
  void testGetValue()
  {
    assertAll(() -> assertEquals(4.0, LetterGrade.A.getValue()),
        () -> assertEquals(3.7, LetterGrade.AMINUS.getValue()),
        () -> assertEquals(3.3, LetterGrade.BPLUS.getValue()),
        () -> assertEquals(3.0, LetterGrade.B.getValue()),
        () -> assertEquals(2.7, LetterGrade.BMINUS.getValue()),
        () -> assertEquals(2.3, LetterGrade.CPLUS.getValue()),
        () -> assertEquals(2.0, LetterGrade.C.getValue()),
        () -> assertEquals(1.7, LetterGrade.CMINUS.getValue()),
        () -> assertEquals(1.3, LetterGrade.DPLUS.getValue()),
        () -> assertEquals(1.0, LetterGrade.D.getValue()),
        () -> assertEquals(0.7, LetterGrade.DMINUS.getValue()),
        () -> assertEquals(0.0, LetterGrade.F.getValue()));
  }

  /**
   * Verifies that the string representation of each LeterGrade is formatted correctly.
   */
  @Test
  void testToString()
  {
    assertEquals("F  (0.0)", LetterGrade.F.toString());
    assertEquals("D- (0.7)", LetterGrade.DMINUS.toString());
    assertEquals("D  (1.0)", LetterGrade.D.toString());
    assertEquals("D+ (1.3)", LetterGrade.DPLUS.toString());
    assertEquals("C- (1.7)", LetterGrade.CMINUS.toString());
    assertEquals("C  (2.0)", LetterGrade.C.toString());
    assertEquals("C+ (2.3)", LetterGrade.CPLUS.toString());
    assertEquals("B- (2.7)", LetterGrade.BMINUS.toString());
    assertEquals("B  (3.0)", LetterGrade.B.toString());
    assertEquals("B+ (3.3)", LetterGrade.BPLUS.toString());
    assertEquals("A- (3.7)", LetterGrade.AMINUS.toString());
    assertEquals("A  (4.0)", LetterGrade.A.toString());
  }
}
