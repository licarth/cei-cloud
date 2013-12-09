package common.problem;

public class ProblemInputDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProblemInputDataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProblemInputDataException(String message) {
		super(message);
	}
}
