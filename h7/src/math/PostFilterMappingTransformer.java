//package math;
//
//import java.util.*;
//
//// NOTE: This implementation contains defects!!
//
///**
// * A Transformer that first filters the data and then applies a Map to the filtered data.
// * 
// * @author Ann E. Koder, Sagacious Media
// * @version Supplied for H7
// */
//public class PostFilterMappingTransformer implements Transformer
//{
//  private Filter filter;
//  private Map<String, Double> map = new HashMap<String, Double>();
//
//  /**
//   * Explicit Value Constructor.
//   * 
//   * @param filter
//   *          The filter to apply to the data before using the mapping
//   * @param map
//   *          The map to apply to the filtered data
//   */
//  public PostFilterMappingTransformer(Filter filter, Map<String, Double> map)
//  {
//    filter = this.filter;
//    this.map = map;
//  }
//
//  /**
//   * Apply this Transformer.
//   * 
//   * @param data
//   *          The data to transform
//   * @return The filtered and then mapped data
//   * @throws SizeException
//   *           if data is null/empty or the result is null/empty
//   */
//  @Override
//  public List<LabeledDouble> apply(final List<LabeledDouble> data) throws SizeException
//  {
//    if (data == null || data.size() == 0) // Fix: checking for null first
//      throw new SizeException("No Data");
//
//    List<LabeledDouble> result = new ArrayList<>();
//    List<LabeledDouble> filtered;
//    if (filter != null)
//    {
//      filtered = filter.apply(data);
//    }
//    else
//    {
//      filtered = data;
//    }
//
//    for (LabeledDouble element : filtered)
//    { // Fix: using filtered instead of data
//      String label = element.getLabel();
//      Double value;
//      if (map == null)
//      {
//        value = map.get(label);
//      }
//      else
//      {
//        value = null;
//      }
//      result.add(new LeafLabeledDouble(label, value));
//    }
//
//    if (result.size() == 0)
//    {
//      throw new SizeException("No Data");
//    }
//
//    return result;
//  }
//}

package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Transformer that first filters the data and then applies a Map to the filtered data.
 * 
 * @author Ann E. Koder, Sagacious Media
 * @version Supplied for H7
 */
public class PostFilterMappingTransformer implements Transformer
{
  private static String noData = "No Data";
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
  public PostFilterMappingTransformer(final Filter filter, final Map<String, Double> map)
  {
    this.filter = filter; // Fix: Correct assignment of filter
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
    if (data == null || data.size() == 0)
    {
      // Fix: checking for null first
      throw new SizeException(noData);
    }

    List<LabeledDouble> result = new ArrayList<>();
    List<LabeledDouble> filtered;

    // Fix: Correct handling for filter application
    if (filter != null)
    {
      filtered = filter.apply(data);
    }
    else
    {
      filtered = data;
    }

    for (LabeledDouble element : filtered)
    {
      String label = element.getLabel();
      Double value;

      // Fix: Handle null map correctly
      if (map != null)
      {
        value = map.get(label);
      }
      else
      {
        value = null;
      }
      result.add(new LeafLabeledDouble(label, value));
    }

    // Fix: Check result size to throw exception if no valid data
    if (result.size() == 0)
    {
      throw new SizeException(noData);
    }

    return result;
  }
}
