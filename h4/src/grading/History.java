package grading;

import java.util.ArrayList;
import java.util.List;

import math.Calculator;
import math.Filter;
import math.LabeledDouble;
import math.SizeException;

public class History 
{
  private String label;
  private List<LabeledDouble> data;
  private Calculator calculator;
  private Filter filter;

  public History(String label, Filter filter, Calculator calculator) throws IllegalArgumentException
  {
    if (label == null || label.isEmpty())
    {
      throw new IllegalArgumentException("Label cannot be null or empty");
    }
    this.label = label;
    this.filter = filter;
    this.calculator = calculator;
    this.data = new ArrayList<>();
  }

  public void add(LabeledDouble item)
  {
    if (item != null)
    {
      data.add(item);
    }
  }

  public List<LabeledDouble> filter() throws SizeException
  {
    if (filter == null)
    {
      return new ArrayList<>(data);
    }
    return filter.apply(data);
  }

  public String getLabel()
  {
    return label;
  }

  public LabeledDouble getValue() throws SizeException
  {
    return calculator.calculate(label, filter());
  }
}
