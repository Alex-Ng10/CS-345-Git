package math;

import java.util.List;

/**
 * The requirements of an object that is used to perform calculations on 
 * a List of LabeledDouble objects.
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public interface Calculator 
{
  /**
   * Calculate a LabeledDouble from a List of LabeledDouble objects.
   * 
   * @param resultLabel The label to use for the result
   * @param data  The List of LabeledDouble objects to use in the calculation
   * @return The resulting LabeledDouble
   * @throws SizeException if the List is null
   */
  public abstract LabeledDouble calculate(final String resultLabel, 
      final List<LabeledDouble> data) 
          throws SizeException;

}
