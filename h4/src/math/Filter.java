package math;

import java.util.List;

/**
 * A Filter can be used to "remove or include" specific LabeledDouble objects from a
 * List of LabeledDouble objects.
 *
 * Note: A Filter does not change the underlying List, it creates
 * a new List that satisfies the conditions of the Filter.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version H1
 */
public interface Filter
{
  /**
   * Apply this Filter.
   * 
   * This method throws a SizeException if the given List is null.
   *
   * @param data  The original List of LabeledDouble objects
   * @return A List containing the resulting LabeledDouble objects
   * @throws SizeException if the List is null
   */
  public abstract List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException;
}
