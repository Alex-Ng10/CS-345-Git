package math;

/**
 * A class representing a labeled double value that can optionally be missing (null). Extends
 * AbstractLabeledDouble and provides immutable behavior.
 */
public class LeafLabeledDouble extends AbstractLabeledDouble
{

  private final Double value;

  /**
   * Constructor with a label only (default value is 0.0).
   * 
   * @param label
   *          The label (must not be null or empty)
   */
  public LeafLabeledDouble(final String label)
  {
    this(label, Double.valueOf(0.0));
  }

  /**
   * Constructor with a label and a primitive double value.
   * 
   * @param label
   *          The label (must not be null or empty)
   * @param value
   *          The value (as a primitive double)
   * @throws IllegalArgumentException if the label is null or empty
   */
  public LeafLabeledDouble(final String label, final double value)
  {
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
  public LeafLabeledDouble(final String label, final Double value)
  {
    super(label);
    this.value = value;
  }

  @Override
  public Double getValue()
  {
    return value;
  }
}
