package grading;

import java.util.*;

/**
 * An encapsulation of the number of credits associated with courses at 
 * James madison University.
 * 
 * This class is really a placeholder. Ultimately, we will have a general
 * CourseTable class that reads the information from a file.
 * 
 * @author Ann E. Koder, SagaciousMedia
 * @version H1
 */
public class JMUCourseTable extends HashMap<String, Double>
{
  private static final long serialVersionUID = 1L;

  /** 
   * Default Constructor.
   */
  public JMUCourseTable()
  {
    super();
    
    put("CS149", 3.0);
    put("CS159", 3.0);
    put("CS227", 3.0);
    put("CS240", 3.0);
    put("CS261", 3.0);
    put("CS337", 3.0);
    put("CS345", 3.0);
    put("CS361", 3.0);
    put("MATH199", 1.0);
    put("MATH231", 3.0);
    put("MATH232", 3.0);
    put("MATH235", 4.0);
    put("MATH236", 4.0);
    put("MATH237", 4.0);

  }
}
