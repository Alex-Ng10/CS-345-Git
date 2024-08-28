package math;

/**
 * A labeled double value, representing a numerical value associated with a specific label.
 * 
 * The LabeledDouble class encapsulates a double value along with a label, allowing for easy
 * identification and comparison of numerical data within a labeled context
 * 
 * @author Alex Nguyen
 */
public final class LabeledDouble
{
  private final Double value;
  private final String label;
  private String IllegalArgument = "Non Applicable";
  private String NullEmpty = "Label cannot be null or empty";

  /**
   * Constructor that initializes the LabeledDouble with a label and a default value of 0.0.
   * 
   * @param label
   *          The label associated with the value
   * @throws IllegalArgumentException
   *           If the label is null or empty
   */
  public LabeledDouble(final String label)
  {

    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException(NullEmpty);
    }
    this.label = label;
    this.value = 0.0;
  }

  /**
   * Constructor that initializes the LabeledDouble with a label and a specific value.
   * 
   * @param label
   *          The label associated with the value
   * @param value
   *          The numerical value
   * @throws IllegalArgumentException
   *           If the label is null or empty
   */
  public LabeledDouble(final String label, final double value)
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException(NullEmpty);
    }
    this.label = label;
    this.value = value;
  }

  /**
   * Constructor that initializes the LabeledDouble with a label and a specific value, allowing
   * nulls.
   * 
   * @param label
   *          The label associated with the value
   * @param value
   *          The numerical value, which may be null
   * @throws IllegalArgumentException
   *           If the label is null or empty
   */
  public LabeledDouble(final String label, final Double value)
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException(NullEmpty);
    }
    this.label = label;
    this.value = value;
  }

  /**
   * Compares this LabeledDouble with another LabeledDouble based on their values.
   * 
   * @param other
   *          The other LabeledDouble to compare with
   * @return -1 if this value is less than the other's, 1 if greater, 0 if equal
   */
  public int compareTo(final LabeledDouble other)
  {
    if (this.value == null && other.value == null)
    {
      return 0;
    }
    else if (this.value == null && other.value != null)
    {
      return -1;
    }
    else if (other.value == null && this.value != null)
    {
      return 1;
    }

    return Double.compare(this.value, other.value);
  }

  /**
   * Gets the value associated with this LabeledDouble.
   * 
   * @return the value
   */
  public Double getValue()
  {
    return value;
  }

  /**
   * Gets the label associated with this LabeledDouble.
   * 
   * @return The label
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Returns a string representation of this LabeledDouble, optionally in a verbose format.
   * 
   * @param verbose
   *          Whether to include the label in the string representation
   * @return A string representing the labeled double
   */
  public String toString(final boolean verbose)
  {
    if (verbose)
    {
      if (value == null || value.isNaN())
      {
        return String.format("%s: %s", this.label, IllegalArgument);
      }
      else
      {
        return String.format("%s: %f", this.label, this.value);
      }
    }
    else
    {
      if (value == null || value.isNaN())
      {
        return String.format("%s", IllegalArgument);
      }
      else
      {
        return String.format("%f", this.value);
      }
    }
  }

  /**
   * Returns a string representation of this LabeledDouble in non-verbose format.
   * 
   * @return A string representing the double value
   */
  public String toString()
  {
    return toString(false);
  }

}
