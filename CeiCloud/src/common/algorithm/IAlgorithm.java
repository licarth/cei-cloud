package common.algorithm;

import common.problem.IInstance;
import common.problem.Problem;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public interface IAlgorithm<P extends Problem, I extends IInstance<? extends P>> {
	
	Solution<P, I> solve(I instance) throws ProblemInputDataException;
	
	I getInstance();
	/**
	 * 
	 * A short name for this algorithm.
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * Short Abbrev for this alg.
	 * 
	 * @return
	 */
	String getAbbrev();
}
