package math;

final public class LabeledDouble {
  private final Double value;
  private final String label;
  
  public LabeledDouble(String label) {
    this(label, 0.0);
    if (label == null || label.isEmpty()) {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
  }
  
  public LabeledDouble(String label, double value) {
    if (label == null || label.isEmpty()) {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
    this.value = value;
  }

  public LabeledDouble(String label, Double value) {
    if (label == null || label.isEmpty()) {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
    this.value = value;
  }
  
  public int compareTo(LabeledDouble other) {
    if (this.value == null && other.value == null) {
      return 0;
    } else if (this.value == null && other.value != null) {
      return -1;
    } else if (other.value == null && this.value != null) {
      return 1;
    } 
    
    return Double.compare(this.value, other.value);
  }
  
  /**
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * @return the label
   */
  public String getLabel() {
    return label;
  }
  
  public String toString(boolean verbose) {
    if (verbose) {
      if (value == null || value.isNaN()) {
        return String.format("%s: %s", this.label, "N/A");
      } else {
        return String.format("%s: %f", this.label, this.value);
      }
    } else {
      if (value == null || value.isNaN()) {
        return String.format("%s", "N/A");
      } else {
        return String.format("%f", this.value);
      }
    }
  }
  
  public String toString() {
    return toString(false);
  }
  
}
