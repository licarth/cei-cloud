package common.solution;

public class OptimalCostNotKnownException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OptimalCostNotKnownException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OptimalCostNotKnownException(String message) {
		super(message);
	}
}
