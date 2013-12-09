package common.algorithm;

import common.problem.Instance;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public interface Algorithm<I extends Instance> {
	Solution<I> solve(I instance) throws ProblemInputDataException;
	I getInstance();
}
