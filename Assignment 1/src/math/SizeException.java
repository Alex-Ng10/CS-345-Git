package math;

/**
 * A custom exception class for handling size-related errors in operations
 * 
 * The SizeException class is signaling if an operation has encountered 
 * an unexpected size condition (such as an empty list or an inappropriate input size)
 * 
 * @see RuntimeException
 * 
 * @author Alex Nguyen
 */
public class SizeException extends RuntimeException {
  
  /**
   * Default constructor.
   */
  public SizeException() {
	  super();
  }
  
  /**
   * Constructor with error message
   * @param message error associated with the exception
   */
  public SizeException(String message) {
	  super(message);
  }
}
