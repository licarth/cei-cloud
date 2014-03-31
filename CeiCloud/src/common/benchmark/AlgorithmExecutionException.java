package common.benchmark;

public class AlgorithmExecutionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlgorithmExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AlgorithmExecutionException(String message) {
		super(message);
	}
}
