package math;

/**
 * A utility class for numeric operations, 
 * providing methods for handling null values and bounds
 * 
 * The Numerics class includes methods to safely convert Double objects to 
 * primitive doubles, handling missing values, and applying bounds
 * 
 * @author Alex Nguyen
 */
public class Numerics
{
  public static final double DEFAULT_MISSING_VALUE = 0.0;
  
  /**
   * Returns the primitive double value of a Double object, or a default value if null
   *
   * @param number The Double object to convert
   * @return The primitive double value, or the default missing value if null
   */
  public static double doubleValueOf(Double number) {
    if (number != null) {
      return number.doubleValue();
    } 
    return DEFAULT_MISSING_VALUE;
  }
  
  /**
   * Returns the primitive double value of a Double object, or a specified missing value if null
   * 
   * @param number The Double object to convert
   * @param missingValue The value to return if the number is null
   * @return The primitive double value, or the missing value if null
   */
  public static double doubleValueOf(Double number, double missingValue) {
    if (number != null) {
      return number.doubleValue();
    }
    return missingValue;
  }
  
  /**
   * @param number
   * @param missingValue
   * @param lowerBound
   * @return
   */
  public static double doubleValueOf(Double number, double missingValue, double lowerBound) {
    if (number == null || number.doubleValue() < lowerBound) {
      return missingValue;
    }
    return number.doubleValue();
  }
  
  /**
   * Returns the sign of the given integer
   * 
   * @param n The integer whose sign is to be determined
   * @return -1 if n is negative, 0 if n is zero, 1 if n is positive
   */
  public static int signum(int n) {
    if (n < 0) {
      return -1;
    } else if (n == 0) {
      return 0;
    } else {
      return 1;
    }
  }
}
