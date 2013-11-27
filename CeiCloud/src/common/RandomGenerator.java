package common;

import java.util.List;
import java.util.Random;

public interface RandomGenerator<P extends Problem> {
	
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
	
	/**
	 * Generates a random object.
	 * @return
	 */
	Random getRandom();
}
