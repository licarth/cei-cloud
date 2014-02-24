package common.generator;

import java.util.List;
import java.util.Random;

import common.benchmark.BenchmarkRunException;
import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.InputDataException;

/**
 * IGenerator that creates random instances from a random seed.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class RandomGenerator<P extends IProblem, I extends IInstance<? extends P>> extends AbstractGenerator<P,I>{
	
	public RandomGenerator(P problem) {
		super(problem);
	}

	/**
	 * Generates one input instance of the problem.
	 * @return
	 * @throws Exception 
	 * @throws BenchmarkRunException 
	 */
	abstract public I generateInstance() throws InputDataException, Exception;
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws BenchmarkRunException 
	 */
	abstract public List<I> generateInstances(int n) throws InputDataException;
	
	/**
	 * Generates a seed for random method. Here it's a constant.
	 * @return
	 */
	abstract public long getSeed();
	
	/**
	 * Generates a random object.
	 * @return
	 */
	abstract public Random getRandom();
}
