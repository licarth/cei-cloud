package common.generator;

import java.util.List;
import java.util.Random;

import common.problem.Instance;
import common.problem.Problem;
import common.problem.ProblemInputDataException;

public interface LibraryGenerator<P extends Problem> extends Generator<P> {
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 * @throws ProblemInputDataException 
	 */
	List<? extends Instance<P>> generateInstances() throws ProblemInputDataException;
	
}
