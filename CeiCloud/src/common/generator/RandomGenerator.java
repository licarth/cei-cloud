package common.generator;

import java.util.List;
import java.util.Random;

import common.problem.Instance;
import common.problem.Problem;
import common.problem.ProblemInputDataException;

/**
 * Generator that creates random instances from a random seed.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface RandomGenerator<P extends Problem> extends Generator<P>{
	
	/**
	 * Generates one input instance of the problem.
	 * @return
	 * @throws ProblemInputDataException 
	 */
	Instance<P> generateInstance() throws ProblemInputDataException;
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws ProblemInputDataException 
	 */
	List<? extends Instance<P>> generateInstances(int n) throws ProblemInputDataException;
	
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
