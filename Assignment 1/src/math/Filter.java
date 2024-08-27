package math;

import java.util.List;

/**
 * An interface for filtering lists of LabeledDouble objects
 * 
 * The Filter interface defines a method for applying a filter 
 * returning a subset of the original list based on specific criteria.
 * 
 * @see LabeledDouble
 * @see SizeException
 * 
 * @author Alex Nguyen
 */
public interface Filter {
	public List<LabeledDouble> apply(List<LabeledDouble> data) throws SizeException;
}