package math; Hello

import java.util.*;

/**
 * A filter for selecting LabeledDouble objects based on a threshold value and comparison signs.
 * 
 * The ThresholdFilter class filters a list of LabeledDouble objects which are defined by comparison
 * signs and a threshold value
 * 
 * @author Alex Nguyen
 */
public class ThresholdFilter implements Filter
{

  private int[] sign; // Array of signs indicating filter conditions (-1, 0, 1)
  private LabeledDouble threshold; // The threshold value used for comparison

  /**
   * Constructor to create a ThresholdFilter.
   * 
   * @param threshold
   *          The threshold value to compare against
   * @param sign
   *          An array of integers representing the conditions
   */
  public ThresholdFilter(final double threshold, final int... sign)
  {
    this.threshold = new LabeledDouble("Threshold", threshold);
    this.sign = sign;
  }

  /*
   * Filters the input list based on the specified threshold and signs
   * 
   * @param data The list of LabeledDouble objects to be filtered
   * 
   * @return A list of LabeledDouble objects that meet the filter criteria
   * 
   * @throws SizeException If the input list is null
   */
  @Override
  public List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException
  {

    List<LabeledDouble> filteredData = new ArrayList<>();

    if (data == null)
    {
      throw new SizeException("The input is null");
    }

    if (sign.length == 0)
    {
      return filteredData;
    }

    for (LabeledDouble item : data)
    {
      int compare = item.compareTo(threshold);
      for (int i : sign)
      {
        if ((i == 0 && compare == 0) || (i == -1 && compare == -1) || (i == 1 && compare == 1))
        {
          filteredData.add(item);
          break;
        }
      }
    }

    return filteredData;
  }
}
