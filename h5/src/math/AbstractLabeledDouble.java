package math;

public class AbstractLabeledDouble implements LabeledDouble
{
  protected final String label;

  protected AbstractLabeledDouble(String label)
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
  }

  @Override
  public int compareTo(LabeledDouble other)
  {
    if (getValue() == null && other.getValue() == null)
    {
      return 0;
    }
    if (getValue() == null)
    {
      return -1;
    }
    if (other.getValue() == null)
    {
      return 1;
    }
    return Double.compare(getValue(), other.getValue());
  }

  @Override
  public String getLabel()
  {
    return label;
  }
  
  public String toString(boolean verbose) {
    
  }
  
  public String toString() {
    
  }
}
