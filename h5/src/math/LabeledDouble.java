package math;

public interface LabeledDouble extends Comparable<LabeledDouble>
{
  public String toString(boolean verbose);
  
  public String toString();
  
  String getLabel();
 
  Double getValue();
 
}
