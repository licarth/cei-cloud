package common.problem;

import common.solution.OptimalCostNotKnownException;

public interface IOptimalCostAwareSolution {
	/**
	 * 
	 * 
	 * @return the error ratio between this solution and the optimal cost.
	 */
	public double getErrorRatio() throws OptimalCostNotKnownException;
}
