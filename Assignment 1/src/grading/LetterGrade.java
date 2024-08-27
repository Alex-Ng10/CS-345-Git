package grading;
/**
 * An enumeration of letter grades and their associated grade points.
 * 
 * This enum represents the common letter grades used in an academic setting,
 * along with their corresponding grade points on a 4.0 scale.
 * 
 * @author Alex Nguyen
 */
public enum LetterGrade {
  F("F", 0.0),
  DMINUS("D-", 0.7),
  D("D", 1.0),
  DPLUS("D+", 1.3),
  CMINUS("C-", 1.7),
  C("C", 2.0),
  CPLUS("C+", 2.3),
  BMINUS("B-", 2.7),
  B("B", 3.0),
  BPLUS("B+", 3.3),
  AMINUS("A-", 3.7),
  A("A", 4.0);
  
  private Double points;
  private String label;
  
  /**
   * Constructor for LetterGrade.
   * 
   * @param label The label representing the letter grade (e.g., "A", "B+").
   * @param points The grade points associated with the letter grade.
   */
  private LetterGrade(String label, Double points) {
    this.label = label; 
    this.points = points;
  }
  
  /**
   * Retrieves the corresponding LetterGrade for a given grade code.
   * 
   * @param code The string code representing the grade (e.g., "A", "B+").
   * @return The matching LetterGrade, or null if the code is invalid.
   */
  public static LetterGrade fromCode(String code) {
    for (LetterGrade grade : LetterGrade.values()) {
      if (grade.getLabel().equals(code)) {
        return grade;
      }
    }
    return null;
  }

  /**
   * Gets the label of the letter grade.
   * 
   * @return The label.
   */
  public String getLabel() {
    
    return label;
    
  }

  /**
   * Gets the grade points associated with the letter grade.
   * 
   * @return The grade points.
   */
  public Double getValue() {
    return points;
  }
  
  /**
   * Returns a string representation of the letter grade.
   * 
   * @return A string formatted as "label (points)".
   */
  public String toString() {
    
    return String.format("%-2s (%3.1f)", label, points.doubleValue());
 
  }
}
