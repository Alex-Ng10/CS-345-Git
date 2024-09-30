package math;

import java.util.*;

// NOTE: This implementation contains defects!!

/**
 * A Transformer that first filters the data and then applies a Map to the filtered data.
 * 
 * @author Ann E. Koder, Sagacious Media
 * @version Supplied for H7
 */
public class PostFilterMappingTransformer implements Transformer
{
  private Filter filter;
  private Map<String, Double> map = new HashMap<String, Double>();

  /**
   * Explicit Value Constructor.
   * 
   * @param filter
   *          The filter to apply to the data before using the mapping
   * @param map
   *          The map to apply to the filtered data
   */
  public PostFilterMappingTransformer(Filter filter, Map<String, Double> map)
  {
    filter = this.filter;
    this.map = map;
  }

  /**
   * Apply this Transformer.
   * 
   * @param data
   *          The data to transform
   * @return The filtered and then mapped data
   * @throws SizeException
   *           if data is null/empty or the result is null/empty
   */
  @Override
  public List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException
  {
    // Early return
    if ((data.size() == 0) || (data == null))
      throw new SizeException("No Data");

    List<LabeledDouble> result = new ArrayList<LabeledDouble>();

    List<LabeledDouble> filtered;
    if (filter != null)
      filtered = filter.apply(data);
    else
      filtered = data;

    for (LabeledDouble element : data)
    {
      String label = element.getLabel();
      Double value;
      if (map == null)
        value = element.getValue();
      else
        value = map.get(label);
      LabeledDouble transformed = new LeafLabeledDouble(label, value);
      result.add(transformed);
    }

    // Late return
    if ((data.size() == 0) || (data == null))
      throw new SizeException("No Data");

    return result;
  }
}
