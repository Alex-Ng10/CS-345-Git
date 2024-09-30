package math;

import java.util.*;

/**
 * The requirements of a Transformer.
 * 
 * @author Ann E. Koder, Sagacious Media
 * @version H7
 */
public interface Transformer
{
  /**
   * Apply this Transformer.
   * 
   * @param data
   *          The data to transform
   * @return The result of the transformation
   * @throws SizeException
   *           if data is null/has no elements, or if the result is null/has no elements
   */
  public abstract List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException;
}
