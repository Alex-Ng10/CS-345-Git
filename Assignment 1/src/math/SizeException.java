package math;

public class SizeException extends RuntimeException {
  public SizeException() {
	  super();
  }
  
  public SizeException(String message) {
	  System.out.println(message);
  }
}
