package math;

public class Numerics
{
  public static final double DEFAULT_MISSING_VALUE = 0.0;
  
  public static double doubleValueOf(Double number) {
    if (number != null) {
      return number.doubleValue();
    } else {
      return DEFAULT_MISSING_VALUE;
    }
  }
  
  public static double doubleValueOf(Double number, double missingValue) {
    if (number != null) {
      return number.doubleValue();
    } else {
      return missingValue;
    }
  }
  
  public static double doubleValueOf(Double number, double missingValue, double lowerBound) {
    if (number == null || number.doubleValue() < lowerBound) {
      return missingValue;
    } else {
      return number.doubleValue();
    }
  }
  
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
