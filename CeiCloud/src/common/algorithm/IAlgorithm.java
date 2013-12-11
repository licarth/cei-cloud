package common.algorithm;

import common.problem.IInstance;
import common.problem.Instance;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public interface IAlgorithm<P extends IProblem, I extends IInstance<P>> {
	
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
	
	Solution<P, I> solve(I instance) throws ProblemInputDataException;
}
