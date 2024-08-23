package math;
import java.util.*;

public class ThresholdFilter implements Filter{

	private int[] sign;
	private LabeledDouble threshold;
	
	public ThresholdFilter(double threshold, int... sign) {
		this.threshold = threshold;
		this.sign = sign;
	}
	
	public List<LabeledDouble> apply(List<LabeledDouble> data) throws SizeException {
		
		if (data == null) {
			throw new SizeException("The input is null");
		}
		
		List<LabeledDouble> result = new ArrayList<>();
		
		for (LabeledDouble item : data) {
			for (int i : sign) {
				if (i == 1 && item.getValue() > threshold.getValue()) {
					return 
				}
			}
		}
	}
}