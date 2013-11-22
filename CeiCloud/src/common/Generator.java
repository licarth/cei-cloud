package common;

public interface Generator<P extends Problem> {
	
	public static final long SEED = 4378578463L;

	/**
	 * Generates one input instance of problem P.
	 * @return
	 */
	P generateInstance();
	
	/**
	 * Generates a seed for random method. Here it's a constant.
	 * @return
	 */
	long getSeed();
}
