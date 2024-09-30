package math;

/**
 * An abstract base class that implements the LabeledDouble interface. It provides basic
 * functionality for managing labeled double values, including comparison logic and string
 * representations.
 * 
 * This class handles common logic for subclasses such as managing labels, comparing values, and
 * providing a string representation.
 * 
 * @author Alex Nguyen
 * @version H5
 */
public abstract class AbstractLabeledDouble implements LabeledDouble
{
  protected final String label;
  private final String na = "N/A";

  /**
   * Constructs an AbstractLabeledDouble with the given label. Throws an IllegalArgumentException if
   * the label is null or empty.
   * 
   * @param label
   *          The label for this LabeledDouble (must not be null or empty)
   * @throws IllegalArgumentException
   *           If the label is null or empty
   */
  protected AbstractLabeledDouble(final String label)
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
  }

  /**
   * Compares this LabeledDouble to another LabeledDouble for order.
   * 
   * - Returns 0 if both values are null. - Returns -1 if this value is null but the other is not. -
   * Returns 1 if the other value is null but this is not. - Otherwise, compares the two values
   * numerically.
   * 
   * @param other
   *          The other LabeledDouble to compare to.
   * @return -1, 0, or 1 based on the comparison of values.
   */
  @Override
  public int compareTo(final LabeledDouble other)
  {
    if (this.getValue() == null && other.getValue() == null)
      return 0;
    if (this.getValue() == null)
      return -1;
    if (other.getValue() == null)
      return 1;
    return Double.compare(this.getValue(), other.getValue());
  }

  /**
   * Gets the label of this LabeledDouble.
   * 
   * @return The label associated with this LabeledDouble.
   */
  @Override
  public String getLabel()
  {
    return label;
  }

  /**
   * Create a String representation of this LabeledDouble.
   *
   * @param verbose
   *          true to return a verbose representation; false for terse
   * @return The String representation
   */
  public String toString(final boolean verbose)
  {
    String result;

    if (verbose)
    {
      if ((getValue() == null) || getValue().isNaN())
        result = String.format("%s: %s", label, na); // Spec 5.1.1
      else
        result = String.format("%s: %f", label, getValue()); // Spec 5.1.2
    }
    else
    {
      if ((getValue() == null) || getValue().isNaN())
        result = String.format("%s", na); // Spec 5.2.1
      else
        result = String.format("%f", getValue()); // Spec 5.2.2
    }

    return result;
  }

  /**
   * Create a terse String representation of this Score.
   *
   * @return The String representation
   */
  @Override
  public String toString()
  {
    return toString(false); // Spec 6
  }

}
