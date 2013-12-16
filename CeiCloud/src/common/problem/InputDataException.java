package common.problem;

public class InputDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputDataException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InputDataException(String message) {
		super(message);
	}
}
