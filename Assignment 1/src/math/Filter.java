package math;

import java.util.List;

public interface Filter {
	public List<LabeledDouble> apply(List<LabeledDouble> data) throws SizeException;
}