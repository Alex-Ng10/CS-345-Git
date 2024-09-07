package math;

/**
 * A checked exception that indicates that a size problem has occurred.
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public class SizeException extends RuntimeException // Spec 1
{
  public static final long serialVersionUID = 1L;

  /**
   * Default Constructor.
   */
  public SizeException()
  {
    super();
  }

  /**
   * Explicit Value Constructor.
   * 
   * @param message   The human-readable message
   */
  public SizeException(final String message)
  {
    super(message);
  }
}
