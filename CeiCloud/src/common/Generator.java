package common;

import java.util.List;

public interface Generator<P extends Problem> {
	
	public static final long SEED = 4378578463L;

	/**
	 * Generates one input instance of problem P.
	 * @return
	 */
	P generateInstance();
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 */
	List<P> generateInstances(int n);
	
	/**
	 * Generates a seed for random method. Here it's a constant.
	 * @return
	 */
	long getSeed();
}
