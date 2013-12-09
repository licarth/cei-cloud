package common.generator;

import java.util.List;
import java.util.Random;

import common.problem.Instance;
import common.problem.Problem;

public interface RandomGenerator<P extends Problem> extends Generator<P>{
	
	/**
	 * Generates one input instance of the problem.
	 * @return
	 */
	Instance<P> generateInstance(P problem);
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 */
	List<? extends Instance<P>> generateInstances(int n);
	
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
