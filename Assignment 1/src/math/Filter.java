package math;

import java.util.List;

/**
 * An interface for filtering lists of LabeledDouble objects
 * 
 * The Filter interface defines a method for applying a filter returning a subset of the original
 * list based on specific criteria.
 * 
 * @see LabeledDouble
 * @see SizeException
 * 
 * @author Alex Nguyen
 */
public interface Filter
{
  /**
   * Applies the filter to a list of LabeledDouble objects.
   * 
   * @param data
   * @return Filtered list of LabeledDouble objects
   * @throws SizeException
   */
  public List<LabeledDouble> apply(List<LabeledDouble> data) throws SizeException;
}
