package math;

/**
 * An immutable encapsulation of a floating point number that has an associated label. An important
 * aspect of LabeledDouble objects is that they can be "missing" (i.e., have null values), and this
 * can be used when performing calculations involving them (e.g., missing values can be ignored).
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
// public class LeafLabeledDouble implements Comparable<LeafLabeledDouble>
// {
// private static final String NA = "N/A";
//
// private final Double value;
// private final String label;
//
// /**
// * Construct a LabeledDouble with a value of 0.0.
// *
// * @param label
// * The label
// * @throws IllegalArgumentException
// * if the label is null or empty
// */
// public LeafLabeledDouble(final String label) throws IllegalArgumentException
// {
// this(label, Double.valueOf(0.0)); // Spec 3
// }
//
// /**
// * Construct a LabeledDouble with the given value.
// *
// * @param label
// * The label
// * @param value
// * The value
// * @throws IllegalArgumentException
// * if the label is null or empty
// */
// public LeafLabeledDouble(final String label, final double value) throws IllegalArgumentException
// {
// this(label, Double.valueOf(value));
// }
//
// /**
// * Construct a LabeledDouble with the given value.
// *
// * @param label
// * The label
// * @param value
// * The value
// * @throws IllegalArgumentException
// * if the label is null or empty
// */
// public LeafLabeledDouble(final String label, final Double value) throws IllegalArgumentException
// {
// if ((label == null) || label.equals(""))
// throw new IllegalArgumentException(); // Spec 2
//
// this.label = label;
// this.value = value;
// }
//
// /**
// * Compare this LabeledDouble object with the given LabeledDouble object for order. Note that,
// * unlike other classes that implement the Comparable interface, in a LabeledDouble this method
// * return -1, 0, or 1.
// *
// * @param other
// * The LabeledDouble object to compare to
// * @return -1/0/1 depending on whether this </==/> other
// */
// @Override
// public int compareTo(final LeafLabeledDouble other)
// {
// int result;
//
// if ((this.value == null) && (other.value == null))
// result = 0; // Spec 4.1
// else if (this.value == null)
// result = -1; // Spec 4.2
// else if (other.value == null)
// result = 1; // Spec 4.3
// else
// result = Numerics.signum(this.value.compareTo(other.value)); // Spec 4
//
// return result;
// }
//
// /**
// * Get the label for this LabeledDouble.
// *
// * @return The label
// */
// public String getLabel()
// {
// return label;
// }
//
// /**
// * Get the Double value of this LabeledDouble (or null to indicate that the value is missing).
// *
// * @return The Double value (or null)
// */
// public Double getValue()
// {
// return value;
// }
//
// /**
// * Create a String representation of this LabeledDouble.
// *
// * @param verbose
// * true to return a verbose representation; false for terse
// * @return The String representation
// */
// public String toString(final boolean verbose)
// {
// String result;
//
// if (verbose)
// {
// if ((value == null) || value.isNaN())
// result = String.format("%s: %s", label, NA); // Spec 5.1.1
// else
// result = String.format("%s: %f", label, value); // Spec 5.1.2
// }
// else
// {
// if ((value == null) || value.isNaN())
// result = String.format("%s", NA); // Spec 5.2.1
// else
// result = String.format("%f", value); // Spec 5.2.2
// }
//
// return result;
// }
//
// /**
// * Create a terse String representation of this Score.
// *
// * @return The String representation
// */
// @Override
// public String toString()
// {
// return toString(false); // Spec 6
// }

public class LeafLabeledDouble extends AbstractLabeledDouble
{

  private final Double value;

  /**
   * Constructor with a label only (default value is 0.0).
   * 
   * @param label
   *          The label (must not be null or empty)
   */
  public LeafLabeledDouble(String label)
  {
    this(label, Double.valueOf(0.0));
  }

  public LeafLabeledDouble(String label, double value) {
    this(label, Double.valueOf(value));
  }
  
  /**
   * Constructor with a label and value.
   * 
   * @param label
   *          The label (must not be null or empty)
   * @param value
   *          The value (nullable)
   */
  public LeafLabeledDouble(String label, Double value) {
    super(label);
    if (label == null || label.isEmpty()) {
        throw new IllegalArgumentException("Label must not be null or empty");
    }
    this.value = value;
}

  @Override
  public Double getValue()
  {
    return value;
  }
}