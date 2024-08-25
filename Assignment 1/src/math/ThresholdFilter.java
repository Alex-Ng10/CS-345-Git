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
		
		List<LabeledDouble> filteredData = new ArrayList<>();
		
		if (data == null) {
			throw new SizeException("The input is null");
		}
		
		if (sign.length == 0) {
			return filteredData;
		}
		
		for (LabeledDouble item : data) {
			int compare = item.compareTo(threshold);
			for (int i : sign) {
			  if ((i == 0 && compare == 0) || (i == -1 && compare == -1) || (i == 1 && compare == 1)) {
			    filteredData.add(item);
			    break;
			  }
			}
		}
		
		return filteredData;
	}
}