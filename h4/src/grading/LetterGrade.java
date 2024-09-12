package grading;

/**
 * An encapsulation of a letter grade (e.g., in a college course).
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public enum LetterGrade
{
  F ("F", 0.0),        // Spec 1
  DMINUS ("D-", 0.7),  // Spec 2
  D ("D", 1.0),        // Spec 3
  DPLUS ("D+", 1.3),   // Spec 4
  CMINUS ("C-", 1.7),  // Spec 5
  C ("C", 2.0),        // Spec 6
  CPLUS ("C+", 2.3),   // Spec 7
  BMINUS ("B-", 2.7),  // Spec 8
  B ("B", 3.0),        // Spec 9
  BPLUS ("B+", 3.3),   // Spec 10
  AMINUS ("A-", 3.7),  // Spec 11
  A ("A", 4.0);        // Spec 12
  
  private String label;
  private Double points;
  
  /**
   * Explicit value constructor.
   * 
   * @param label
   *          The label for the LetterGrade
   * @param points
   *          The numeric points associated with the LetterGrade
   */
  private LetterGrade(final String label, final double points)
  {
    this.label = label;
    this.points = Double.valueOf(points);
  }
  
  /**
   * Get the LetterGrade associated with a particular code.
   * 
   * @param code  The code of interest
   * @return The corresponding LetterGrade (or null)
   */
  public static LetterGrade fromCode(final String code)
  {
    for (LetterGrade p: values())
    {
      if (code.equals(p.label)) return p; // Spec 14
    }
    return null;
  }
  
  /**
   * Get the label of this LetterGrade.
   * 
   * @return The label
   */
  public String getLabel()
  {
    return label;
  }
  
  /**
   * Get the numeric value of this LetterGrade.
   * 
   * @return  The numeric value
   */
  public Double getValue()
  {
    return points;
  }
  
  /**
   * Get the String representation of this LetterGrade.
   * 
   * @return The String representation
   */
  @Override
  public String toString()
  {
    return String.format("%-2s (%3.1f)", label, points.doubleValue()); // Spec 13
  }
}
