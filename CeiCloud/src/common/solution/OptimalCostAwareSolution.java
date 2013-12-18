package common.solution;

import common.algorithm.IAlgorithm;
import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IOptimalCostAwareSolution;
import common.problem.IProblem;

public abstract class OptimalCostAwareSolution<P extends IProblem, I extends IInstance<? extends P> & IOptimalCostAwareInstance> extends AbstractSolution<P, I> implements IOptimalCostAwareSolution {

	public OptimalCostAwareSolution(IAlgorithm<P, I> sourceAlgorithm) {
		super(sourceAlgorithm);
	}
	
	@Override
	public float getErrorRatio() {
		return (float) getCost() / (float) getInstance().getOptimalCost();
	}
	
}
