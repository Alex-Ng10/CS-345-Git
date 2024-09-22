package math;

import java.util.ArrayList;
import java.util.List;

/**
 * A Filter that can be used to add specific elements from a List to an initially empty List.
 * Specifically, it adds elements to the result based on whether they are less than, equal to, or
 * greater than a given threshold value.
 *
 * A ThresholdFilter makes use of a threshold (i.e., the number each element is compared to) and one
 * or more conditions (i.e., the kind of comparison that is performed).
 * 
 * If any of the conditions holds then the element must be added to the result. In other words, the
 * conditions are combined using a logical OR. If no conditions are supplied then the filtered list
 * must be empty. Note that the array of signs need not be ordered.
 * 
 * @author Prof. David Bernstein, James Madison University
 * @version H1
 */
public class ThresholdFilter implements Filter
{
  private int[] sign;
  private LabeledDouble threshold;

  /**
   * Explicit Value Constructor.
   * 
   * @param threshold
   *          The threshold to compare each element to
   * @param sign
   *          The conditions to use
   */
  public ThresholdFilter(final double threshold, final int... sign)
  {
    this.threshold = new LeafLabeledDouble("Threshold", threshold);
    this.sign = sign;
  }

  /**
   * Apply this Filter.
   * 
   * Note: This method must not change the order of the elements.
   * 
   * @param data
   *          The original List of LabeledDouble objects
   * @return A List containing the resulting LabeledDouble objects
   * @throws SizeException
   *           if the List is null
   */
  @Override
  public List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException
  {
    if ((data == null))
      throw new SizeException(); // Spec 4.1

    ArrayList<LabeledDouble> result = new ArrayList<LabeledDouble>(); // Spec 4.2
    if (sign == null || sign.length == 0)
      return result; // Spec 4.2.2

    for (LabeledDouble x : data)
    {
      int comparison = x.compareTo(threshold);
      for (int i = 0; i < sign.length; i++)
      {
        if (comparison == sign[i])
          result.add(x); // Spec 4.2.1; 4.2.3; 4.2.4
      }
    }
    return result;
  }
}
