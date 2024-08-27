package math;

import java.util.List;

/**
 * An interface for calculators computing values from lists of LabeledDouble objects
 * 
 * This interface defines a method, performing calculations and 
 * returning a LabeledDouble as the result
 * @author Alex Nguyen
 */
public interface Calculator {
	public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws SizeException;
}
