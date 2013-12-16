package common.generator;

import java.util.List;
import java.util.Random;

import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.InputDataException;

/**
 * Describes a generator that creates instances from an external source.
 * e.g. a text file.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class LibraryGenerator<P extends IProblem, I extends IInstance<P>> extends AbstractGenerator<P, I> {
	
	public LibraryGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws BenchmarkRunException 
	 */
	abstract public List<? extends IInstance<P>> generateInstances() throws InputDataException;
	
}
