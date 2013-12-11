package common.generator;

import java.util.List;
import java.util.Random;

import common.benchmark.BenchmarkRunException;
import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;

/**
 * IGenerator that creates random instances from a random seed.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface RandomGenerator<P extends IProblem, I extends IInstance<? extends P>> extends IGenerator<P,I>{
	
	/**
	 * Generates one input instance of the problem.
	 * @return
	 * @throws BenchmarkRunException 
	 */
	I generateInstance() throws ProblemInputDataException;
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws BenchmarkRunException 
	 */
	List<I> generateInstances(int n) throws ProblemInputDataException;
	
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
