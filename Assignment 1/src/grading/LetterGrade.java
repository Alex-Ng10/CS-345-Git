package grading;

public enum LetterGrade
{
  F("F", 0.0),
  DMINUS("D-", 0.7),
  D("D", 1.0),
  DPLUS("D+", 1.3),
  CMINUS("C-", 1.7),
  C("C", 2.0),
  CPLUS("C", 2.3),
  BMINUS("B-", 2.7),
  B("B", 3.0),
  BPLUS("B+", 3.3),
  AMINUS("A-", 3.7),
  A("A", 4.0);
  
  private Double points;
  private String label;
  
  private LetterGrade(String label, Double points) {
    this.label = label; 
    this.points = points;
  }
  
  public static LetterGrade fromCode(String code) {
    for (LetterGrade grade : LetterGrade.values()) {
      if (grade.getLabel().equals(code)) {
        return grade;
      }
    }
    return null;
  }

  /**
   * @return the label
   */
  public String getLabel() {
    
    return label;
    
  }

  public Double getValue() {
    return points;
  }
  
  public String toString() {
    
    return String.format("%2s (.1f)", label, points );
 
  }
}
