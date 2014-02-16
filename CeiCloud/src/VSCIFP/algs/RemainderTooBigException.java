package VSCIFP.algs;

public class RemainderTooBigException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemainderTooBigException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RemainderTooBigException(String message) {
		super(message);
	}
}
