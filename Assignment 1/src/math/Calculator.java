package math;

import java.util.List;

public interface Calculator {
	public LabeledDouble calculate(String resultLabel, List<LabeledDouble> data) throws SizeException;
}
