package common.generator;

import java.util.List;
import java.util.Random;

import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;

/**
 * Describes a generator that creates instances from an external source.
 * e.g. a text file.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface LibraryGenerator<P extends IProblem, I extends IInstance<P>> extends IGenerator<P, I> {
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws BenchmarkRunException 
	 */
	List<? extends IInstance<P>> generateInstances() throws ProblemInputDataException;
	
}
