package math;

/**
 * An interface representing a labeled double value, which includes a label and an optional value.
 * 
 * Classes implementing this interface must provide functionality to retrieve the label, the value
 * (which may be missing or null), and the string representation of the object.
 */
public interface LabeledDouble extends Comparable<LabeledDouble>
{
  /**
   * Returns a string representation of the LabeledDouble, with optional verbosity.
   * 
   * @param verbose
   *          If true, returns a verbose representation including the label and value. If false,
   *          returns a terse representation (usually just the value).
   * @return A string representation of the LabeledDouble.
   */
  public String toString(boolean verbose);

  /**
   * Returns a terse string representation of the LabeledDouble.
   * 
   * This is typically a simplified version that only includes the value without the label.
   * 
   * @return A string representation of the LabeledDouble.
   */
  public String toString();

  /**
   * Gets the label associated with this LabeledDouble.
   * 
   * @return The label as a String (must not be null or empty).
   */
  String getLabel();

  /**
   * Gets the value of this LabeledDouble.
   * 
   * The value may be null, indicating a missing or undefined value.
   * 
   * @return The value as a Double, or null if the value is missing.
   */
  Double getValue();
}
