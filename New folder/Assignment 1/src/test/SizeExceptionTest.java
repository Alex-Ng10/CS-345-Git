package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import math.SizeException;

/**
 * Unit tests for the SizeException class.
 * 
 * This test class verifies that the SizeException is properly instantiated and behaves correctly
 * when thrown, particularly focusing on the default constructor.
 * 
 * @author Alex Nguyen
 */
public class SizeExceptionTest
{

  private String message = "Error Message";

  /**
   * default constructor test.
   */
  @Test
  public void testSizeException()
  {
    SizeException exception = new SizeException();
    assertEquals(null, exception.getMessage());
  }

  /**
   * Test default constructor with a message.
   */
  @Test
  public void testSizeExceptionMessage()
  {
    SizeException exception = new SizeException(message);
    assertEquals(message, exception.getMessage());
  }

}
