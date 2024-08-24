package math;
import java.util.*;

public class ThresholdFilter implements Filter {

	private int[] sign;
	private LabeledDouble threshold;
	
	public ThresholdFilter(double threshold, int... sign) {
		this.threshold = new LabeledDouble("Threshold", threshold);
		this.sign = sign;
	}
	
	@Override
	public List<LabeledDouble> apply(List<LabeledDouble> data) throws SizeException {
		
		if (data == null) {
			throw new SizeException("The input is null");
		}
		
		List<LabeledDouble> filteredData = new ArrayList<>();
		
		for (LabeledDouble item : data) {
			boolean addList = false;
			
			for (int s : sign) {
				if ( (s == 1 && item.getValue() > threshold.getValue())) {
					addList = true;
				} else if (s == 0 && item.getValue() == threshold.getValue()) {
					addList = true;
				} else if (s == -1 && item.getValue() < threshold.getValue()) {
					addList = true;
				}
			}
			
			if (addList) {
				filteredData.add(item);
			}
		}
		
		return filteredData;
	}
}