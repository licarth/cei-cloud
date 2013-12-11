package common.benchmark;

public class BenchmarkRunException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BenchmarkRunException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BenchmarkRunException(String message) {
		super(message);
	}
}
